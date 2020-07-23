package com.reschikov.testtask65apps.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("f_name") @Expose val fName : String,
                    @SerializedName("l_name") @Expose val lName : String,
                    @SerializedName("birthday") @Expose val birthday : String?,
                    @SerializedName("avatr_url") @Expose val avatrUrl : String?,
                    @SerializedName("specialty") @Expose val specialty : List<Specialty>)