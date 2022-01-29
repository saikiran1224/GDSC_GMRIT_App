package com.gmrit.gdsc.models

import com.google.firebase.messaging.RemoteMessage

data class NotificationData(
    var title: String = "",
    var message: String = "",
    var timestamp: String = ""
)