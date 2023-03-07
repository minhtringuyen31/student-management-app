package com.example.studentmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import com.google.android.material.button.MaterialButtonToggleGroup

class StudentInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_information)
        var fullName:EditText? =null
        var className:EditText?=null
        var dateOfBirth:EditText?=null

        var deleteBtn:Button?=null
        var saveBtn:Button?=null




        fullName=findViewById(R.id.detailFullName)
        className=findViewById(R.id.detailClassName)
        dateOfBirth=findViewById(R.id.detailDateOfBirth)

        deleteBtn = findViewById(R.id.detailDeleteBtn)
        saveBtn= findViewById(R.id.detailSaveBtn)

        className!!.setOnClickListener {
            val intent = Intent(this, ChooseClass::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }



        val student = intent.getSerializableExtra("student") as StudentModel

        fullName?.text = Editable.Factory.getInstance().newEditable(student.fullName)
        className?.text = Editable.Factory.getInstance().newEditable(student.className)
        dateOfBirth?.text = Editable.Factory.getInstance().newEditable(student.dateOfBirth)

    }
    val REQUEST_CODE = 1112
}