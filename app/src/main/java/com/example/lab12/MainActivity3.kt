package com.example.lab12

import android.Manifest

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainActivity3 : AppCompatActivity() {
    var uname: EditText? = null
    var pwd: EditText? = null
    var saveBtn: Button? = null
    var fstream: FileOutputStream? = null
    var nIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        uname = findViewById<View?>(R.id.txtName) as EditText
        pwd = findViewById<View?>(R.id.txtPwd) as EditText
        saveBtn = findViewById<View?>(R.id.btnSave) as Button
        saveBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val username = uname!!.getText().toString() + "\n"
                val password = pwd!!.getText().toString()
                try {
                    ActivityCompat.requestPermissions(
                        this@MainActivity3,
                        arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                        23
                    )
                    val folder =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val myFile = File(folder, "user_details")
                    fstream = FileOutputStream(myFile)
                    fstream!!.write(username.toByteArray())
                    fstream!!.write(password.toByteArray())
                    fstream!!.close()
                    Toast.makeText(
                        getApplicationContext(),
                        "Details Saved in " + myFile.getAbsolutePath(),
                        Toast.LENGTH_SHORT
                    ).show()
                    nIntent = Intent(this@MainActivity3, DetailsActivity3::class.java)
                    startActivity(nIntent)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }
}
