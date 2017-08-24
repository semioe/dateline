package semioe.dateline

import java.text.SimpleDateFormat
import java.util.*

class DateHelper(){
    var simpleFormat = SimpleDateFormat("yyyy-MM-dd")
    private fun now(): String {
        //val simpleFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        var timeString = simpleFormat.format(curDate)//单位秒
        return timeString
    }
    fun days(expire_date:String): Int {
        val expireDate = simpleFormat.format(simpleFormat.parse(expire_date))
        val nowDate = simpleFormat.format(simpleFormat.parse(now()))

        val a = simpleFormat.parse(expireDate).time
        val b = simpleFormat.parse(nowDate).time

        var days = ((a - b) / 1000*60*60*24).toInt()
        return days
    }

}