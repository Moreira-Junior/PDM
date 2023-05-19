package com.example.a07_fragments2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class SobreFragment : Fragment() {
    private lateinit var listViewInfos: ListView
    private lateinit var infos: ArrayList<Info>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sobre, container, false)

        listViewInfos = view.findViewById(R.id.listViewInfos)
        infos = createInfoList()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            infos.map { it.titulo }
        )

        listViewInfos.adapter = adapter
        listViewInfos.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedInfo = infos[position]
                detalhesInfo(selectedInfo)
            }
        return view
    }

    private fun createInfoList(): ArrayList<Info> {
        val infoList = ArrayList<Info>()
        infoList.add(
            Info(
                getString(R.string.titulo_sobre),
                "${getString(R.string.descricao_sobre)}\n${getString(R.string.descricao2_sobre)}"
            )
        )
        infoList.add(
            Info(
                getString(R.string.insercao_sobre),
                getString(R.string.insercao1_sobre)
            )
        )
        infoList.add(
            Info(
                getString(R.string.quando_sobre),
                "${getString(R.string.quando_sobre1)}\n${getString(R.string.quando_sobre2)}"
            )
        )
        infoList.add(
            Info(
                getString(R.string.vantagens_sobre),
                "${getString(R.string.vantagem1_sobre)}\n${getString(R.string.vantagem2_sobre)}"
            )
        )
        infoList.add(
            Info(
                getString(R.string.desvantagens_sobre),
                "${getString(R.string.desvantagem1_sobre)}\n${getString(R.string.desvantagem2_sobre)}"
            )
        )
        infoList.add(
            Info(
                getString(R.string.aplicacoes_sobre),
                "${getString(R.string.aplicacao1_sobre)}\n${getString(R.string.aplicacao2_sobre)}\n" +
                        "${getString(R.string.aplicacao3_sobre)}\n" +
                        "${getString(R.string.aplicacao4_sobre)}"
            )
        )
        return infoList
    }

    private fun detalhesInfo(info: Info) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(info.titulo)
        builder.setMessage(info.mensagem)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }

    data class Info(val titulo: String, val mensagem: String)
}
