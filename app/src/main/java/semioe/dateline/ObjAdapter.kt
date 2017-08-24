package semioe.dateline

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.obj.view.*

class ObjAdapter(context: Context, items: ArrayList<Map<String, Any>>, resource: Int, from: Array<String>, to: IntArray) : SimpleAdapter(context, items, resource, from, to) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view=super.getView(position, convertView, parent)
        var days=DateHelper().days(view.obj_expire_date.text.toString())
        var absDays=Math.abs(days)
        if (days<0){
            view.obj_title.setTextColor(android.graphics.Color.RED)
            view.info.setText("已过期 $absDays 天")
        }else if (days>0){
            view.info.setText("还有 $absDays 天过期")
        }
        else if (days===0){
            view.obj_title.setTextColor(android.graphics.Color.RED)
            view.info.setText("今日过期")
        }
        return view
    }
}
