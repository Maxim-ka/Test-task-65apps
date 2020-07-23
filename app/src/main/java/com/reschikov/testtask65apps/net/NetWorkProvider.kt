package com.reschikov.testtask65apps.net

import android.net.ConnectivityManager
import android.os.Build
import com.reschikov.testtask65apps.entity.dot.NoNetWork
import com.reschikov.testtask65apps.net.model.Reply
import com.reschikov.testtask65apps.repository.Requested
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetWorkProvider @Inject constructor(
                      private val request: RequestTestTask,
                      private val connectivity : ConnectivityManager) : Requested {


    override suspend fun receive(): Reply {
        return suspendCoroutine{continuation ->
            if (hasNotNet()) continuation.resumeWithException(NoNetWork())
            else request.getResponses().enqueue(object : Callback<Reply>{
                override fun onFailure(call: Call<Reply>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                    if(response.isSuccessful){
                        response.body()?.let { continuation.resume(it) }
                    } else {
                        response.errorBody()?.let { continuation.resumeWithException(Throwable(it.string())) }
                    }
                }
            })
        }
    }

    private fun hasNotNet() : Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return  connectivity.activeNetwork == null
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return !connectivity.isDefaultNetworkActive
        }
        val networkInfo = connectivity.activeNetworkInfo
        return networkInfo == null || !networkInfo.isConnectedOrConnecting
    }
}