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

        var second = ((a - b) / 1000).toInt()
        var minutes = (second/60).toInt()
        var hours = (minutes/60).toInt()
        val days = (hours/24).toInt()


        /*
        while(hours>24){
            hours -= 24;
        }

        while(minutes>60){
            minutes -= 60;
        }

        while(second>60){
            second -= 60;
        }

        var oktime="$days 日 $hours 时 $minutes 分 $second 秒"

        if(days<=0){
            oktime="$hours 时 $minutes 分 $second 秒"
            if(hours<=0){
                oktime="$minutes 分 $second 秒"
                if(minutes<=0){
                    oktime="$second 秒"
                }
            }
        }*/
        return days.toInt()
    }

}