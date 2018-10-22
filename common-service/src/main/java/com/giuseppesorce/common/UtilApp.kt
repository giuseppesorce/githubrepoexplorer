package com.giuseppesorce.common

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */

class UtilConnections @Inject constructor(
) {

    /**
     * Check connection, any type.Be careful not to forget to get ACCESS_NETWORK_STATE permissions
     * @author Android dev site
     * @see <a href="https://developer.android.com/training/monitoring-device-state/connectivity-monitoring/>Determine if you have an internet connection</a>
     */
    @SuppressLint("MissingPermission")
    fun checkConnections(context: Context?): Boolean {
        val cm: ConnectivityManager? = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm?.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}
