package com.reschikov.testtask65apps.net.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Specialty(@SerializedName("specialty_id") @Expose val specialtyId : Int,
                     @SerializedName("name") @Expose val name : String)