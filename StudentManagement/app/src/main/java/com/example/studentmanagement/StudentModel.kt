package com.example.studentmanagement

data class StudentModel( var fullName: String, var className: String, var dateOfBirth: String, var gender: String) :java.io.Serializable {
//    fun getFullName(): String {
//        return this.fullName
//    }
//
//    fun setClassName(className:String){
//        this.className = className
//    }
//
//    fun getClassName():String{
//        return this.className
//    }
//    fun setDateOfBirth(dateOfBirth:String){
//        this.dateOfBirth = dateOfBirth
//    }
//
//    fun getDateOfBirth():String{
//        return this.dateOfBirth
//    }
//    fun setGender(gender:String){
//        this.gender = gender
//    }
//
//    fun getGender():String{
//        return this.gender
//    }
    override fun toString(): String {
        return this.fullName + "-" + this.className + "-" + this.dateOfBirth + "-" + this.gender
    }

}