package semioe.dateline

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        var dbHelper=DbHelper(this)
        var bundle=this.intent.extras
        var action=bundle.getString("action")
        when (action) {
            "insert" ->{
                //新增

            }
            "edit"->{
                //修改
                var _id=bundle.getString("_id")
            }
        }

        button_select_date.setOnClickListener(View.OnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            val alertDialog = alertDialogBuilder.create()
            //alertDialog.setTitle("选择日期")
            var dp= DatePicker(this)
            alertDialog.setView(dp)
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"确定", DialogInterface.OnClickListener({ dialogInterface: DialogInterface, i: Int ->
                val year = dp.year
                val month = dp.month+1
                val day = dp.dayOfMonth
                var str_year=year.toString()
                var str_month=month.toString()
                var str_day=day.toString()
                if(month<10){
                    str_month="0$str_month"
                }
                if(day<10){
                    str_day="0$str_day"
                }
                var expire_time="$str_year-$str_month-$str_day"
                input_obj_expire_date.setText(expire_time)
            }))
            alertDialog.show()
        })

        button_save.setOnClickListener(View.OnClickListener {
            var obj_title=input_obj_title.text
            var obj_expire_date=input_obj_expire_date.text
            var obj_text=input_obj_text.text
            var obj_tag=input_obj_tag.text
            dbHelper.exec("insert into objs(title,text,tag,expire_date)values('$obj_title','$obj_text','$obj_tag','$obj_expire_date')")
            val intent = Intent(this@InputActivity, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "insert")
            //bundle.putString("_id", "2")
            //bundle.putString("action", "edit")
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
}
