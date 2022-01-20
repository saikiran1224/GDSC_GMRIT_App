package com.gmrit.gdsc.activities.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmrit.gdsc.R
import com.gmrit.gdsc.adapters.OurTeamAdapter
import com.gmrit.gdsc.models.TeamMemberData

class OurTeamActivity : AppCompatActivity() {

    lateinit var ourTeamRecycler: RecyclerView
    lateinit var ourTeamAdapter: OurTeamAdapter
    lateinit var teamMembersList: ArrayList<TeamMemberData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_our_team)

        ourTeamRecycler = findViewById(R.id.ourTeamRecycler)

        teamMembersList = ArrayList()

        teamMembersList.add(TeamMemberData("Sai Kiran Kopparthi", R.drawable.sai_kiran_pic, "GDSC Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub", R.drawable.saikiran))
        teamMembersList.add(TeamMemberData("Yeswanth Choudari", R.drawable.sai_kiran_pic, "UI/UX Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub",R.drawable.yeswanth))
        teamMembersList.add(TeamMemberData("Vinay Sri Ram Tummidi", R.drawable.sai_kiran_pic, "Full Stack Dev Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub",R.drawable.vinay))
        teamMembersList.add(TeamMemberData("Prathyusha Kuppili", R.drawable.sai_kiran_pic, "Android Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub",R.drawable.aravind))
        teamMembersList.add(TeamMemberData("Aravind Vinjamuri", R.drawable.sai_kiran_pic, "Competetive Programming Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub",R.drawable.prathyusha))
        teamMembersList.add(TeamMemberData("Pallavi Pareek", R.drawable.sai_kiran_pic, "Product Design Lead", "Good Looking and Handsome person looking forward to teach Android", "Insta", "Linkedin", "GitHub",R.drawable.pallavi))

        ourTeamAdapter = OurTeamAdapter(this, teamMembersList)
        ourTeamRecycler.adapter = ourTeamAdapter
        ourTeamRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ourTeamRecycler.hasFixedSize()

    }
}