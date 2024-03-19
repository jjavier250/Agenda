package com.example.agenda.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.activities.provider.Agenda
import com.example.agenda.activities.provider.AgendaDao
import com.example.agenda.databinding.ActivityMainActivityinsertarBinding

class MainActivityinsertar : AppCompatActivity() {

    private lateinit var binding:ActivityMainActivityinsertarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var agendaDao = AgendaDao(this)

        binding = ActivityMainActivityinsertarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //flechita para ir para atras va asociada con : +++++   para retroceder
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //++++


        //  +++++++++++++    grabar insertar datos ++++++++++++++++++++++++++++
        binding.btngrabatarea.setOnClickListener(){
            var pasa:Boolean=false
            // Insertar
            if (!binding.txtnombre.text.toString().isEmpty()) {
                pasa=true
            }
            else
            {
                pasa=false
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            }

            if (!binding.txttelefono.text.toString().isEmpty()){
                pasa=true
            }
            else{
                pasa=false
                Toast.makeText(this, "El telefono no puede estar vacío", Toast.LENGTH_SHORT).show()
            }

            if(binding.txttelefono.text.length<9){
                pasa=false
                Toast.makeText(this, "El telefono no coincide con su longitud", Toast.LENGTH_SHORT).show()
            }
            else
            {
                pasa=true
            }

            if(pasa==true){
                var agenda: Agenda = Agenda(-1, binding.txtnombre.text.toString(), binding.txtapellidos.text.toString(),binding.txtdireccion.text.toString(),binding.txttelefono.text.toString(),binding.txtfechanacimiento.text.toString(),binding.txtcorreo.text.toString())
                agendaDao.insert(agenda)

                finish()

            }


        }

        // +++++++++++++++++++++++++++    Boton de cancelar Cancelar   +++++++++++++++++++++++

        binding.btncancelar.setOnClickListener(){
            finish()
        }

        }
    // Flechita de retroceder para atras y opcion de compartir
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Aquí maneja la acción del botón de retroceso
                finish() // Esta es una forma de retroceder la pantalla
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    }
