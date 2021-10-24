package com.d34th.movies.utils

import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {
    suspend fun isNetworkAvailable()= coroutineScope {
        return@coroutineScope try {
            val sock=Socket()
            val socketAddress=InetSocketAddress("8.8.8.8",53)
            sock.connect(socketAddress,1500)
            sock.close()
            true
        }catch (e:Exception){
            false
        }
    }
}