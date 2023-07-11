package com.example.medicinereminderapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapplication.Adapters.MedAdapter
import com.example.medicinereminderapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.FieldPosition

class FetchingActivity : AppCompatActivity() {
    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadinData: TextView
    private lateinit var medList: ArrayList<medsmodel>
    private lateinit var dbRef:  DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)
        
        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadinData = findViewById(R.id.tvLoadingData)
        medList = arrayListOf<medsmodel>()
        getmedicineData()

    }

    private fun getmedicineData() {
        empRecyclerView.visibility = View.GONE
        tvLoadinData.visibility = View.VISIBLE
        dbRef =  FirebaseDatabase.getInstance().getReference("Meds")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot:  DataSnapshot) {
                medList.clear()
                if (snapshot.exists()) {
                    for (medSnap in snapshot.children) {
                        val medData = medSnap.getValue(medsmodel::class.java)
                        medList.add(medData!!)
                    }
                    val mAdapter = MedAdapter(medList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : MedAdapter.OnItemClickListener {

                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity,  Medicinedetails::class.java)
                            intent.putExtra("Medid", medList[position].Medid)
                            intent.putExtra("Description", medList[position].Description)
                            intent.putExtra(" Medname", medList[position].Medname)
                            startActivity(intent)
                        }
                    })
                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadinData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }



}
