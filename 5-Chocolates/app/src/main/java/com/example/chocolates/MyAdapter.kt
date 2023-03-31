package com.example.chocolates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class MyAdapter(val lista: MutableList<Chocolate>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var onItemClick: OnItemCLickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item, parent, false
        )
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyHolder, position: Int) {
        val chocolate = this.lista.get(position)
        holder.tvNome?.text = chocolate.descricao
    }

    override fun getItemCount(): Int = this.lista.size

    fun del(position: Int){
        this.lista.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.lista.size)
    }

    fun mov(from: Int, to: Int){
        Collections.swap(this.lista, from, to)
        notifyItemMoved(from, to)
    }

    inner class MyHolder(item: View): ViewHolder(item){
        var tvNome: TextView?

        init {
            this.tvNome = item.findViewById(R.id.tvItemNome)
            itemView.setOnClickListener{
                this@MyAdapter.onItemClick?.onItemClick(this.adapterPosition)
            }
        }
    }
}