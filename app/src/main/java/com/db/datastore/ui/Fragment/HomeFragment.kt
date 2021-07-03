package com.db.datastore.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.db.datastore.adapter.HomeRvAdapter
import com.db.datastore.R
import com.db.datastore.`interface`.MainStoreInterface
import com.db.datastore.data.ResellResponse
import com.db.datastore.ui.Activity.MainActivity
import com.db.datastore.ui.Activity.SelectCategoryActivity
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

        val categoryBt : ImageButton = root!!.findViewById(R.id.home_tune_ib)
        categoryBt.setOnClickListener {
            val categoryIntent = Intent(activity, SelectCategoryActivity::class.java)
            startActivity(categoryIntent)
        }

        var ResellService = retrofit.create(MainStoreInterface::class.java)
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



