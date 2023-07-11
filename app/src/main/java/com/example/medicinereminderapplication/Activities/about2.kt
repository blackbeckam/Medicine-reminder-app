package com.example.medicinereminderapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.example.medicinereminderapplication.R

class about2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about2)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent= Intent(this,about3::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}