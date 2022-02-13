package com.gmrit.gdsc.adapters

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R
import com.google.common.collect.ArrayTable
import org.w3c.dom.Text
import java.util.*

class SliderOnBoardingAdapter(private val context: Context, private val imagesList: ArrayList<Int>, private val titlesList: ArrayList<String>, private val descriptionList: ArrayList<String>): RecyclerView.Adapter<SliderOnBoardingAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderOnBoardingAdapter.ViewPagerViewHolder {

        val  view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_slide_layout,parent,false)
        return ViewPagerViewHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: SliderOnBoardingAdapter.ViewPagerViewHolder, position: Int) {

        Glide.with(context).load(imagesList[position]).into(holder.itemView.findViewById<ImageView>(R.id.imageView))
        //holder.itemView.findViewById<ImageView>(R.id.imageView).setImageResource(imagesList[position])

        val spannable = SpannableStringBuilder(titlesList[position].toString())
        if(titlesList[position].toString().equals("Find New Experience"))
            spannable.setSpan(ForegroundColorSpan(context.getColor(R.color.lightBlue)), 5, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        else if(titlesList[position].toString().equals("Improve your Skills"))
            spannable.setSpan(ForegroundColorSpan(context.getColor(R.color.lightRed)), 13, 19, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        else
            spannable.setSpan(ForegroundColorSpan(context.getColor(R.color.lightYellow)), 9, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE)


        holder.itemView.findViewById<TextView>(R.id.txtTitle).text =spannable
        holder.itemView.findViewById<TextView>(R.id.txtDescription).text = descriptionList[position].toString()


    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

}