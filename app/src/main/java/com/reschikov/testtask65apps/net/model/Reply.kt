package com.reschikov.testtask65apps.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reply(@SerializedName("response") @Expose val response : List<Response>)