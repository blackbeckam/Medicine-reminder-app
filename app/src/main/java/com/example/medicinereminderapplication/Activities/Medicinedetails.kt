package com.example.medicinereminderapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.medicinereminderapplication.R
import com.google.firebase.database.FirebaseDatabase

class Medicinedetails : AppCompatActivity() {

    private lateinit var Button:Button
    private lateinit var   button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicinedetails)

        button2=findViewById(R.id.button2)
        Button=findViewById(R.id.button3)

         button2.setOnClickListener {
             val intent=Intent(this,alarmactivity::class.java)
             startActivity(intent)
         }
        Button.setOnClickListener {
            deleteRecord(intent.getStringExtra("Medid").toString())
        }
    }
    private fun deleteRecord(id: String){
        val dbRef= FirebaseDatabase.getInstance().getReference("Meds").child(id)
        val  mTask=dbRef.removeValue()

        mTask.addOnSuccessListener{
            Toast.makeText(this,"medicine data deleted",Toast.LENGTH_LONG).show()

            val intent=Intent(this,FetchingActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this,"failed to delete",Toast.LENGTH_LONG).show()
        }
    }

}