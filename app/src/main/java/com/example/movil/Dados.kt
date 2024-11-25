package com.example.movil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movil.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

    class Dados : AppCompatActivity() {
        private var suma: Int = 0
        private lateinit var bindingDados: ActivityDadosBinding
        private var paloSelec: String = "Poker"

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            bindingDados = ActivityDadosBinding.inflate(layoutInflater)
            setContentView(bindingDados.root)
            val spinner: Spinner = bindingDados.spinner
            val palos = listOf("Póker", "Española")
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                palos
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bindingDados.spinner.adapter = adapter
            bindingDados.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    paloSelec = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
            bindingDados.volver.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

            bindingDados.txtResultado.visibility = View.INVISIBLE
            bindingDados.textView3.visibility = View.INVISIBLE
            bindingDados.imagenpoker.visibility = View.INVISIBLE
            bindingDados.imagenpoker2.visibility = View.INVISIBLE

            bindingDados.imageButton.setOnClickListener {
                bindingDados.txtResultado.visibility = View.VISIBLE
                bindingDados.textView3.visibility = View.VISIBLE
                bindingDados.imagenpoker.visibility = View.VISIBLE
                bindingDados.imagenpoker2.visibility = View.VISIBLE
                val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion_vaso)
                bindingDados.imageButton.startAnimation(animacion)
                partida()
            }
        }

        private fun partida() {
            scheduleRun()
        }

        private fun scheduleRun() {
            val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
            val msc = 1000
            for (i in 1..3) {
                schedulerExecutor.schedule(
                    {
                        lanzaeldado()
                    },
                    msc * i.toLong(),
                    TimeUnit.MILLISECONDS
                )
            }
            schedulerExecutor.shutdown()
        }

        private fun lanzaeldado() {
            val numeros = Array(3) { Random.nextInt(1, 6) }
            val imagenes: Array<ImageView> = arrayOf(
                bindingDados.imageviewDado1,
                bindingDados.imageviewDado2,
                bindingDados.imageviewDado3
            )
            val palo = paloSelec
            suma = numeros.sum()
            for (i in imagenes.indices) {
                seleccionarImagen(imagenes[i], numeros[i], suma, palo)
            }
        }

        private fun seleccionarImagen(image: ImageView, numero: Int, suma: Int, palo: String) {
            when (numero) {
                1 -> image.setImageResource(R.drawable.dado1)
                2 -> image.setImageResource(R.drawable.dado2)
                3 -> image.setImageResource(R.drawable.dado3)
                4 -> image.setImageResource(R.drawable.dado4)
                5 -> image.setImageResource(R.drawable.dado5)
                6 -> image.setImageResource(R.drawable.dado6)
            }
            if (palo == "Española") {
                if (suma <= 10) {
                    when (suma) {
                        3 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp3)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "3";
                        }
                        4 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp4)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "4";
                        }
                        5 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp5)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "5";
                        }
                        6 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp6)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "6";
                        }
                        7 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp7)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "7";
                        }
                        8 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp8)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "8";
                        }
                        9 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp9)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "9";
                        }
                        10 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "10";
                        }
                    }
                } else if (suma > 10 && suma <= 18) {
                    when (suma) {
                        11 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp1)
                            bindingDados.txtResultado.text= "11";
                        }
                        12 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp2)
                            bindingDados.txtResultado.text= "12";
                        }
                        13 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp3)
                            bindingDados.txtResultado.text= "13";
                        }
                        14 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp4)
                            bindingDados.txtResultado.text= "14";
                        }
                        15 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp5)
                            bindingDados.txtResultado.text= "15";
                        }
                        16 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp6)
                            bindingDados.txtResultado.text= "16";
                        }
                        17 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp7)
                            bindingDados.txtResultado.text= "17";
                        }
                        18 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.esp10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.esp8)
                            bindingDados.txtResultado.text= "18";
                        }
                    }
                } else {
                    Toast.makeText(this, "Algo ha fallado", Toast.LENGTH_LONG).show()
                }
            } else {
                if (suma <= 10) {
                    when (suma) {
                        3 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker3)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "3";
                        }
                        4 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker4)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "4";
                        }
                        5 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker5)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "5";
                        }
                        6 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker6)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "6";
                        }
                        7 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker7)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "7";
                        }
                        8 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker8)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "8";
                        }
                        9 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker9)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "9";
                        }
                        10 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility = View.INVISIBLE
                            bindingDados.txtResultado.text= "10";
                        }
                    }
                } else if (suma >10 && suma <= 18) {
                    when (suma) {
                        11 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.pokeras)
                            bindingDados.txtResultado.text= "11";
                        }
                        12 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker2)
                            bindingDados.txtResultado.text= "12";
                        }
                        13 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker3)
                            bindingDados.txtResultado.text= "13";
                        }
                        14 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker4)
                            bindingDados.txtResultado.text= "14";
                        }
                        15 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker5)
                            bindingDados.txtResultado.text= "15";
                        }
                        16 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker6)
                            bindingDados.txtResultado.text= "16";
                        }
                        17 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker7)
                            bindingDados.txtResultado.text= "17";
                        }
                        18 -> {
                            bindingDados.imagenpoker.setImageResource(R.drawable.poker10)
                            bindingDados.imagenpoker2.visibility= View.VISIBLE;
                            bindingDados.imagenpoker2.setImageResource(R.drawable.poker8)
                            bindingDados.txtResultado.text= "18";
                        }
                    }
                } else {
                    Toast.makeText(this, "Algo ha fallado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
