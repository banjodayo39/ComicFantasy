package com.example.comicfantasy.util

import com.google.gson.annotations.SerializedName

class ApiError {

    @SerializedName("error")
    var error: Error? = null
}
