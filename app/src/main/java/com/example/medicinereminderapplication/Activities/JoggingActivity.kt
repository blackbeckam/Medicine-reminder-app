package com.example.medicinereminderapplication.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.hardware.SensorEventListener
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.medicinereminderapplication.R

class JoggingActivity : AppCompatActivity(),SensorEventListener{
    var running=false
    var sensorManager: SensorManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogging)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    override fun onResume() {
        running=true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepsSensor==null){
            Toast.makeText(this,"no step counter sensor!", Toast.LENGTH_SHORT).show()
        }else{
            sensorManager?.registerListener(this,stepsSensor,SensorManager.SENSOR_DELAY_UI)
        }
        super.onResume()
    }

    override fun onPause() {
        running=false
        sensorManager?.unregisterListener(this,)
        super.onPause()
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy:Int){
        TODO("not yet implimented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running){
            val steps: TextView =findViewById(R.id.stepsvalue)
            if (event!=null){
                steps.text= ""+event.values[0].toString()
            }
        }
    }

}