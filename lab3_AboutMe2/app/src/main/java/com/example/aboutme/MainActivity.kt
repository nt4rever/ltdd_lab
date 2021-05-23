package com.example.aboutme

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var btnDone: Button
    lateinit var editTextName: EditText
    lateinit var textName: TextView
    lateinit var layout1: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDone = findViewById(R.id.btn_done)
        editTextName = findViewById(R.id.editTextNickName)
        textName = findViewById(R.id.textName)
        layout1 = findViewById(R.id.layout1)
        btnDone.setOnClickListener {
            var str: String = editTextName.text.toString()
            if (str != "") {
                textName.text = str
                layout1.visibility = View.GONE
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            } else {
                Toast.makeText(this, "Please fill your nickname!", Toast.LENGTH_SHORT).show()
            }

        }
        textName.setOnClickListener {
            layout1.visibility = View.VISIBLE
            editTextName.requestFocus()
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editTextName, 0)
        }

    }

}