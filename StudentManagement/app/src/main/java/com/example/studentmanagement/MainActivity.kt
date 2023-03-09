package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import java.io.*

class MainActivity : AppCompatActivity() {
    var adapter: StudentListAdapter?= null
    var studentNameList = ArrayList<String>()
    var studentClassList = ArrayList<String>()
    var otherInformation= ArrayList<String>()
    var studentList = StudentListModel()
    val fileName = "studentList.txt"
    var addNewBtn : Button? =null
    var studentListView: ListView?= null

    fun readData(){
        studentNameList.clear()
        studentClassList.clear()
        otherInformation.clear()
        studentList.getStudentList().clear()

        val iStream: InputStream? = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(iStream)
        val reader = BufferedReader(inputStreamReader)
        if (iStream != null) {
            var line: String? = reader.readLine()
            while (line != null) {
                var student = StudentModel.parseStudent(line)
                studentList.addStudent(student)
                studentNameList.add(student.getFullName())
                studentClassList.add(student.getClassName())
                otherInformation.add(student.getDateOfBirth() + " - " + student.getGender())
                line = reader.readLine()
            }
            iStream.close()
        }
    }

    fun updateFile(studentList: StudentListModel){
        val out = OutputStreamWriter(openFileOutput(fileName, 0))
        for (i in studentList.getStudentList().indices){
            out.write(studentList.getStudentList()[i].toString())
            out.write("\n")
        }
        out.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.readData()

        studentListView = findViewById(R.id.studentsListView)
        addNewBtn = findViewById(R.id.addNewBtn)
        addNewBtn?.setOnClickListener{
            val intent = Intent(this, AddNewStudentActivity::class.java)
            intent.putExtra("studentList", studentList)
            startActivityForResult(intent, REQUEST_CODE)
        }
        adapter = StudentListAdapter(this, studentNameList, studentClassList, otherInformation)
        studentListView?.adapter = adapter
        studentListView?.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, StudentInformationActivity::class.java)
            val string: List<String> = otherInformation[i].split("-")
            val oldStudent = StudentModel(studentNameList[i], string[0].trim(), studentClassList[i], string[1].trim())
            intent.putExtra("oldStudent", oldStudent)
            intent.putExtra("studentList", studentList)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }
    val REQUEST_CODE = 1112
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE){
            if (resultCode === Activity.RESULT_OK){
                val reply = data!!.getSerializableExtra("newStudentList") as StudentListModel
                this.updateFile(reply)
                adapter!!.clear()
                this.readData()
                adapter = StudentListAdapter(this, studentNameList, studentClassList, otherInformation)
                studentListView!!.adapter = adapter
            }
        }
    }
}