package com.example.lab12


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity1 : AppCompatActivity() {
    var prf: SharedPreferences? = null
    var logoutIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details1)

        val result = findViewById<TextView>(R.id.resultView)
        val btnLogOut = findViewById<Button>(R.id.btnLogOut)

        prf = getSharedPreferences("user_details", MODE_PRIVATE)
        logoutIntent = Intent(this@DetailsActivity1, MainActivity1::class.java)

        result.text = "Hello, " + prf!!.getString("username", null)

        btnLogOut.setOnClickListener {
            val editor = prf!!.edit()
            editor.clear()
            editor.apply()
            startActivity(logoutIntent)
        }
    }
}

