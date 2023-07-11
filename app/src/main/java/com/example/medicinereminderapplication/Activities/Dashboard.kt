package com.example.medicinereminderapplication.Activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.medicinereminderapplication.R
import kotlinx.coroutines.NonCancellable

class Dashboard : AppCompatActivity() {

    lateinit var  addMedicine: CardView
    lateinit var   buyMedicine: CardView
    lateinit var   Mymedicine: CardView
    lateinit var calldoctor:CardView
    lateinit var About:CardView
    lateinit var logout: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

         addMedicine=findViewById(R.id.cardHome)
         buyMedicine=findViewById(R.id.cardSettings)
        Mymedicine=findViewById(R.id.cardChat)
        logout=findViewById(R.id.cardLogout)
         calldoctor=findViewById(R.id.cardWidget)
        About=findViewById(R.id.cardProfile)
         addMedicine.setOnClickListener {
            val intent= Intent(this,  InsertionActivity::class.java)
            startActivity(intent)
        }
        Mymedicine.setOnClickListener{
            val intent= Intent(this,  FetchingActivity::class.java)
            startActivity(intent)
        }

      calldoctor.setOnClickListener {
          val dialIntent=Intent(Intent.ACTION_DIAL)
          dialIntent.data= Uri.parse("tel:"+"0794973356")
          startActivity(dialIntent)

      }
        About.setOnClickListener{
            val intent= Intent(this,  about1::class.java)
            startActivity(intent)
        }
        buyMedicine.setOnClickListener {
            val intent= Intent(this, buymedicne::class.java)
            startActivity(intent)

        }
        logout.setOnClickListener {
            var box= AlertDialog.Builder(this)
            box.setMessage("DO YOU WANT TO CLOSE THIS APP?")
            box.setNegativeButton("PROCEED", DialogInterface.OnClickListener { dialog, id -> finish() })
            box.setPositiveButton("CANCEL",
                DialogInterface.OnClickListener { dialog, id -> NonCancellable.cancel() })

            var alert=box.create()
            alert.setTitle("Medicine reminder app")
            alert.show()
        }}}


