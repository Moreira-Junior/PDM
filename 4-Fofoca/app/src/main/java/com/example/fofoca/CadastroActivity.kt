package com.example.fofoca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener

class CadastroActivity : AppCompatActivity() {

    private lateinit var etFofoca: EditText
    private lateinit var rbVerdade: RadioButton
    private lateinit var rbMentira: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnSalvar: Button
    private lateinit var btnVoltar: Button
    private lateinit var jogo: Jogo
    private var estadoFofoca: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.etFofoca = findViewById(R.id.cadastroEtFofoca)
        this.rbVerdade = findViewById(R.id.cadastroRbVerdade)
        this.rbMentira = findViewById(R.id.cadastroRbMentira)
        this.btnSalvar = findViewById(R.id.cadastroBtnSalvar)
        this.btnVoltar = findViewById(R.id.cadastroBtnVoltar)
        this.radioGroup = findViewById(R.id.cadastroRadioGroup)
        this.jogo = this.intent.getSerializableExtra("jogo") as Jogo

        this.radioGroup.setOnCheckedChangeListener(RadioGroupChange())
        this.btnSalvar.setOnClickListener(ClickBotaoSalvar())
        this.btnVoltar.setOnClickListener({ voltar() })
    }

    inner class ClickBotaoSalvar: OnClickListener{
        override fun onClick(v: View?) {
//            var estadoFofoca = false
//            if(this@CadastroActivity.rbVerdade.isChecked()){
//                estadoFofoca = true
//            }
            var fofoca = Fofoca(this@CadastroActivity.etFofoca.text.toString(), this@CadastroActivity.estadoFofoca)
            this@CadastroActivity.jogo.adicionar(fofoca)
            Log.i("APP_INFO", this@CadastroActivity.jogo.getFofocas().size.toString())
            limparCadastro()
        }
    }

    inner class RadioGroupChange : OnCheckedChangeListener{
        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            if(checkedId == R.id.cadastroRbVerdade){
                this@CadastroActivity.estadoFofoca = true
            } else {
                this@CadastroActivity.estadoFofoca = false
            }
        }
    }

    fun limparCadastro(){
        this.etFofoca.text.clear()
        this.radioGroup.clearCheck()
    }

    fun voltar(){
        val intent = Intent()
        intent.putExtra("jogo", this.jogo)
        setResult(RESULT_OK, intent)
        finish()
    }
}