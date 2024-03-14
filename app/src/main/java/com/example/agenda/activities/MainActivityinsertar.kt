package com.example.agenda.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        //  +++++++++++++    grabar insertar datos ++++++++++++++++++++++++++++
        binding.btngrabatarea.setOnClickListener(){
            // Insertar
            if (!binding.txtnombre.text.toString().isEmpty()) {
                var agenda: Agenda = Agenda(-1, binding.txtnombre.text.toString(), binding.txtapellidos.text.toString(),binding.txtdireccion.text.toString(),binding.txttelefono.text.toString(),binding.txtfechanacimiento.text.toString())
                agendaDao.insert(agenda)

                finish()
            }
            else
            {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            }

        }

        // +++++++++++++++++++++++++++    Boton de cancelar Cancelar   +++++++++++++++++++++++

        binding.btncancelar.setOnClickListener(){
            finish()
        }

        }

    }
