package com.example.lab12



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
    var navIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        val db = DbHandler(this)
        val userList: ArrayList<HashMap<String, String>> = db.getUsers()
        val lv = findViewById<View?>(R.id.user_list) as ListView
        val adapter: ListAdapter = SimpleAdapter(
            this@DetailsActivity,
            userList,
            R.layout.list_row,
            arrayOf<String>("name", "designation", "location"),
            intArrayOf(R.id.name, R.id.designation, R.id.location)
        )
        lv.setAdapter(adapter)
        val back = findViewById<View?>(R.id.btnBack) as Button
        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                navIntent = Intent(this@DetailsActivity, MainActivity::class.java)
                startActivity(navIntent)
            }
        })
    }
}
