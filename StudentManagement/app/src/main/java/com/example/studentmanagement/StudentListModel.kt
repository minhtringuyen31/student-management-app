package com.example.studentmanagement

class StudentListModel {
    private var studentList = ArrayList<StudentModel>()
    fun getStudentList():ArrayList<StudentModel>{
        return this.studentList
    }
    fun addStudent(student: StudentModel){
        studentList.add(student)
    }

}