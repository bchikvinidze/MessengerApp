package com.nchikvinidze.messengerapp.data

data class MessageItem(var timemillis : Long, var time : String, var sent : Boolean, var from : String, var to : String, var text : String){
    constructor() : this(0,"00:00",true,"", "", "")
}
