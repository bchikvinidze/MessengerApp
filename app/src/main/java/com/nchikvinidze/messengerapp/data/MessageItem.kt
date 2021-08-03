package com.nchikvinidze.messengerapp.data

import java.util.*

data class MessageItem(val timemillis : Long, val time : String, val sent : Boolean, val from : String, val to : String, val text : String){
    constructor() : this(0,"00:00",true,"", "", "")
}
