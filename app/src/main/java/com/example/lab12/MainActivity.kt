package com.example.lab12

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab12.DbHandler

class MainActivity : AppCompatActivity() {
    var name: EditText? = null
    var loc: EditText? = null
    var desig: EditText? = null
    var saveBtn: Button? = null
    var navIntent: Intent? = null // Renamed from 'intent'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.txtName) as EditText
        loc = findViewById(R.id.txtLocation) as EditText
        desig = findViewById(R.id.txtDesignation) as EditText
        saveBtn = findViewById(R.id.btnSave) as Button
        saveBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val username = name!!.getText().toString() + "\n"
                val location = loc!!.getText().toString()
                val designation = desig!!.getText().toString()
                val dbHandler = DbHandler(this@MainActivity)
                dbHandler.insertUserDetails(username, location, designation)
                navIntent = Intent(this@MainActivity, DetailsActivity::class.java)
                startActivity(navIntent)
                Toast.makeText(
                    applicationContext,
                    "Details Inserted Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}