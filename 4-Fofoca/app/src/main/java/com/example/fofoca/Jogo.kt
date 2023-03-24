package com.example.fofoca

import android.util.Log

class Jogo (var nome: String) : java.io.Serializable{
    private var fofocas: MutableList<Fofoca> = mutableListOf()

    fun adicionar(fofoca: Fofoca){
        this.fofocas.add(fofoca)
        Log.i("APP_INFO", "Fofoca adicionada com sucesso")
    }

    fun getFofoca(): Fofoca? {
        for (fofoca in fofocas){
            if(!fofoca.isLida())
                return fofoca
        }
        return null
    }

    fun getFofocas(): MutableList<Fofoca>{
        return this.fofocas
    }
}