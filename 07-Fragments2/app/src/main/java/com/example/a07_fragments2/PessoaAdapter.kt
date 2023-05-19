package com.example.a07_fragments2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PessoaAdapter(private val listaPessoas: List<Pessoa>) : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pessoa, parent, false)
        return PessoaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        val pessoa = listaPessoas[position]
        holder.tvNome?.text = pessoa.nome
    }

    override fun getItemCount(): Int {
        return listaPessoas.size
    }

    inner class PessoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView?

        init {
            this.tvNome = itemView.findViewById(R.id.tvNome)
        }
    }
}