package com.gmrit.gdsc.models

data class TeamMemberData(
    var memName: String = "",
    var memPhotUrl: Int = 0, // change it to String once connected to Backend
    var memRole: String = "",
    var memDesc: String = "",
    var memInstaUrl: String = "",
    var memLinkedinUrl: String = "",
    var memGitUrl: String = "",
    var coverImagerUrl: Int = 0
)
