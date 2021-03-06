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
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.obj.view.*

class ListActivity : AppCompatActivity() {

    val list = ArrayList<Map<String, Any>>()
    var simpleAdapter: SimpleAdapter? =null
    lateinit var dbHelper:DbHelper
    var sql="select * from objs order by _id desc"
    fun upUI(){
        //获得objs数据
        var c=dbHelper.cursor(sql)
        if(c!=null){
            list.clear()
            while (c.moveToNext()) {
                val _id = c.getInt(c.getColumnIndex("_id"))
                val title = c.getString(c.getColumnIndex("title"))
                val text = c.getString(c.getColumnIndex("text"))
                val tag = c.getString(c.getColumnIndex("tag"))
                val expire_date = c.getString(c.getColumnIndex("expire_date"))
                val map = HashMap<String, Any>()
                map.put("obj_id", _id)
                map.put("obj_title", title)
                map.put("obj_text", text)
                map.put("obj_tag", tag)
                map.put("obj_expire_date", expire_date);
                list.add(map)
                simpleAdapter?.notifyDataSetChanged()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        dbHelper=DbHelper(this)


        var bundle=this.intent.extras
        var action=bundle.getString("action")

        when (action) {
            "expire" ->{
                sql="select * from objs where date(expire_date)<=datetime('now')"
            }
            "ok" ->{
                sql="select * from objs where date(expire_date)>datetime('now')"
            }
            "all" ->{
                sql="select * from objs order by _id desc"
            }
        }

        simpleAdapter = ObjAdapter(baseContext, list,
                R.layout.obj, arrayOf("obj_id","obj_title","obj_text","obj_text","obj_expire_date"), intArrayOf(R.id.obj_id,R.id.obj_title,R.id.obj_text,R.id.obj_tag,R.id.obj_expire_date))
        objs.adapter= simpleAdapter

        upUI()

        //添加物品
        button_add.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ListActivity, InputActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "insert")
            //bundle.putString("_id", "2")
            //bundle.putString("action", "edit")
            intent.putExtras(bundle)
            startActivity(intent)
        })

        objs.onItemClickListener= AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            var _id = view.obj_id.text
            val intent = Intent(this@ListActivity, InputActivity::class.java)
            val bundle = Bundle()
            bundle.putString("_id", _id.toString())
            bundle.putString("action", "edit")
            intent.putExtras(bundle)
            startActivity(intent)
        }
        objs.onItemLongClickListener= AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            var _id = view.obj_id.text
            val alertDialogBuilder = AlertDialog.Builder(this)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.setTitle("删除？")
            var title_input = EditText(this)
            //alertDialog.setView(title_input)
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "删除", DialogInterface.OnClickListener({ dialogInterface: DialogInterface, i: Int ->
                dbHelper.exec("delete from objs where _id='$_id'")
                upUI()
            }))
            alertDialog.show()
            return@OnItemLongClickListener true
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this@ListActivity, InfoActivity::class.java)
            startActivity(intent)
            return true//不执行父类点击事件
        }
        return super.onKeyDown(keyCode, event)//继续执行父类其他点击事件
    }
}
