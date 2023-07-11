package com.example.medicinereminderapplication.classes

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medicinereminderapplication.Activities.destination
import com.example.medicinereminderapplication.R

class alarmreceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         val i=Intent(context, destination::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
         val pendingIntent= PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE)
        val notificationManager= NotificationManagerCompat.from(context!!)
        val biulder=NotificationCompat.Builder(context!!,"medicine_reminders").setSmallIcon(R.drawable.baseline_mediation_24).setContentTitle("medicine reminder").setContentText("it is time to take your medicine").setContentIntent(pendingIntent).setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH).setDefaults(NotificationCompat.DEFAULT_ALL)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify( 123 ,biulder.build())

    }
}