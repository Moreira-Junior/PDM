package com.example.fofoca

class Fofoca (private var descricao: String, private var verdadeiro: Boolean,
              private var lida: Boolean = false) : java.io.Serializable{

    fun getDescricao(): String{
        return this.descricao
    }

    fun isVerdadeiro(): Boolean{
        return this.verdadeiro
    }

    fun isLida(): Boolean{
        return this.lida
    }

    fun setLida(){
        this.lida = true
    }
}