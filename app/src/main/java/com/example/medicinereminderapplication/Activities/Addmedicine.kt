package com.example.medicinereminderapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.medicinereminderapplication.R

class Addmedicine : AppCompatActivity() {
    lateinit var cardHome:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmedicine)
        cardHome=findViewById(R.id.cardMeds)
    }
}