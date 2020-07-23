package com.reschikov.testtask65apps.net

import com.reschikov.testtask65apps.net.model.Reply
import retrofit2.Call
import retrofit2.http.GET

interface RequestTestTask {

    @GET("65gb/static/raw/master/testTask.json")
    fun getResponses() : Call<Reply>
}