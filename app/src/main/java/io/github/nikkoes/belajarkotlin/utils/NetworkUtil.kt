package io.github.nikkoes.belajarkotlin.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil {

    companion object {

        fun isConnect(context: Context): Boolean {

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return if (activeNetworkInfo != null) {
                activeNetworkInfo.isConnected || activeNetworkInfo.isConnectedOrConnecting
            } else {
                false
            }
        }
    }


}