package com.gmrit.gdsc.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmrit.gdsc.R
import com.gmrit.gdsc.models.BannerData
import com.gmrit.gdsc.models.TeamMemberData
import de.hdodenhof.circleimageview.CircleImageView

class OurTeamAdapter(private val context: Context, private val teamMembersList: ArrayList<TeamMemberData>):
    RecyclerView.Adapter<OurTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_team_card,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: OurTeamAdapter.ViewHolder, position: Int) {

        Glide.with(context).load(teamMembersList[position].memPhotUrl).placeholder(R.drawable.gdsc_temp_logo).into(holder.memPhotoUrl)

        holder.memName.text = teamMembersList[position].memName
        holder.memRole.text = teamMembersList[position].memRole
        holder.memDescription.text = teamMembersList[position].memDesc

        // TODO: Need to inscribe links of Social Media Handles on Icons
        holder.memInstaUrl.contentDescription = "Instagram"
        holder.memGitUrl.contentDescription = "GitHub"
        holder.memLinkedinUrl.contentDescription = "LinkedIn"


       holder.coverImageUrl.setImageDrawable(context.getDrawable(teamMembersList[position].coverImagerUrl))

    }

    override fun getItemCount(): Int {
       return teamMembersList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val memPhotoUrl = itemView.findViewById<CircleImageView>(R.id.imageTeamMember)
        var memName = itemView.findViewById<TextView>(R.id.txtTeamMemberName)
        var memRole = itemView.findViewById<TextView>(R.id.txtTeamMemberRole)
        var memDescription = itemView.findViewById<TextView>(R.id.txtAboutTeamMember)
        var memInstaUrl = itemView.findViewById<ImageView>(R.id.iconInstagram)
        var memLinkedinUrl = itemView.findViewById<ImageView>(R.id.iconLinkedin)
        var memGitUrl = itemView.findViewById<ImageView>(R.id.iconGitHub)
        var coverImageUrl = itemView.findViewById<ImageView>(R.id.coverImage)

    }
}