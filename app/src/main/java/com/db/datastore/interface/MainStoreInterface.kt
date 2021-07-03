package com.db.datastore.`interface`

import com.db.datastore.data.ResellResponse
import retrofit2.http.GET

interface MainStoreInterface {
    @GET("/api/posts/all")
    fun getStoreList(): retrofit2.Call<List<ResellResponse>>
}