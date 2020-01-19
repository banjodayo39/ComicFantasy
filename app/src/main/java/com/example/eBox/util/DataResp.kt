package com.example.eBox.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataResp<T> {
    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("data")
    @Expose
    var data: T? = null

    @SerializedName("results")
    @Expose
    var results: T? = null

    @SerializedName("error")
    @Expose
    var error: Error? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

}
