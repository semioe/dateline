package semioe.dateline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        var dbHelper=DbHelper(this)
        var count_ok=dbHelper.count("select * from objs where date(expire_date)>datetime('now')")
        var count_expire=dbHelper.count("select * from objs where date(expire_date)<=datetime('now')")
        var count_all=count_ok+count_expire

        button_expire.setText("已过期（$count_expire）")
        button_ok.setText("未过期（$count_ok）")
        button_all.setText("全部($count_all)")

        button_all.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InfoActivity, ListActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "all")
            intent.putExtras(bundle)
            startActivity(intent)
        })
        button_ok.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InfoActivity, ListActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "ok")
            intent.putExtras(bundle)
            startActivity(intent)
        })
        button_expire.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InfoActivity, ListActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "expire")
            intent.putExtras(bundle)
            startActivity(intent)
        })

        button_add.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InfoActivity, InputActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "insert")
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return true//不执行父类点击事件
        }
        return super.onKeyDown(keyCode, event)//继续执行父类其他点击事件
    }
}
