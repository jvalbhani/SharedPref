package com.test.sharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.test.sharedpref.room.SharedPrefDatabase
import com.test.sharedpref.room.entity.PrefData
import com.test.sharedpref.util.PrefHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database =
            Room.databaseBuilder(applicationContext, SharedPrefDatabase::class.java, "SharedPrefDb")
                .build()
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.actionButton)
        val button2 = findViewById<Button>(R.id.actionButton2)
        val prefHelper = PrefHelper(database.getPrefsDao())

        button.setOnClickListener {
            prefHelper.putPrefData("bool", true)
            prefHelper.putPrefData("int", 1)
            prefHelper.putPrefData("date", Date())
        }

        button2.setOnClickListener {
            GlobalScope.launch {
                val bool = prefHelper.getOrDefault("bool", false)
                val num = prefHelper.getOrDefault("int", 0)
                val date = prefHelper.getOrDefault("date", Date())
                runOnUiThread { textView.text = "$bool \n$num \n$date" }
            }
        }
    }
}