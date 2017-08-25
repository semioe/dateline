package semioe.dateline

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.obj.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //如果不存在表objs，则创建表objs
        var dbHelper=DbHelper(this)
        if(!dbHelper.isTableExist("objs")){
            dbHelper.exec("create table objs(_id integer primary key autoincrement,title text,text text,tag text,expire_date text)")
        }
        val intent = Intent(this@MainActivity, InfoActivity::class.java)
        startActivity(intent)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true//不执行父类点击事件
        }
        return super.onKeyDown(keyCode, event)//继续执行父类其他点击事件
    }
}
