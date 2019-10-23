package com.example.comicfantasy.data.remote

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "comicEntity")
data class DataResponse(
    @PrimaryKey
    @NonNull
    var attributionHTML: String = "",
    var attributionText: String? = null,
    var code: String? = null,
    var copyright: String? = null,
    var data: DataX? = null,
    var etag: String? = null,
    var status: String? = null
)

data class DataX(
    var count: String= "",
    var limit: String? = null,
    var offset: String? = null,
    @SerializedName("results")
    var results: List<Results?>? = null,
    var total: String? = null
)

data class ResultsResp(
    @SerializedName("results")
    var results: ArrayList<Results>? = null
)


@Parcelize
data class Results(

   var id: Int?=null,
   @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null,
    var title: String? = "",
   @SerializedName("characters")
   var characters: Characters? = null,

    var creators: Creators? = null,
    var description: String? = null,
    var diamondCode: String? = null,
    var digitalId: String? = null,
    var ean: String? = null,
    var events: Events? = null,
    var format: String? = null,
    var images: List<Image?>? = null,
    var isbn: String? = null,
    var issn: String? = null,
    var issueNumber: String? = null,
    var modified: String? = null,
    var pageCount: String? = null,
    var resourceURI: String? = null,
    var stories: Stories? = null,
    var upc: String? = null,
    var series: Series,
/*
    var dates: List<Date?>? = null,
       var series: Series? = null,

    var textObjects: List<TextObject?>? = null,
        var variants: List<Variant?>? = null,
        var prices: List<Price?>? = null,
     var collectedIssues: List<CollectedIssue?>? = null,
    var collections: List<Collection?>? = null,
    var urls: List<Url?>? = null,
*/
    var variantDescription: String? = null
):Parcelable

@Parcelize
data class Characters(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<Item?>? = null,
    var returned: String? = null
):Parcelable

@Parcelize
data class Item(
    var name: String? = null,
    var resourceURI: String? = null,
    var role: String? = null
):Parcelable

data class CollectedIssue(
    var name: String? = null,
    var resourceURI: String? = null
)

data class Collection(
    var name: String? = null,
    var resourceURI: String? = null
)

@Parcelize
data class Creators(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<ItemX?>? = null,
    var returned: String? = null
):Parcelable

@Parcelize
data class ItemX(
    var name: String? = null,
    var resourceURI: String? = null,
    var role: String? = null
):Parcelable

data class Date(
    var date: String? = null,
    var type: String? = null
)

@Parcelize
data class Events(
    var available: String? = null,
    var collectionURI: String? = null,
   // var items: List<ItemXX?>? = null,
    var returned: String? = null
):Parcelable

data class ItemXX(
    var name: String? = null,
    var resourceURI: String? = null
)


@Parcelize
data class Image(
    var extension: String? = null,
    var path: String? = null
):Parcelable

data class Price(
    var price: String? = null,
    var type: String? = null
)

@Parcelize
data class Series(
    var name: String? = null,
    var resourceURI: String? = null
):Parcelable

@Parcelize
data class Stories(
    var available: String? = null,
    var collectionURI: String? = null,
  //  var items: List<ItemXXX?>? = null,
    var returned: String? = null
):Parcelable

data class ItemXXX(
    var name: String? = null,
    var resourceURI: String? = null,
    var type: String? = null
)

data class TextObject(
    var language: String? = null,
    var text: String? = null,
    var type: String? = null
)

@Parcelize
data class Thumbnail(
    var extension: String? = null,
    var path: String? = null
):Parcelable



data class Url(
    var type: String? = null,
    var url: String? = null
)

data class Variant(
    var name: String? = null,
    var resourceURI: String? = null
)

