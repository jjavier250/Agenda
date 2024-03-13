package com.example.agenda.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.activities.adapter.AgendaAdapter
import com.example.agenda.activities.provider.Agenda
import com.example.agenda.activities.provider.AgendaDao

class MainActivity : AppCompatActivity() {

    lateinit var listaAgenda: MutableList<Agenda>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:AgendaAdapter
    val agendaDao = AgendaDao(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        listaAgenda = agendaDao.findAll().toMutableList()

        inicializar()
    }

    private fun inicializar() {

        recyclerView = findViewById(R.id.recyclerView)

        adapter = AgendaAdapter(listaAgenda, { llamarPantallaClick(it) }, {llamarPapeleraClick(it)})
    }

    private fun llamarPantallaClick(position: Int) {

        var agenda: Agenda = listaAgenda[position]

        //val intent = Intent(this, MainActivityModificar::class.java)
       // intent.putExtra("ID_TABLA",agenda.id)

        //startActivity(intent)

    }

    private fun llamarPapeleraClick(it: Int) {

        var agenda: Agenda = listaAgenda[it]
        agendaDao.delete(agenda)
        listaAgenda.removeAt(it)
        adapter.notifyDataSetChanged()
    }
}