package semioe.dateline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = ArrayList<Map<String, Any>>()
        var simpleAdapter: Adapter? =null
        var dbHelper=DbHelper(this)
        //如果不存在表objs，则创建表objs
        if(!dbHelper.isTableExist("objs")){
            dbHelper.exec("create table objs(_id integer primary key autoincrement,title text,text text,tag text,expire_date text)")
        }

        simpleAdapter = ObjAdapter(baseContext, list,
                R.layout.obj, arrayOf("obj_id","obj_title","obj_text","obj_text","obj_expire_date"), intArrayOf(R.id.obj_id,R.id.obj_title,R.id.obj_text,R.id.obj_tag,R.id.obj_expire_date))
        objs.adapter=simpleAdapter

        //获得objs数据
        var c=dbHelper.cursor("select * from objs order by _id desc")
        if(c!=null){
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

        //添加物品
        button_add.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            val bundle = Bundle()
            bundle.putString("action", "insert")
            //bundle.putString("_id", "2")
            //bundle.putString("action", "edit")
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
}
