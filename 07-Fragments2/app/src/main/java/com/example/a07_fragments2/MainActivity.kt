package com.example.a07_fragments2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnMainListar: Button
    private lateinit var btnMainCadastrar: Button
    private lateinit var btnMainSobre: Button
    private lateinit var listarFragment: ListarFragment
    private lateinit var cadastrarFragment: CadastrarFragment
    private lateinit var sobreFragment: SobreFragment
    private lateinit var lista: MutableList<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = mutableListOf(Pessoa("Valeria", "valeria@ifpb.br"))

        this.btnMainListar = findViewById(R.id.btnMainListar)
        this.btnMainCadastrar = findViewById(R.id.btnMainCadastrar)
        this.btnMainSobre = findViewById(R.id.btnMainSobre)

        listarFragment = ListarFragment(this.lista)
        cadastrarFragment = CadastrarFragment()
        sobreFragment = SobreFragment()

        btnMainListar.setOnClickListener {
            mostrarFragmento(listarFragment)
        }

        btnMainCadastrar.setOnClickListener {
            mostrarFragmento(cadastrarFragment)
        }

        btnMainSobre.setOnClickListener {
            mostrarFragmento(sobreFragment)
        }

        mostrarFragmento(listarFragment)
    }

    fun mostrarFragmento(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun adicionarPessoa(pessoa: Pessoa) {
        lista.add(pessoa)
    }
}