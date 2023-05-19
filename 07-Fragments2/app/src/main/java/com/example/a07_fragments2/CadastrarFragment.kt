package com.example.a07_fragments2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class CadastrarFragment : Fragment() {

    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cadastrar, container, false)
        etNome = view.findViewById(R.id.etNome)
        etEmail = view.findViewById(R.id.etEmail)
        btnCadastrar = view.findViewById(R.id.btnCadastrar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCadastrar.setOnClickListener {
            cadastrarPessoa()
        }
    }

    private fun cadastrarPessoa() {
        val nome = etNome.text.toString()
        val email = etEmail.text.toString()

        val pessoa = Pessoa(nome, email)
        val mainActivity = activity as MainActivity
        mainActivity.adicionarPessoa(pessoa)
        limparCampos()
    }

    private fun limparCampos() {
        etNome.text.clear()
        etEmail.text.clear()
    }
}