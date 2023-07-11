package com.example.medicinereminderapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medicinereminderapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.System.err

class InsertionActivity : AppCompatActivity() {
    private lateinit var etMedicinename: EditText
    private lateinit var   etDescription: EditText
    private lateinit var   btnSaveData:  Button
    private lateinit var    dbRef:   DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
        etMedicinename=findViewById(R.id.etMedName)

        etDescription=findViewById(R.id.etMedsideeffects)
        btnSaveData=findViewById(R.id.btnSave)
        dbRef =  FirebaseDatabase.getInstance().getReference("Meds")
        btnSaveData.setOnClickListener {
            savemedicineData()
        }


    }
    private fun  savemedicineData() {
        val Medname = etMedicinename.text.toString()
        val  Description =  etDescription.text.toString()
        if ( Description.isBlank()) {
            etDescription.error = "please enter the description"
        }

        if (Medname.isBlank()) {
            etMedicinename.error = "please enter Medicinename"
        }


        val Medid = dbRef.push().key!!
        val  medicine= medsmodel(Medname,Description, Medid)
        dbRef.child(Medid).setValue(medicine).addOnCompleteListener {
            Toast.makeText(this, "medicine inserted successfully", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "failed to insert medicine", Toast.LENGTH_SHORT).show()
        }
    }}