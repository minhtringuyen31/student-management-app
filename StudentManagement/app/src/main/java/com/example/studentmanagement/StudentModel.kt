package com.example.studentmanagement

import java.io.Serializable

class StudentModel : Serializable {
    private var fullName: String? = null
    private var dateOfBirth: String? = null
    private var className: String? = null
    private var gender: String? = null

    constructor() {
        this.fullName = ""
        this.dateOfBirth = ""
        this.className = ""
        this.gender = ""
    }

    constructor(fullName: String, dateOfBirth: String, className: String, gender: String) {
        this.fullName = fullName
        this.dateOfBirth = dateOfBirth
        this.className = className
        this.gender = gender
    }

    companion object {
        fun parseStudent(string: String): StudentModel {
            var student = StudentModel()
            var contents: List<String>? = null
            contents = string.split("-")
            student!!.setFullName(contents[0])
            student!!.setDateOfBirth(contents[1])
            student!!.setClassName(contents[2])
            student!!.setGender(contents[3])
            return student
        }
    }

    override fun toString(): String {
        return this.fullName + "-" + this.dateOfBirth + "-" + this.className + "-" + this.gender
    }
    fun getFullName(): String {
        return this.fullName.toString()
    }
    
    fun setFullName(fullName: String) {
        this.fullName = fullName
    }

    fun getDateOfBirth(): String {
        return this.dateOfBirth.toString()
    }
    
    fun setDateOfBirth(dateOfBirth: String) {
        this.dateOfBirth = dateOfBirth
    }

    fun getClassName(): String {
        return this.className.toString()
    }

    fun setClassName(className: String) {
        this.className = className
    }

    fun getGender(): String {
        return this.gender.toString()
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun isEqual(student: StudentModel): Boolean {
        if (this.fullName != student.getFullName() || this.dateOfBirth != student.getDateOfBirth() ||
            this.className != student.getClassName() || this.gender != student.getGender()
        )
            return false
        return true
    }
}