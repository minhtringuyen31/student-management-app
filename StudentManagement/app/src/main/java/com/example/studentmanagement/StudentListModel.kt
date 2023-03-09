package com.example.studentmanagement

import java.io.Serializable

class StudentListModel: Serializable {
    private var studentList = ArrayList<StudentModel>()
    fun getStudentList(): ArrayList<StudentModel> {
        return this.studentList
    }
    fun setStudentList(studentList: ArrayList<StudentModel>){
        this.studentList = studentList
    }
    fun addStudent(student: StudentModel){
        studentList.add(student)
    }
    fun deleteStudent(student: StudentModel){
        for (i in studentList.indices){
            if (studentList[i].isEqual(student)){
                studentList.removeAt(i)
                return
            }
        }
    }
    fun editStudentInfo(oldStudent: StudentModel, newStudent: StudentModel){
        for (i in studentList.indices){
            if (studentList[i].isEqual(oldStudent)){
                studentList[i].setFullName(newStudent.getFullName())
                studentList[i].setDateOfBirth(newStudent.getDateOfBirth())
                studentList[i].setGender(newStudent.getGender())
                studentList[i].setClassName(newStudent.getClassName())
                return
            }
        }
    }
}