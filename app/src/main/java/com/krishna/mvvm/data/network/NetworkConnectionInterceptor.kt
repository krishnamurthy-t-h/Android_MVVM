package com.krishna.mvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.krishna.mvvm.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationCOntext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()){
            throw NoInternetException("You must have active internet connection")
        }

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable() : Boolean{

        val connectivityManager = applicationCOntext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }

    }

}