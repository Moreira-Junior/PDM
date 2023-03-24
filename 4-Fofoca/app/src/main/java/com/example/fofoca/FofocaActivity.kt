package com.example.fofoca

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FofocaActivity : AppCompatActivity(){

    private lateinit var tvFofoca: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbVerdade: RadioButton
    private lateinit var rbMentira: RadioButton
    private lateinit var btnResponder: Button
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var jogo: Jogo
    private var estadoFofoca: Boolean = false
    private var fofoca: Fofoca? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fofoca)

        this.tvFofoca = findViewById(R.id.fofocaTvFofoca)
        this.radioGroup = findViewById(R.id.fofocaradioGroup)
        this.rbVerdade = findViewById(R.id.fofocaRbVerdade)
        this.rbMentira = findViewById(R.id.fofocaRbMentira)
        this.btnResponder = findViewById(R.id.fofocaBtnRespoder)
        this.progressBar1 = findViewById(R.id.fofocaProgressBar1)
        this.progressBar2 = findViewById(R.id.fofocaProgressBar2)
        this.jogo = this.intent.getSerializableExtra("jogo") as Jogo
        if(this.intent.hasExtra("jogo")){
            this.fofoca = this.jogo.getFofoca()
            this.tvFofoca.text = this.fofoca?.getDescricao()
        }
        this.btnResponder.setOnClickListener({responder()})

    }
    private fun progress(){
        Thread{
            while (this.progressBar1.progress < 100){
                this.progressBar1.progress += 1
                Thread.sleep(100)
            }
            this.progressBar2.visibility = View.INVISIBLE
        }.start()
    }

    fun responder(){
        val tempFofoca = this.fofoca
        if(tempFofoca != null){
            verificarAcerto(tempFofoca)
            tempFofoca.setLida()
            this.fofoca = this.jogo.getFofoca()
            this.tvFofoca.text = this.fofoca?.getDescricao() ?: "Acabaram as fofocas!"
        } else{
            this.tvFofoca.text = "Acabaram as fofocas!"
        }
    }

    fun verificarAcerto(fofoca: Fofoca){
        if(fofoca.isVerdadeiro()){
            if(this.rbVerdade.isChecked){
                Toast.makeText(this, "Acertou!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Errou!!!", Toast.LENGTH_SHORT).show()
            }
        } else{
            if(this.rbMentira.isChecked){
                Toast.makeText(this, "Acertou!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Errou!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}