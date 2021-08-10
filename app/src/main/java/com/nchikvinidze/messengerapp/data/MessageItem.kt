package com.nchikvinidze.messengerapp.data

import java.net.URL

data class MessageItem(var timemillis : Long, var time : String, var sent : Boolean, var from : String, var to : String, var text : String, var url: URL? = null){
    constructor() : this(0,"00:00",true,"", "", "")
}
