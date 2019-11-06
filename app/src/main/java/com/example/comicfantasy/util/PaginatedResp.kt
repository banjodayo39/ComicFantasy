package com.example.comicfantasy.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginatedResp<T> : ListResp<T>() {

    @SerializedName("next_index")
    @Expose
    var nextIndex: Int = 0

}
