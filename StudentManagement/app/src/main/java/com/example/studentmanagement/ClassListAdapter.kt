package com.example.studentmanagement

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ClassListAdapter(private val context: Activity, private val titles: List<String>, ): ArrayAdapter<String>(context, R.layout.class_list_item, titles) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.class_list_item, null, false)
        val className = rowView.findViewById(R.id.studentNameItem) as TextView
        className.text = titles[position]
        return rowView
    }
}