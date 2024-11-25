package com.example.movil

import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageHelper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var intent : Intent
    private lateinit var map : ImageView
    private lateinit var web : ImageView
    private lateinit var llamada : ImageView
    private lateinit var alarma : ImageView
    private lateinit var dados : ImageView
    private lateinit var chistes: ImageView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        map = findViewById(R.id.map)
        web = findViewById(R.id.web)
        alarma = findViewById(R.id.alarma)
        llamada = findViewById(R.id.telefono)
        dados = findViewById(R.id.juego)
        chistes = findViewById(R.id.Chistes)

        map.setOnClickListener{
            val url = "https://www.google.com/maps/place/McGee's+Pub/@40.7657323,-73.9824277,17z/data=!3m1!5s0x89c258f806f55bf7:0xcc95b2f005e4d67f!4m6!3m5!1s0x89c258f8003f64f7:0x108bd3f3b772bd0!8m2!3d40.76484!4d-73.982993!16s%2Fg%2F1v2jdtwq?entry=ttu&g_ep=EgoyMDI0MTAyOS4wIKXMDSoASAFQAw%3D%3D"
            intent = Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse(url)
            startActivity(intent)
        }
        alarma.setOnClickListener{
            ponerAlarma();
        }
        web.setOnClickListener{
            val url = "https://how-i-met-your-mother.fandom.com/wiki/How_I_Met_Your_Mother_Wiki"
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        llamada.setOnClickListener{
            intent = Intent(this, MeterTelefono::class.java)
            startActivity(intent)
        }
        dados.setOnClickListener{
            intent = Intent(this, Dados::class.java);
            startActivity(intent);
        }
        chistes.setOnClickListener{
            intent = Intent(this, Chistes::class.java);
            startActivity(intent)
        }

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun ponerAlarma() {
        val calendar = Calendar.getInstance()
        val minutos = calendar.get(Calendar.MINUTE)
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Prueba aplicacion")
            putExtra(AlarmClock.EXTRA_MINUTES, minutos+2)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        startActivity(intent)
    }
}
