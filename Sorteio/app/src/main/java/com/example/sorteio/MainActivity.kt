package com.example.sorteio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private lateinit var etEntrada: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var btnSortear: Button
    private lateinit var sorteio: Sorteio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvResultado = findViewById(R.id.tvResultado)
        this.tvResultado.text = ""
        this.etEntrada = findViewById(R.id.etEntrada)
        this.btnCadastrar = findViewById(R.id.btnCadastrar)
        this.btnSortear = findViewById(R.id.btnSortear)
        this.sorteio = Sorteio()

        this.btnSortear.setOnClickListener(ClickBotaoSortear())
        this.btnCadastrar.setOnClickListener(ClickBotaoCadastrar())
    }

    inner class ClickBotaoCadastrar: View.OnClickListener {
        override fun onClick(v: View?) {
            this@MainActivity.sorteio.adicionar(this@MainActivity.etEntrada.text.toString())
            this@MainActivity.etEntrada.text.clear()
        }
    }

    inner class ClickBotaoSortear: OnClickListener{
        override fun onClick(v: View?) {
            this@MainActivity.tvResultado.text = this@MainActivity.sorteio.sortear()
        }
    }
}