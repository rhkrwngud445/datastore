package com.db.datastore

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HomeRvAdapter (private val items: ArrayList<recyclerList>) : RecyclerView.Adapter<HomeRvAdapter.ViewHolder>() {
//
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view : View = v

        val textview : TextView? = null

        fun bind(listener:View.OnClickListener,item: recyclerList) {

//            tv_name.text= item.name
//            tv_minPrice.text= item.minPrice.toString()
//            tv_deliveryTip.text= item.deliveryTip.toString()
//            tv_deliveryTime.text= item.deliverTime.toString()
            view.setOnClickListener(listener)
            //view..setImageDrawable(item.image)
            //tv_name.text = item.name.setOnClickListener(listener)
        }

        init
        {
//            layout = view.findViewById(R.id.rc_layout)
//            tv_name = view.findViewById(R.id.rc_tv_name)
//            tv_deliveryTime = view.findViewById(R.id.rc_tv_deliverytime)
//            tv_deliveryTip = view.findViewById(R.id.rc_tv_deliveytip)
//            tv_minPrice = view.findViewById(R.id.rc_tv_minprice)
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
    }


}