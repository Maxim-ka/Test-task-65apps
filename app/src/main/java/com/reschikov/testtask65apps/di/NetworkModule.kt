package com.reschikov.testtask65apps.di

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.lifecycle.LiveData
import com.reschikov.testtask65apps.net.CheckNetWork
import com.reschikov.testtask65apps.net.NetWorkProvider
import com.reschikov.testtask65apps.net.RequestTestTask
import com.reschikov.testtask65apps.net.tsl.ActivateableTLS
import com.reschikov.testtask65apps.net.tsl.ClientOkHttp
import com.reschikov.testtask65apps.repository.Requested
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_SERVER = "http://gitlab.65apps.com/"

@Module
abstract class NetworkModule{

    companion object{

        @SuppressLint("JvmStaticProvidesInObjectDetector")
        @JvmStatic
        @Provides
        fun provideRequestTestTask(activateableTLS : Lazy<ActivateableTLS>) : RequestTestTask {
            return getRetrofit(activateableTLS)
                .create(RequestTestTask::class.java)
        }

        private fun getRetrofit(activateableTLS : Lazy<ActivateableTLS>) : Retrofit {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Retrofit
                    .Builder()
                    .client(activateableTLS.get().getClient())
                    .baseUrl(URL_SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            } else {
                Retrofit
                    .Builder()
                    .baseUrl(URL_SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }

        @SuppressLint("JvmStaticProvidesInObjectDetector")
        @JvmStatic
        @Provides
        fun provideConnectivityManager(context: Context) : ConnectivityManager {
            return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }

    @Binds
    abstract fun bindRequested(netWorkProvider: NetWorkProvider) : Requested

    @Binds
    abstract fun bindLiveDataStateNet(checkNetWork: CheckNetWork) : LiveData<Boolean>

    @Binds
    abstract fun bindActivateableTLS(clientOkHttp : ClientOkHttp) : ActivateableTLS
}