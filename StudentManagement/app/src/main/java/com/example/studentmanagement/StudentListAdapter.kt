package com.example.studentmanagement

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentListAdapter (private  val context : Activity, private val arrayList : ArrayList<StudentModel>): ArrayAdapter<StudentModel>(context, R.layout.student_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.student_item, null)

        val fullName:TextView = view.findViewById(R.id.fullName)
        val className:TextView = view.findViewById(R.id.className)
        val dateOfBirthAndSex:TextView =view.findViewById(R.id.dateOfBirthAndSex)

        fullName.text = arrayList[position].fullName
        className.text = arrayList[position].className
        dateOfBirthAndSex.text = arrayList[position].dateOfBirth

        return view
    }
}