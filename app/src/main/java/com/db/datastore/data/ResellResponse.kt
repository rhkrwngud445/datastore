package com.db.datastore.data

import com.google.gson.annotations.SerializedName

class ResellResponse {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("author")
    val author: Author? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("content")
    val content: String? = null

    @SerializedName("photos")
    val photos: ArrayList<Photos>? = null

    @SerializedName("location")
    val location: Location? = null

    @SerializedName("price")
    val price: Int? = null

    @SerializedName("category")
    val category: String? = null
    @SerializedName("status")
    val status: String? = null

}

class Author {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("profilePhotoUrl")
    val profilePhotoUrl: String? = null
}

class Photos {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("url")
    val url: String? = null
}

class Location {
    @SerializedName("longitude")
    val longitude: String? = null

    @SerializedName("latitude")
    val latitude: String? = null
}
