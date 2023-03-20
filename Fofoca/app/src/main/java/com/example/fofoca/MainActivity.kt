package com.example.fofoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {

    private lateinit var etFofoca: EditText
    private lateinit var rbVerdade: RadioButton
    private  lateinit var rbMentira: RadioButton
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etFofoca = findViewById(R.id.mainEtFofoca)
        this.rbVerdade = findViewById(R.id.mainRbVerdade)
        this.rbMentira = findViewById(R.id.mainRbMentira)
        this.btnSalvar = findViewById(R.id.mainBtnSalvar)

    }
}