package com.example.movil

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movil.databinding.ActivityMetllaBinding

class MeterTelefono : AppCompatActivity() {
    private lateinit var numero: String
    private lateinit var confBinding: ActivityMetllaBinding
    private lateinit var  fichero: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        confBinding = ActivityMetllaBinding.inflate(layoutInflater)
        setContentView(confBinding.root)
        iniciarPreferencias()
        start()
    }

    private fun iniciarPreferencias(){
        val nombre = getString(R.string.fichero)
        this.numero = getString(R.string.numero)
        this.fichero = getSharedPreferences(nombre, MODE_PRIVATE)
    }

    private fun start(){
        confBinding.confirmar.setOnClickListener{
            val telefono = confBinding.telefono.text.toString()
            if(telefono.isEmpty()){
                Toast.makeText(this, "Introduce un numero", Toast.LENGTH_LONG).show()
            }else {
                if(buenNumero(telefono)){
                    val editar = fichero.edit()
                    editar.putString(numero, telefono)
                    editar.apply()
                    startActivityM(telefono)
                }else{
                    Toast.makeText(this, "El numero es incorrecto", Toast.LENGTH_LONG).show()
                }
            }
        }

        confBinding.volver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buenNumero(numero: String): Boolean {
        val regex = Regex("^[679]\\d{8}$")
        return regex.matches(numero)
    }

    private fun startActivityM(telefono: String){
        val intent = Intent(this, Llamada::class.java)
        intent.apply {
            putExtra("Telefono", telefono)
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val ret = intent.getBooleanExtra("back", false)
        if(ret){
            confBinding.telefono.setText("")
            intent.removeExtra("back")
        }
    }
}

