package com.example.sorteio

import android.util.Log

class Sorteio {

    var lista: MutableList<String> = mutableListOf()

    fun adicionar(string: String){
        this.lista.add(string)
    }

    fun sortear(): String{
        return this.lista.random()
    }
}