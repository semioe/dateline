package semioe.dateline

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.obj.view.*

class ObjAdapter(context: Context, items: ArrayList<Map<String, Any>>, resource: Int, from: Array<String>, to: IntArray) : SimpleAdapter(context, items, resource, from, to) {
    var resource=resource
    var context=context
    var to=to
    var items=items
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view=super.getView(position, convertView, parent)
        var days=DateHelper().days(view.obj_expire_date.text.toString())

        view.info.setText(days.toString())
        
        if (days<=0){
            view.obj_title.setTextColor(android.graphics.Color.RED)
        }else{

        }
        return view
    }
}
