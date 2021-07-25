package com.nchikvinidze.messengerapp.data

import java.util.*

data class MessageItem(val timemillis : Long, val timestamp : String, val sent : Boolean, val from : String, val to : String, val text : String)
