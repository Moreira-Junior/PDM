package com.example.rgb

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var rvMainCores: RecyclerView
    private lateinit var fabMainAdd: FloatingActionButton
    private var cores: MutableList<Cor>

    init {
        this.cores = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvMainCores = findViewById(R.id.rvMainCores)
        this.fabMainAdd = findViewById(R.id.fabMainAdd)

        val adapter = MyAdapter(this.cores)
        adapter.onItemClick = OnItemClick()
        this.rvMainCores.adapter = adapter

        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvMainCores)

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val cor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("COR", Cor::class.java)
                } else {
                    it.data?.getSerializableExtra("COR")
                } as Cor
                (this.rvMainCores.adapter as MyAdapter).add(cor)
            }
        }

        this.fabMainAdd.setOnClickListener({
            val intent = Intent()
            intent.action = "NOVACOR"
            formResult.launch(intent)
        })

    }

    inner class OnItemClick: OnItemCLickRecyclerView{
        override fun onItemClick(position: Int) {
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.custom_color_dialog, null)
            val redSeekBar = view.findViewById<SeekBar>(R.id.red_seekbar)
            val greenSeekBar = view.findViewById<SeekBar>(R.id.green_seekbar)
            val blueSeekBar = view.findViewById<SeekBar>(R.id.blue_seekbar)
            val llEditColor = view.findViewById<LinearLayout>(R.id.llEditColor)
            val nameTextView = view.findViewById<TextView>(R.id.name_textview)

            val colorSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    val redValue = redSeekBar.progress
                    val greenValue = greenSeekBar.progress
                    val blueValue = blueSeekBar.progress
                    val color = Color.rgb(redValue, greenValue, blueValue)
                    llEditColor.setBackgroundColor(color)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Não é necessário fazer nada aqui
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Não é necessário fazer nada aqui
                }
            }

            redSeekBar.setOnSeekBarChangeListener(colorSeekBarChangeListener)
            greenSeekBar.setOnSeekBarChangeListener(colorSeekBarChangeListener)
            blueSeekBar.setOnSeekBarChangeListener(colorSeekBarChangeListener)

            val builder = AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Edite a Cor!")
                setView(view)
                val cor = this@MainActivity.cores[position]
                redSeekBar.progress = cor.getRed()
                greenSeekBar.progress = cor.getGreen()
                blueSeekBar.progress = cor.getBlue()
                nameTextView.text = cor.getName()
                setPositiveButton("Salvar") { _, _ ->
                    val redValue = redSeekBar.progress
                    val greenValue = greenSeekBar.progress
                    val blueValue = blueSeekBar.progress

                    cor.editColor(redValue, greenValue, blueValue)
                    (this@MainActivity.rvMainCores.adapter as MyAdapter).notifyDataSetChanged()
                }
                setNegativeButton("Cancelar", null)
            }
            builder.create().show()

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
            (this@MainActivity.rvMainCores.adapter as MyAdapter).mov(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(32 == direction){
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Excluir cor")
                builder.setMessage("Tem certeza que deseja excluir esta cor?")
                builder.setPositiveButton("Excluir") { dialog, which ->
                    (this@MainActivity.rvMainCores.adapter as MyAdapter).del(viewHolder.adapterPosition)
                }
                builder.setNegativeButton("Cancelar") { dialog, which ->
                    (this@MainActivity.rvMainCores.adapter as MyAdapter).notifyItemChanged(viewHolder.adapterPosition)
                }
                builder.show()
            } else if (16 == direction){
                val cor = (this@MainActivity.rvMainCores.adapter as MyAdapter).lista[viewHolder.adapterPosition]
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_SUBJECT, "Estou enviando uma cor em RGB!")
                    putExtra(Intent.EXTRA_TEXT, "Nome hexadecimal: ${cor.getName()} red: ${cor.getRed()} green: ${cor.getGreen()} blue: ${cor.getBlue()}")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "Gmail não instalado.", Toast.LENGTH_SHORT).show()
                }
                (this@MainActivity.rvMainCores.adapter as MyAdapter).notifyDataSetChanged()
            }
        }
    }

}
