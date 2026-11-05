package com.example.brujuladigital

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager

    private var acelerometro: Sensor? = null
    private var magnetometro: Sensor? = null

    private var gravedad = FloatArray(3)
    private var campoMagnetico = FloatArray(3)

    private var tieneGravedad = false
    private var tieneGMagnetismo = false

    private lateinit var imgBrujula: ImageView
    private lateinit var txtGrados: TextView
    private lateinit var txtDireccion: TextView
    private lateinit var txtSensor: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        imgBrujula = findViewById(R.id.imgBrujula)
        txtGrados = findViewById(R.id.txtGrados)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtSensor = findViewById(R.id.txtSensor)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        if (acelerometro == null || magnetometro == null) {
            txtSensor.text = "Este dispositivo no tiene los sensores necesarios para esta función"

            txtGrados.text = "--º"
            txtDireccion.text = "No disponible"
        } else {
            txtSensor.text = "Sesores OK"
        }


    }


    override fun onResume() {
        super.onResume()
        acelerometro?.let { sensor ->
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
        }
        magnetometro?.let { sensor ->
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )

        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

    }

    override fun onSensorChange(event: SensorEvent){
        when(event.sensor.type){
            Sensor.TYPE_ACCELEROMETER -> {

            }

            Sensor.TYPE_MAGNETIC_FIELD -> {

            }

            if (tieneGravedad && tieneGMagnetismo) calcularOrientacion()



        }
    }
}