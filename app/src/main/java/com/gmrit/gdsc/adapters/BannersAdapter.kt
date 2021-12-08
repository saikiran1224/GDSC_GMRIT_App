package com.gmrit.gdsc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.models.BannerData

class BannersAdapter(private val context: Context, private val eventsDataList: ArrayList<BannerData>):
    RecyclerView.Adapter<BannersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_banner_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersAdapter.ViewHolder, position: Int) {
       holder.eventTitle.text = eventsDataList[position].eventTitle
       holder.eventDescription.text = eventsDataList[position].eventDescription

        holder.eventImage.setImageResource(eventsDataList[position].eventImageUrl)

      // Glide.with(context).load(eventsDataList[position].toString()).into(holder.eventImage)
    }

    override fun getItemCount(): Int {
       return eventsDataList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val eventTitle = itemView.findViewById<TextView>(R.id.bannerTitle)
        val eventDescription = itemView.findViewById<TextView>(R.id.bannerDescription)
        val eventImage = itemView.findViewById<ImageView>(R.id.bannerIcon)

    }
}