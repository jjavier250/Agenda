package com.example.agenda.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.activities.provider.Agenda

class AgendaAdapter(private var dataSet: List<Agenda> =listOf(), val onClickListener:(Int)->Unit,
                    private val onclickpapelera:(position:Int)->Unit) :
    RecyclerView.Adapter<AgendaAdapter.MiViewHolder>() {

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtnombre: TextView
        val imagenpapelera: ImageButton


        init {
            // Define click listener for the ViewHolder's View
            txtnombre = view.findViewById(R.id.txtnombre)  // hace referencia el textview que esta en item_agenda

            imagenpapelera=view.findViewById(R.id.imagenpapelera)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MiViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_agenda, viewGroup, false) // item_tarea hace referencia al xml de item_tarea.xml

        return MiViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MiViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener{onClickListener(position)} // capturamos el click del control
        viewHolder.imagenpapelera.setOnClickListener{onclickpapelera(position)} // capturamos el click del papelera


        val agenda:Agenda = dataSet[position]

        // val drawable = viewHolder.textView.context.getDrawable(image)
        // a mi variable viewHolder le asigno al xml de la imagen y del text los valores

        viewHolder.txtnombre.text =  agenda.nombre
        //viewHolder.imageView.setImageDrawable(drawable)




    }


    override fun getItemCount() = dataSet.size

    fun updateData(list:List<Agenda>){
        dataSet=list
        notifyDataSetChanged()
    }

}
