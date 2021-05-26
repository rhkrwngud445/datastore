package com.db.datastore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class HomeFragment : Fragment() {
    var list = ArrayList<recyclerList>()
    var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.home_fragment, container, false)
        list.clear()

        var retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recyclerView = root.findViewById(R.id.recyclerview_home)
        var adapter: HomeRvAdapter

        var ResellService = retrofit.create(ResellService::class.java)
        ResellService.getStoreList().enqueue(object : Callback<List<ResellResponse>>{
            override fun onFailure(call: Call<List<ResellResponse>>, t: Throwable) {
                Log.d("home_failure",t.message.toString())
            }

            override fun onResponse(
                call: Call<List<ResellResponse>>,
                response: Response<List<ResellResponse>>
            ) {

                    val responseList : List<ResellResponse>? = response.body()
                    var size = responseList!!.size

                for (i in 0..size - 1) {
                    list.add(
                        recyclerList(
                            responseList[i]!!.title!!,
                            responseList[i]!!.photos!![0].url.toString(),
                            "",
                            responseList[i].price!!,
                            0,
                            0
                        )
                    )
                }


                adapter = HomeRvAdapter(list)
                recyclerView!!.adapter = adapter
                recyclerView!!.adapter!!.notifyDataSetChanged()
            }


        })
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

data class recyclerList(
    var title: String, var imageview: String?,
    var address: String, var price: Int, var reply: Int, var heart: Int
)

interface ResellService {

    @GET("/api/posts/all")
    fun getStoreList(): retrofit2.Call<List<ResellResponse>>
}

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

