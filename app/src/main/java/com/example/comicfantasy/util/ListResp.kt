package com.example.comicfantasy.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class ListResp<T> {

    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("data")
    @Expose
    var list: List<T>? = null

    @SerializedName("error")
    @Expose
    var error: Error? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}
