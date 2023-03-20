package com.example.arrocha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.arrocha.jogo.Arrocha

class MainActivity : AppCompatActivity() {

    private lateinit var tvSorteio: TextView
    private lateinit var tvMenor: TextView
    private lateinit var tvMaior: TextView
    private lateinit var tvFeedback: TextView
    private lateinit var etChute: EditText
    private lateinit var btnChutar: Button
    private lateinit var arrocha: Arrocha

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvSorteio = findViewById(R.id.tvSorteio)
        this.tvMenor = findViewById(R.id.tvMenor)
        this.tvMaior = findViewById(R.id.tvMaior)
        this.tvFeedback = findViewById(R.id.tvFeedback)
        this.etChute = findViewById(R.id.etChute)
        this.btnChutar = findViewById(R.id.btnChutar)

        this.arrocha = Arrocha(1,100)

        this.tvSorteio.text = this.arrocha.numeroSorteado.toString()
        this.tvMenor.text = this.arrocha.limiteInferior.toString()
        this.tvMaior.text = this.arrocha.limiteSuperior.toString()
        this.tvFeedback.text = this.arrocha.feedback.toString()
    }
}