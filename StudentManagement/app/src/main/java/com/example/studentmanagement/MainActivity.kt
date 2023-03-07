package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    var studentList = StudentListModel()
    @Throws(IOException::class)
    fun readData() {
        val iStream: InputStream = resources.openRawResource(R.raw.student_list)
        val reader = BufferedReader(InputStreamReader(iStream))
        if (iStream != null){
            var line: String? = reader.readLine()
            while (line!=null){
                var detail: List<String>?=null
                detail = line.split("-")
                var student = StudentModel(detail[0], detail[1], detail[2], detail[3])
                studentList?.addStudent(student)
                line = reader.readLine()
            }
        }
        iStream.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.readData()

        val addNewBtn: Button = findViewById(R.id.addNewBtn)
        val studentListView:ListView = findViewById(R.id.studentListView)

        addNewBtn.setOnClickListener{
            val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)
        }

        var adapter:StudentListAdapter = StudentListAdapter(this, studentList.getStudentList())
        studentListView?.adapter = adapter
        studentListView?.setOnItemClickListener{parent, view, position, id ->
            val fullName =studentList.getStudentList()[position].fullName
            val className  =studentList.getStudentList()[position].className
            val dateOfBirth = studentList.getStudentList()[position].dateOfBirth
            val gender = studentList.getStudentList()[position].gender

            val student = StudentModel(fullName, className, dateOfBirth, gender)

            val intent = Intent(this, StudentInformationActivity::class.java)
            intent.putExtra("student", student)
            intent.putExtra("fullName", fullName)

            startActivityForResult(intent, REQUEST_CODE)
        }

    }
    val REQUEST_CODE = 1111
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode === REQUEST_CODE){
//            if (resultCode === Activity.RESULT_OK){
//                val reply = data!!.getStringExtra()
//            }
//        }
//    }

    fun updateInformation(){

    }


}