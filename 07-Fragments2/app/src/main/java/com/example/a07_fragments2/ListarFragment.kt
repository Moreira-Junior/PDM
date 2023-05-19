package com.example.a07_fragments2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListarFragment(var listaPessoas: MutableList<Pessoa>) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PessoaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listar, container, false)
        recyclerView = view.findViewById(R.id.rvListar)
        adapter = PessoaAdapter(listaPessoas)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.notifyDataSetChanged()
    }
}