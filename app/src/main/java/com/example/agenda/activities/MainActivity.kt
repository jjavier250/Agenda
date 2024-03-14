package com.example.agenda.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.activities.adapter.AgendaAdapter
import com.example.agenda.activities.provider.Agenda
import com.example.agenda.activities.provider.AgendaDao
import com.example.agenda.activities.utils.Sesion
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var listaAgenda: MutableList<Agenda>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:AgendaAdapter
    lateinit var btnpaninsertar: FloatingActionButton
    lateinit var progressBar:ProgressBar

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
        btnpaninsertar=findViewById(R.id.btnpaninsertar)
        progressBar=findViewById(R.id.progressBar)

        // reloj de tiempo
        progressBar.setVisibility(View.VISIBLE)

        //Recuperamos los click y llamamos a las funciones
        adapter = AgendaAdapter(listaAgenda, { llamarPantallaClick(it) }, {llamarPapeleraClick(it)})

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // reloj de tiempo que se oculte
        progressBar.setVisibility(View.GONE)


        btnpaninsertar.setOnClickListener(){
            val intent = Intent(this,MainActivityinsertar::class.java)
            startActivity(intent)
        }


        // ++++++++   MENU BUSCAR  +++++++++++++++
         fun onCreateOptionsMenu(menu: Menu?): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.main_menu, menu)
            //llamamos a la funcion para buscar
            buscarContacto(menu?.findItem(R.id.menu_buscar))

            return true
        }

    }

    private fun llamarPantallaClick(position: Int) {

        var agenda: Agenda = listaAgenda[position]

        val intent = Intent(this, MainActivityModificar::class.java)
        intent.putExtra("ID_TABLA",agenda.id)
        startActivity(intent)

    }

    private fun llamarPapeleraClick(it: Int) {

        var agenda: Agenda = listaAgenda[it]
        agendaDao.delete(agenda)
        listaAgenda.removeAt(it)
        adapter.notifyDataSetChanged()
    }


    private fun buscarContacto(searchItem: MenuItem?) {
        if (searchItem != null) {
            var searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        //searchSuperheroes(query)
                    }
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })
        }
    }
}