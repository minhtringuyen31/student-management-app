package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class StudentInformationActivity : AppCompatActivity() {
    var editStudentName: EditText?= null
    var editStudentBrithday: EditText?= null
    var editStudentClass: TextView?=null
    var editStudentGender: RadioGroup?= null
    var saveEditStudentBtn: Button?= null
    var deleteStudentBtn: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_infomation)

        editStudentName = findViewById(R.id.fullNameET)
        editStudentBrithday = findViewById(R.id.dateOfBirthET)
        editStudentClass = findViewById(R.id.classNameTV_edit)
        editStudentGender = findViewById(R.id.editGender)
        saveEditStudentBtn = findViewById(R.id.saveEditStudentBtn)
        deleteStudentBtn = findViewById(R.id.deleteStudentBtn)

        val intent = intent

        var oldStudent = intent.getSerializableExtra("oldStudent") as StudentModel
        var studentList = intent.getSerializableExtra("studentList") as StudentListModel
        val studentName = oldStudent.getFullName()
        val studentBirthday = oldStudent.getDateOfBirth()
        val studentClass = oldStudent.getClassName()
        val studentGender = oldStudent.getGender()
        this.setStudentInformation(studentName,studentBirthday, studentClass, studentGender)

        editStudentClass!!.setOnClickListener {
            val intent = Intent(this, ChooseClassActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        saveEditStudentBtn!!.setOnClickListener {
            var radioID: Int = editStudentGender!!.checkedRadioButtonId
            var currentGender: RadioButton?= findViewById(radioID)
            var newStudent =  StudentModel(editStudentName!!.text.toString(),
                editStudentBrithday!!.text.toString(),
                editStudentClass!!.text.toString(),
                currentGender!!.text.toString())
            studentList.editStudentInfo(oldStudent, newStudent)
            Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show()

            val replyIntent = Intent()
            replyIntent.putExtra("newStudentList", studentList)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
        deleteStudentBtn!!.setOnClickListener {
            studentList.deleteStudent(oldStudent)
            Toast.makeText(this, "Delete successfully!", Toast.LENGTH_SHORT).show()
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
                editStudentClass!!.setError(null)
                editStudentClass!!.setText(reply)
            }
        }
    }
    fun setStudentInformation(name: String, birthday: String, classroom: String, gender:String){
        editStudentName!!.setText(name)
        editStudentBrithday!!.setText(birthday)
        editStudentClass!!.setText(classroom)
        if (gender == "Male")
            editStudentGender!!.check(R.id.maleRB_edit)
        else if (gender == "Female")
            editStudentGender!!.check(R.id.femaleRB_edit)
        else
            editStudentGender!!.check(R.id.otherGenderRB_edit)
    }
}