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

// funciones lambda que que paso el click de la fila y de la papelera. Al onclick lo puedo llamar como me de la gana: en este caso onclickpapelera
class AgendaAdapter(private var dataSet: List<Agenda> =listOf(), val onClickListener:(Int)->Unit,
                    private val onclickpapelera:(position:Int)->Unit) :
    RecyclerView.Adapter<AgendaAdapter.MiViewHolder>() {

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtnombre: TextView
        val txttelefono: TextView
        val imagenpapelera: ImageButton


        init {
            // hace referencia el textview que esta en item_agenda saco 3 columnas pero mas abajo pinto 2 de la BD
            txtnombre = view.findViewById(R.id.txtnombre)
            txttelefono=view.findViewById(R.id.txttelefono)
            imagenpapelera=view.findViewById(R.id.imagenpapelera)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MiViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_agenda, viewGroup, false) // item_tarea hace referencia al xml de item_tarea.xml

        return MiViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MiViewHolder, position: Int) {

        // En el MainActivity recogemos las llamdas así :   adapter = AgendaAdapter(listaAgenda, { llamarPantallaClick(it) }, {llamarPapeleraClick(it)})
        viewHolder.itemView.setOnClickListener{onClickListener(position)} // capturamos el click del control
        viewHolder.imagenpapelera.setOnClickListener{onclickpapelera(position)} // capturamos el click del papelera


        val agenda:Agenda = dataSet[position]

        // los 2 textview del layaut item_agenda le asigno los valores

        viewHolder.txtnombre.text =  agenda.nombre
        viewHolder.txttelefono.text=agenda.telefono


    }


    override fun getItemCount() = dataSet.size

    fun updateData(list:List<Agenda>){
        dataSet=list
        notifyDataSetChanged()
    }

}
