package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.OutputStreamWriter

class AddNewStudentActivity : AppCompatActivity() {
    var newStudentName: EditText?= null
    var newStudentDateOfBirth: EditText?=null
    var newStudentClass: TextView?= null
    var newStudentGender: RadioGroup?= null
    var saveStudentBtn: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)
        newStudentName = findViewById(R.id.newFullNameET)
        newStudentDateOfBirth = findViewById(R.id.newDateOfBirthET)
        newStudentGender = findViewById(R.id.newGender)
        newStudentClass = findViewById(R.id.newClassTV)
        saveStudentBtn = findViewById(R.id.saveNewStudentBtn)

        var studentList = intent.getSerializableExtra("studentList") as StudentListModel
        newStudentClass!!.setOnClickListener {
            val intent = Intent(this, ChooseClassActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        saveStudentBtn!!.setOnClickListener {
            var radioID: Int = newStudentGender!!.checkedRadioButtonId
            var currentGender: RadioButton = findViewById(radioID)

            var newStudent =  StudentModel(newStudentName!!.text.toString(),
                newStudentDateOfBirth!!.text.toString(),
                newStudentClass!!.text.toString(),
                currentGender!!.text.toString())
            this.saveToFile(newStudent)
            studentList.addStudent(newStudent)
            val replyIntent = Intent()
            replyIntent.putExtra("newStudentList", studentList)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
    val REQUEST_CODE = 1111
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE){
            if (resultCode === Activity.RESULT_OK){
                val reply = data!!.getStringExtra("classroom")
                newStudentClass!!.setError(null)
                newStudentClass!!.setText(reply)
            }
        }
    }
    fun saveToFile(newStudent: StudentModel) {
        try {
            val fileName = "studentList.txt"
            // File will be in "/data/data/$packageName/files/"
            val out = OutputStreamWriter(openFileOutput(fileName, MODE_APPEND))
            out.write(newStudent.toString())
            out.write("\n")
            out.close()
            this.clearInput()
            Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
        } catch (t: Throwable) {
            Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun clearInput(){
        newStudentName!!.setText("")
        newStudentDateOfBirth!!.setText("")
        newStudentClass!!.setText("")
        newStudentGender!!.check(R.id.maleRB)
    }
}