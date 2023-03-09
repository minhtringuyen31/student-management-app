package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class ChooseClassActivity : AppCompatActivity() {
    var classList: List<String> = listOf("20KTPM1", "20KTPM2", "20KTPM3")
    var inputClassListView: ListView? = null
    var saveInputClassroomBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_class)

        saveInputClassroomBtn = findViewById(R.id.saveClassroomBtn)
        inputClassListView = findViewById(R.id.inputClassListView)
        var adapter = ClassListAdapter(this, classList)
        var chosenClassroom: String? = null
        inputClassListView!!.adapter = adapter
        inputClassListView!!.setOnItemClickListener { adapterView, view, i, l ->
            chosenClassroom = classList[i]
        }
        saveInputClassroomBtn!!.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("classroom", chosenClassroom)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}