package com.example.rgb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class MyAdapter(val lista: MutableList<Cor>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var onItemClick: OnItemCLickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item, parent, false
        )
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyHolder, position: Int) {
        val cor = this.lista.get(position)
        holder.tvNome?.text = cor.getName()
        holder.colorCircle?.setBackgroundColor(cor.getRgb())
    }

    override fun getItemCount(): Int = this.lista.size

    fun add(cor: Cor){
        this.lista.add(cor)
        this.notifyDataSetChanged()
    }

    fun del(position: Int){
        this.lista.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.lista.size)
        this.notifyDataSetChanged()
    }

    fun mov(from: Int, to: Int){
        Collections.swap(this.lista, from, to)
        notifyItemMoved(from, to)
    }

    inner class MyHolder(item: View): ViewHolder(item){
        var tvNome: TextView?
        var colorCircle: ImageView?

        init {
            this.tvNome = item.findViewById(R.id.tvItemNome)
            this.colorCircle = item.findViewById(R.id.color_circle)
            itemView.setOnClickListener{
                this@MyAdapter.onItemClick?.onItemClick(this.adapterPosition)
            }
        }
    }
}
