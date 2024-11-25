package com.example.movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import com.example.movil.databinding.ActivityChistesBinding
import java.util.Locale
import kotlin.random.Random

class Chistes : AppCompatActivity() {
    private lateinit var bindingChistes : ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private val TOUCH_MAX_TIME = 500
    private var touchLastTime: Long = 0
    private var touchNumber = 0
    private lateinit var handler: Handler
    private lateinit var chistesNormales: List<String>
    private lateinit var chistesNegros: List<String>
    val MYTAG = "LOGCAT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingChistes = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(bindingChistes.root)
        bindingChistes.volver.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        chistesNormales = listOf("Papá, papá. ¿Qué se siente al tener un hijo tan requete guapo? No sé hijo, pregúntale a tu abuelo",
            "¿Por qué Jaimito va con traje y corbata al oculista? Porque va a la graduación de sus gafas",
            "Mamá. mi marido se fue ayer a comprar arroz y aún no ha vuelto. No sé que hacer. Ay hija, no te preocupes. Ponte a hacer spaghetti",
            "Mamá, ¡estoy embarazada! ¡Ay hija!, ¿Pero dónde tenias la cabeza? Entre el volante y el equipo de música",
            "Si car es coche y men es hombre, entonces mi tía Carmen es un transformer.")
        chistesNegros = listOf(
            "Le tengo malas noticias don José. ¿Cuáles son doctor?. Usted tiene que dejar de masturbarse. ¡Oh dios, ¿pero por qué?! Porque estamos en consulta",
            "¿En qué se diferencia un cura del acné? En que el acné espera a que tengas 12 años",
            "¿Por qué la mayoría de fantasmas son mujeres? Porque aún muertas quieren seguir jodiendo",
            "La nuera llama a la suegra. Querida suegra, ¿me puede decir quien debe limpiar al hijo cuando caga? Mamá o papá. Siempre la mamá querida. " +
            "Entonces venga que su hijo esta borracho y cagado",
            "¡Oye Puri! ¿Y tú marido? En el jardín. ¡No lo veo! Tienes que cavar un poco",
        )
        configureTextToSpeech()
        initEvent()
    }

    private fun initHander() {
        val chiste: String
        if (bindingChistes.checkBox.isChecked){
            chiste = chistesNegros[Random.nextInt(0, chistesNegros.size)]
        }else {
            chiste = chistesNormales[Random.nextInt(0, chistesNormales.size)]
        }
        handler = Handler(Looper.getMainLooper())
        bindingChistes.checkBox.visibility= View.INVISIBLE
        bindingChistes.progressBar.visibility = View.VISIBLE
        bindingChistes.button.visibility = View.INVISIBLE
        Thread{
            val palabras = chiste.split(" ").size * 500
            Thread.sleep(palabras.toLong())
            handler.post{
                bindingChistes.progressBar.visibility = View.GONE
                bindingChistes.checkBox.visibility= View.VISIBLE
                bindingChistes.button.visibility = View.VISIBLE
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
            }
        }.start()
    }

    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it != TextToSpeech.ERROR){
                textToSpeech.language = Locale.getDefault()
                Log.i(MYTAG,"Sin problemas en la configuración TextToSpeech")
            }else{
                Log.i(MYTAG,"Error en la configuración TextToSpeech")
            }
        })
    }

    private fun initEvent() {
        bindingChistes.button.setOnClickListener{
            val chiste: String
            if (bindingChistes.checkBox.isChecked){
                chiste = chistesNegros[Random.nextInt(chistesNegros.size)]
            }else {
                chiste = chistesNormales[Random.nextInt(chistesNormales.size)]
            }
            val currentTime = System.currentTimeMillis()
            if (currentTime - touchLastTime < TOUCH_MAX_TIME){
                executorDoubleTouch(chiste)
                initHander()
                Log.i(MYTAG,"Escuchamos el chiste")
            }
            else{
                Log.i(MYTAG,"Hemos pulsado 1 vez.")
                speakMeDescription("Toca dos veces para escuchar un chiste")
            }
            touchLastTime = currentTime
        }
    }

    private fun speakMeDescription(s: String) {
        Log.i(MYTAG,"Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executorDoubleTouch(chiste: String) {
        speakMeDescription(chiste)
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }
}