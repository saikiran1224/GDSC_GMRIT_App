package com.gmrit.gdsc.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R
import com.gmrit.gdsc.activities.general.EventProfileActivity
import com.gmrit.gdsc.models.EventDetailsData

class EventsAdapter(private val context: Context, private val pastEventsList: ArrayList<EventDetailsData>):
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_event_display,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsAdapter.ViewHolder, position: Int) {

        holder.eventTitle.text = pastEventsList[position].eventName
        holder.eventDescription.text = pastEventsList[position].eventTagLine

        Glide.with(context).load(pastEventsList[position].eventPosterUrl.toString()).into(holder.eventPosterImageView)


        holder.btnKnowMore.setOnClickListener {
            val intent = Intent(context, EventProfileActivity::class.java)
            intent.putExtra("eventName",pastEventsList[position].eventName)
            intent.putExtra("eventTagLine",pastEventsList[position].eventTagLine)
            intent.putExtra("eventDate",pastEventsList[position].eventDate)
            intent.putExtra("aboutTheEvent",pastEventsList[position].aboutTheEvent)
            intent.putExtra("eventPosterUrl",pastEventsList[position].eventPosterUrl)
            intent.putExtra("eventInstructorName",pastEventsList[position].eventInstructorName)
            intent.putExtra("eventInstructorPhotoUrl",pastEventsList[position].eventInstructorPhotoUrl)
            intent.putExtra("eventPreRequisites",pastEventsList[position].eventPreRequisites)
            intent.putExtra("thingsYouWillLearn",pastEventsList[position].thingsYouWillLearn)
            intent.putExtra("eventRegisterUrl",pastEventsList[position].eventRegisterUrl)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return pastEventsList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val eventTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        val eventDescription = itemView.findViewById<TextView>(R.id.eventDescription)

        val eventPosterImageView = itemView.findViewById<ImageView>(R.id.eventPosterImage)


        val btnKnowMore = itemView.findViewById<CardView>(R.id.btnKnowMore)


    }
}