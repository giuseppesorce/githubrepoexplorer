package com.giuseppesorce.watchersexplorer.data.api

import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchRepoResponsePayLoad
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SubscriberResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Giuseppe Sorce
 */
interface SearchApi {


    @GET("search/repositories")
    fun searchRepository(@Query("q") word: String): Single<SearchRepoResponsePayLoad>

    @GET("repos/{owner}/{repo}/subscribers")
    fun searchSubscribers(@Path("owner")  owner: String, @Path("repo")  repo: String): Single<List<SubscriberResponse>>

}