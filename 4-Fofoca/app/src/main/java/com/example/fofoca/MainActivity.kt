package com.example.fofoca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var btnJogar: Button
    private lateinit var btnCadastrar: Button
    private lateinit var jogo: Jogo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.jogo = Jogo("jogao")
        this.btnJogar = findViewById(R.id.mainBtnJogar)
        this.btnCadastrar = findViewById(R.id.mainBtnCadastrar)

        val chamarOutraTela = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                var jogoEditado = it.data?.getSerializableExtra("jogo")
                this.jogo = jogoEditado as Jogo
                Toast.makeText(this, "Deu certo", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnJogar.setOnClickListener {
            val intent = Intent(this, FofocaActivity::class.java)
            intent.putExtra("jogo", this.jogo)
            chamarOutraTela.launch(intent)
        }

        this.btnCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("jogo", this.jogo)
            chamarOutraTela.launch(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i("APP_INFO", this@MainActivity.jogo.nome)
    }
}