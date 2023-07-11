package com.example.medicinereminderapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.medicinereminderapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class signup : AppCompatActivity() {
    lateinit var myemail:  EditText
    lateinit var mypassword: EditText
    lateinit var myconfpassword:EditText
    lateinit var signup: Button
    lateinit var mylogin: TextView
    private lateinit var auth: FirebaseAuth //creating firebase authentication object
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        myemail=findViewById(R.id.Email)
        mypassword=findViewById(R.id.Password)
        myconfpassword=findViewById(R.id.confirmPassword)
        signup=findViewById(R.id.btn)
        mylogin=findViewById(R.id.login)
        auth= Firebase.auth
        mylogin.setOnClickListener {
            val intent= Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }

        signup.setOnClickListener {
            SignUpUser()

        }
    }

    private fun SignUpUser(){
        val email=myemail.text.toString()
        val pass=mypassword.text.toString()
        val confirmpass=myconfpassword.text.toString()
        if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()){
            Toast.makeText(this,"Please Email and password cant be blank", Toast.LENGTH_LONG).show()
            return

        }  else if (pass != confirmpass){
            Toast.makeText(this,"Password do not match", Toast.LENGTH_LONG).show()
            return

        }

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this,"Signed up successfully", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this,"Failed to create", Toast.LENGTH_LONG).show()
            }

        }

    }
}