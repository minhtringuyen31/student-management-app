package com.example.studentmanagement

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentListAdapter(private val context: Activity, private val names: List<String>, private val classes: List<String>, private val otherInfo: List<String>, ): ArrayAdapter<String>(context, R.layout.student_list_item, names) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.student_list_item,null, false)
        val fullName = rowView.findViewById(R.id.studentNameItem) as TextView
        val className = rowView.findViewById(R.id.studentClassItem) as TextView
        val otherInfor = rowView.findViewById(R.id.studentDateOfBirthAndGenderItem) as TextView
        fullName.text = names[position]
        className.text = classes[position]
        otherInfor.text = otherInfo[position]
        return rowView
    }
}