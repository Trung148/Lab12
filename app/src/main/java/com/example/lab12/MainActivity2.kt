package com.example.lab12


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainActivity2 : AppCompatActivity() {
    var uname: EditText? = null
    var pwd: EditText? = null
    var saveBtn: Button? = null
    var fstream: FileOutputStream? = null
    var nIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        uname = findViewById<View?>(R.id.txtName) as EditText
        pwd = findViewById<View?>(R.id.txtPwd) as EditText
        saveBtn = findViewById<View?>(R.id.btnSave) as Button
        saveBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val username = uname!!.getText().toString() + "\n"
                val password = pwd!!.getText().toString()
                try {
                    fstream = openFileOutput("user_details", MODE_PRIVATE)
                    fstream!!.write(username.toByteArray())
                    fstream!!.write(password.toByteArray())
                    fstream!!.close()
                    Toast.makeText(
                        getApplicationContext(),
                        "Details Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    nIntent = Intent(this@MainActivity2, DetailsActivity2::class.java)
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
