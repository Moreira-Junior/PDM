package com.example.chocolates

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvCesta: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private var cesta: Cesta

    init {
        this.cesta = Cesta()
        this.cesta.add(Chocolate("Primeiro", 10))
        this.cesta.add(Chocolate("Segundo", 20))
        this.cesta.add(Chocolate("Terceiro", 30))
        this.cesta.add(Chocolate("Quarto", 40))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.rvCesta = findViewById(R.id.rvMainCesta)
        this.fabAdd = findViewById(R.id.fabMainAdd)

        this.rvCesta.adapter = MyAdapter(this.cesta.get())
        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvCesta)

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val chocolate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("CHOCOLATE", Chocolate::class.java)
                } else {
                    it.data?.getSerializableExtra("CHOCOLATE")
                } as Chocolate
                this.cesta.add(chocolate)
                (this.rvCesta.adapter as MyAdapter).notifyDataSetChanged()
                Log.i("APP_CHOCOLATES", this.cesta.get().toString())
            }
        }

        this.fabAdd.setOnClickListener({
            val intent = Intent(this, FormActivity::class.java)
            formResult.launch(intent)
        })

    }

    inner class OnItemClick: OnItemCLickRecyclerView{
        override fun onItemClick(position: Int) {

        }

    }

    inner class OnItemLongClick: OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long,
        ): Boolean {
            this@MainActivity.cesta.del(position)
            (this@MainActivity.rvCesta.adapter as ArrayAdapter<Chocolate>).notifyDataSetChanged()
            return true
        }
    }

    inner class OnSwipe : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.START or ItemTouchHelper.END
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            (this@MainActivity.rvCesta.adapter as MyAdapter).mov(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (this@MainActivity.rvCesta.adapter as MyAdapter).del(viewHolder.adapterPosition)
        }

    }
}