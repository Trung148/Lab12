package com.example.lab12


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity1 : AppCompatActivity() {
    var uname: EditText? = null
    var pwd: EditText? = null
    var loginBtn: Button? = null
    var pref: SharedPreferences? = null
    var logoutIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        uname = findViewById<View?>(R.id.txtName) as EditText
        pwd = findViewById<View?>(R.id.txtPwd) as EditText
        loginBtn = findViewById<View?>(R.id.btnLogin) as Button
        pref = getSharedPreferences("user_details", MODE_PRIVATE)
        logoutIntent = Intent(this@MainActivity1, DetailsActivity1::class.java)
        if (pref!!.contains("username") && pref!!.contains("password")) {
            startActivity(logoutIntent)
        }
        loginBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val username = uname!!.getText().toString()
                val password = pwd!!.getText().toString()
                if (username == "admin" && password == "admin") {
                    val editor = pref!!.edit()
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.commit()
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(logoutIntent)
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        "Credentials are not valid",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
