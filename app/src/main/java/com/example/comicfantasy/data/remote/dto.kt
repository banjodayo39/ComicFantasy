package com.example.comicfantasy.data.remote

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
    var results: List<Results>? = null
)


data class Results(

   var id: String="",
   @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null,
    var title: String? = ""
   /* var characters: Characters? = null,
    var collectedIssues: List<CollectedIssue?>? = null,
    var collections: List<Collection?>? = null,
    var creators: Creators? = null,
    var dates: List<Date?>? = null,
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
    var prices: List<Price?>? = null,
    var resourceURI: String? = null,
    var series: Series? = null,
    var stories: Stories? = null,
    var textObjects: List<TextObject?>? = null,
    var upc: String? = null,
    var urls: List<Url?>? = null,
    var variantDescription: String? = null,
    var variants: List<Variant?>? = null*/
)
data class Characters(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<Item?>? = null,
    var returned: String? = null
)

data class Item(
    var name: String? = null,
    var resourceURI: String? = null,
    var role: String? = null
)

data class CollectedIssue(
    var name: String? = null,
    var resourceURI: String? = null
)

data class Collection(
    var name: String? = null,
    var resourceURI: String? = null
)

data class Creators(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<ItemX?>? = null,
    var returned: String? = null
)

data class ItemX(
    var name: String? = null,
    var resourceURI: String? = null,
    var role: String? = null
)

data class Date(
    var date: String? = null,
    var type: String? = null
)

data class Events(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<ItemXX?>? = null,
    var returned: String? = null
)

data class ItemXX(
    var name: String? = null,
    var resourceURI: String? = null
)

data class Image(
    var extension: String? = null,
    var path: String? = null
)

data class Price(
    var price: String? = null,
    var type: String? = null
)

data class Series(
    var name: String? = null,
    var resourceURI: String? = null
)

data class Stories(
    var available: String? = null,
    var collectionURI: String? = null,
    var items: List<ItemXXX?>? = null,
    var returned: String? = null
)

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

data class Thumbnail(
    var extension: String? = null,
    var path: String? = null
)

data class Url(
    var type: String? = null,
    var url: String? = null
)

data class Variant(
    var name: String? = null,
    var resourceURI: String? = null
)