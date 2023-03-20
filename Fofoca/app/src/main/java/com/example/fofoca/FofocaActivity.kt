package com.example.fofoca

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FofocaActivity : AppCompatActivity(){

    private lateinit var tvFofoca: TextView
    private lateinit var rbVerdade: RadioButton
    private lateinit var rbMentira: RadioButton
    private lateinit var btnResponder: Button
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fofoca)

        this.tvFofoca = findViewById(R.id.fofocaTvFofoca)
        this.rbVerdade = findViewById(R.id.fofocaRbVerdade)
        this.rbMentira = findViewById(R.id.fofocaRbMentira)
        this.btnResponder = findViewById(R.id.fofocaBtnRespoder)
        this.progressBar1 = findViewById(R.id.fofocaProgressBar1)
        this.progressBar2 = findViewById(R.id.fofocaProgressBar2)
    }
}