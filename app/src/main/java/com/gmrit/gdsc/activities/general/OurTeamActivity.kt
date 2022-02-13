package com.gmrit.gdsc.activities.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

    lateinit var closeIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.light_light_blue)

        setContentView(R.layout.activity_our_team)


        ourTeamRecycler = findViewById(R.id.ourTeamRecycler)

        closeIcon = findViewById(R.id.closeIcon)

        teamMembersList = ArrayList()

        teamMembersList.add(TeamMemberData("Dr. P. Kanchanamala", getString(R.string.dr_p_kanchanamala_mam_profile_pic), "Faculty Coordinator", "Science is what we understand well enough to explain to a computer. Art is everything else we do.", "Insta", "Linkedin", "GitHub", R.drawable.pallavi))
        teamMembersList.add(TeamMemberData("Sai Kiran Kopparthi", getString(R.string.saikiran_profile_pic), "GDSC Lead", "The biggest issue on software teams is making sure everyone understands what everyone else is doing.", "Insta", "Linkedin", "GitHub", R.drawable.saikiran))
        teamMembersList.add(TeamMemberData("Yeswanth Choudari",getString(R.string.yeswanth_profile_pic), "UI/UX Lead", "Object thinking focuses our attention on the problem space rather than the solution space.", "Insta", "Linkedin", "GitHub",R.drawable.yeswanth))
        teamMembersList.add(TeamMemberData("Vinay Sri Ram Tummidi", getString(R.string.vinaysriram_pic), "Full Stack Dev Lead", "The value of a prototype is in the education it gives you, not in the code itself.", "Insta", "Linkedin", "GitHub",R.drawable.prathyusha))
        teamMembersList.add(TeamMemberData("Prathyusha Kuppili", getString(R.string.prathyusha_profile_pic), "Android Lead", "Simple things should be simple, complex things should be possible", "Insta", "Linkedin", "GitHub",R.drawable.aravind))
        teamMembersList.add(TeamMemberData("Aravind Vinjamuri", getString(R.string.aravind_profile_pic), "Competitive Programming Lead", "Make it correct, make it clear, make it concise, make it fast. In that order.", "Insta", "Linkedin", "GitHub",R.drawable.vinay))
        teamMembersList.add(TeamMemberData("Pallavi Pareek", getString(R.string.pallavi_profile_pic), "Product Design Lead", "Programming is not about typing, it's about thinking.", "Insta", "Linkedin", "GitHub",R.drawable.pallavi))

        ourTeamAdapter = OurTeamAdapter(this, teamMembersList)
        ourTeamRecycler.adapter = ourTeamAdapter
        ourTeamRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ourTeamRecycler.hasFixedSize()

        closeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

    }
}