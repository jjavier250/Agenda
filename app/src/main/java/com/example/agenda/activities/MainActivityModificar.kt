package com.example.agenda.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.activities.provider.Agenda
import com.example.agenda.activities.provider.AgendaDao
import com.example.agenda.activities.utils.Sesion
import com.example.agenda.databinding.ActivityMainActivityinsertarBinding
import com.example.agenda.databinding.ActivityMainModificarBinding



class MainActivityModificar : AppCompatActivity() {


    lateinit var agenda: Agenda
    private lateinit var binding:ActivityMainModificarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val agendaDao = AgendaDao(this)

        binding= ActivityMainModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //++++++++++++++++   buscamos el id en BD nos devuelve de tipo agenda  +++++++++++++++++++++
        var id: Int = intent.getIntExtra("ID_TABLA", -1)
        agenda = agendaDao.find(id)!!

        //pintamos lo que nos ha devuelto LA BD en los edittext
        binding.txtnombre.setText(agenda.nombre)
        binding.txtapellidos.setText(agenda.apellidos)
        binding.txtdireccion.setText(agenda.direccion)
        binding.txttelefono.setText(agenda.telefono)
        binding.txtfechanacimiento.setText(agenda.fnacimiento)




        // +++++++++++++++++++++++++++    Boton de cancelar Cancelar   +++++++++++++++++++++++

        binding.btncancelar.setOnClickListener(){
            finish()
        }


        // ++++++++++++++++++++++  Boton de grabar modificación ++++++++++++++++++

        binding.btnmodificar.setOnClickListener(){
            agenda.nombre = binding.txtnombre.text.toString()
            agenda.apellidos=binding.txtapellidos.text.toString()
            agenda.direccion=binding.txtdireccion.text.toString()
            agenda.telefono=binding.txttelefono.text.toString()
            agenda.fnacimiento=binding.txtfechanacimiento.text.toString()

            agendaDao.update(agenda)

            // fin parte de actualizar
            finish()
        }

        binding.imgfavorito.setOnClickListener(){
            //++++++++++++++++++++++++++++++++INSTANCIAR CLASE PARA SESION
            val sesion: Sesion = Sesion(this)


            //binding.imgfavorito.visibility (View.GONE)
           // binding.imgfavoritoactivado.visibility(View.VISIBLE)

            // guardar datos en sesion, mi persona favorita
            sesion.guardarDatosSesion(binding.txtnombre.text.toString())
            Toast.makeText(this, "Guardado en Favorito:" + sesion.leerDatosSesion(), Toast.LENGTH_LONG).show()
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }

    }
}