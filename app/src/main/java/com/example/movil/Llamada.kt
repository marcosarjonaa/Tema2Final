package com.example.movil

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.movil.databinding.ActivityLlamadaBinding
import android.Manifest
import android.net.Uri

class Llamada : AppCompatActivity() {
    private lateinit var numero: String
    private lateinit var confBinding: ActivityLlamadaBinding
    private lateinit var  fichero: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        confBinding = ActivityLlamadaBinding.inflate(layoutInflater)
        setContentView(confBinding.root)
        iniciarPreferencias()
        
        confBinding.llamaya.setOnClickListener{
            permisos()
        }
        confBinding.volver.setOnClickListener{
            intent = Intent(this, MeterTelefono::class.java)
            startActivity(intent)
        }

    }
    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted) {
            call()
        }
        else {
            Toast.makeText(this, "Tienes que aceptar los permisos, ve a configuracion ", Toast.LENGTH_LONG).show()
        }
    }

    private fun permisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(permisosTelf()){
                call()
            } else {
                requestPermissionsLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }

    private fun call() {
        val numero=fichero.getString(getString(R.string.numero), "") ?: ""
        val intent = Intent(Intent.ACTION_CALL).apply { data= Uri.parse("tel:${numero.toString()}") }
        startActivity(intent)
    }

        private fun permisosTelf(): Boolean =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)==
                    PackageManager.PERMISSION_GRANTED


        private fun iniciarPreferencias(){
            val nombre = getString(R.string.fichero)
            this.numero = getString(R.string.numero)
            this.fichero = getSharedPreferences(nombre, MODE_PRIVATE)
        }



}