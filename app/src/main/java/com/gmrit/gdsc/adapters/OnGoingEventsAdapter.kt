package com.gmrit.gdsc.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.EventProfileActivity
import com.gmrit.gdsc.models.BannerData
import com.gmrit.gdsc.models.OnGoingEventData
import com.gmrit.gdsc.models.UpcomingEventData

class OnGoingEventsAdapter(private val context: Context, private val onGoingEventsList: ArrayList<OnGoingEventData>):
    RecyclerView.Adapter<OnGoingEventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_event_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnGoingEventsAdapter.ViewHolder, position: Int) {

        holder.eventTitle.text = onGoingEventsList[position].eventTitle
        holder.eventDescription.text = onGoingEventsList[position].eventDesc

       /* holder.bannerTitle.text = onGoingEventsList[position].bannerTitle
        holder.bannerDescription.text = onGoingEventsList[position].bannerDesc
        holder.bannerImage.setImageResource(onGoingEventsList[position].bannerIcon)
*/
        holder.btnKnowMore.setOnClickListener {
            val intent = Intent(context, EventProfileActivity::class.java)
            context.startActivity(intent)
        }

      // Glide.with(context).load(eventsDataList[position].toString()).into(holder.eventImage)
    }

    override fun getItemCount(): Int {
       return onGoingEventsList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val eventTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        val eventDescription = itemView.findViewById<TextView>(R.id.eventDescription)
        /*
        val bannerTitle = itemView.findViewById<TextView>(R.id.bannerTitle)
        val bannerDescription = itemView.findViewById<TextView>(R.id.bannerDescription)
        val bannerImage = itemView.findViewById<ImageView>(R.id.bannerIcon)
*/
        val btnKnowMore = itemView.findViewById<CardView>(R.id.btnKnowMore)


    }
}