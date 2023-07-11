package com.example.medicinereminderapplication.Activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import com.example.medicinereminderapplication.R
import com.example.medicinereminderapplication.classes.alarmreceiver
import com.example.medicinereminderapplication.databinding.ActivityAlarmactivityBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar


class alarmactivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmactivityBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var  alarmManager: AlarmManager
    private lateinit var   pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmactivity)
        createnotificattionchannel()
        binding= ActivityAlarmactivityBinding.inflate(layoutInflater)

        binding.selecttime.setOnClickListener {
            showtimePicker()

        }
        binding.cancelalarm.setOnClickListener {
            cancelalarm()
        }
        binding.setalarm.setOnClickListener {
        setalarm()
        }

    }

    private fun cancelalarm() {
        alarmManager=getSystemService(ALARM_SERVICE)as AlarmManager
        val intent=Intent(this,alarmreceiver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
         alarmManager.cancel(pendingIntent)
        Toast.makeText(this,"alarm cancelled",Toast.LENGTH_LONG).show()

    }

    private fun setalarm() {
        alarmManager=getSystemService(ALARM_SERVICE)as AlarmManager
        val intent=Intent(this,alarmreceiver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )

    }

    private fun showtimePicker() {
    picker=MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(0).setTitleText("select alarm time ").build()
        picker.show(supportFragmentManager,"medicine_reminder")
        picker.addOnPositiveButtonClickListener{
            if(picker.hour>12){
                binding.selectedtime.text=String.format("%02d",picker.hour-12)+" : "+String.format("%02d",picker.minute)+"PM"
            }
            else{

                binding.selectedtime.text=String.format("%02d",picker.hour)+" : "+String.format("%02d",picker.minute)+"AM"
            }
            val calendar= Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY]=picker.hour
            calendar[Calendar.MINUTE]=picker.minute
            calendar[Calendar.HOUR_OF_DAY]=picker.hour
            calendar[Calendar.SECOND]= 0
            calendar[Calendar.MILLISECOND]=0

        }



    }

    private fun createnotificattionchannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channelName="MedicineReminders"

            val importance= NotificationManager.IMPORTANCE_HIGH
            val channelId="medicine_reminders"
            val description=" channel for medicine reminder"
            val channel=
                NotificationChannel(channelId,channelName,importance)
            channel.description=description
            channel.enableVibration(true)
            val notificationManager=  getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}