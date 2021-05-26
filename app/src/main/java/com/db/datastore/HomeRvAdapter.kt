package com.db.datastore

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class HomeRvAdapter (private val items: ArrayList<recyclerList>) : RecyclerView.Adapter<HomeRvAdapter.ViewHolder>() {
//
    var imageurl : String? = null
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view : View = v

        var title : TextView? = null
        var address : TextView? = null
        var imageview : ImageView? = null
        var price : TextView?  = null
        var reply : TextView? = null
        var heart : TextView? = null

        fun bind(listener:View.OnClickListener,item: recyclerList) {

            title!!.text = item.title
            address!!.text = item.address
            price!!.text = item.price.toString()
            reply!!.text = item.reply.toString()
            heart!!.text = item.heart.toString()

            view.setOnClickListener(listener)
        }

        init
        {
            imageview = view.findViewById(R.id.iv_profile_home_rv)
            title = view.findViewById(R.id.tv_title_home_rv)
            address = view.findViewById(R.id.tv_address_home_tv)
            price = view.findViewById(R.id.tv_price_home_rv)
            reply= view.findViewById(R.id.tv_reply_home_rv)
            heart = view.findViewById(R.id.tv_heart_home_rv)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_recycler_item, parent, false)
            return ViewHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        // listener -> 클릭시 이벤트를 넣고 싶을 때

        val listener = View.OnClickListener { it->

        }
        holder.apply {
            bind(listener,item)
            itemView.tag= item
        }
        Glide.with(holder.itemView.context).load(item.imageview).into(holder.imageview!!)
    }


}