package com.giuseppesorce.watchersexplorer.android.models

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Giuseppe Sorce
 */
class HeadersConfiguration {

    companion object {
        private const val CONTENT_TYPE_KEY = "content-type"
        private const val CONTENT_TYPE_VALUE = "application/json"
        private const val LANG_DEFAULT = "en"
        private const val CLIENT_DEFAULT = "android-5.0/1.0demo"
        private const val TOKEN = "X-TOKEN"
        private const val LANG = "X-LANG"
        private const val CLIENT = "X-CLIENT"
    }

    /**
     * Session headers map which contains all the headers that
     * need to be used for authorizing any network communication.
     */
    var headers: Map<String, String> = mapOf(CONTENT_TYPE_KEY to CONTENT_TYPE_VALUE, LANG to LANG_DEFAULT, CLIENT to CLIENT_DEFAULT)
        private set

    /**
     * Updates the headers hold by the current instance using the given parameters.
     */
    fun updateHeaders(sessionId: String, token: String, lang:String, sid:String, client: String) {
        headers = mapOf(
                CONTENT_TYPE_KEY to CONTENT_TYPE_VALUE,
                TOKEN to token,
                CLIENT to client,
                LANG to lang
        )
    }
}


@Singleton
class AuthHeaderInterceptor @Inject
constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        //builder.addHeader("Authorization", environment.getCredentials())
        return chain.proceed(builder.build())
    }
}
