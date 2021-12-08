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
import com.gmrit.gdsc.models.UpcomingEventData

class UpcomingEventsAdapter(private val context: Context, private val upcomingEventsList: ArrayList<UpcomingEventData>):
    RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_event_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingEventsAdapter.ViewHolder, position: Int) {

        holder.eventTitle.text = upcomingEventsList[position].eventTitle
        holder.eventDescription.text = upcomingEventsList[position].eventDesc

        holder.bannerTitle.text = upcomingEventsList[position].bannerTitle
        holder.bannerDescription.text = upcomingEventsList[position].bannerDesc
        holder.bannerImage.setImageResource(upcomingEventsList[position].bannerIcon)

      // Glide.with(context).load(eventsDataList[position].toString()).into(holder.eventImage)
    }

    override fun getItemCount(): Int {
       return upcomingEventsList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val eventTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        val eventDescription = itemView.findViewById<TextView>(R.id.eventDescription)
        val bannerTitle = itemView.findViewById<TextView>(R.id.bannerTitle)
        val bannerDescription = itemView.findViewById<TextView>(R.id.bannerDescription)
        val bannerImage = itemView.findViewById<ImageView>(R.id.bannerIcon)

    }
}