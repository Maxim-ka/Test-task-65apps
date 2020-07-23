package com.reschikov.testtask65apps.net.tsl

import okhttp3.OkHttpClient

interface ActivateableTLS {
    fun getClient() : OkHttpClient
}