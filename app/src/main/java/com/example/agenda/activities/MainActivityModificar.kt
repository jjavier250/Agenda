package com.example.agenda.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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


        //flechita para ir para atras va asociada con : +++++   para retroceder
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //++++

        //++++++++++++++++   buscamos el id en BD nos devuelve de tipo agenda  +++++++++++++++++++++
        var id: Int = intent.getIntExtra("ID_TABLA", -1)
        agenda = agendaDao.find(id)!!

        //pintamos lo que nos ha devuelto LA BD en los edittext
        binding.txtnombre.setText(agenda.nombre)
        binding.txtapellidos.setText(agenda.apellidos)
        binding.txtdireccion.setText(agenda.direccion)
        binding.txttelefono.setText(agenda.telefono)
        binding.txtfechanacimiento.setText(agenda.fnacimiento)

        // miramos si es favorito para ponerle el corazón marcado como tal

        // pongo los corazones por defecto del diseño
        binding.imgfavorito.visibility = View.VISIBLE
        binding.imgfavoritoactivado.visibility=View.GONE

        val sesion: Sesion = Sesion(this)
        val personafavorita:String

        // si es el de la sesion el favorito le cambio el corazon
        personafavorita=sesion.leerDatosSesion()
        if(personafavorita==binding.txtnombre.text.toString())
        {
            binding.imgfavorito.visibility = View.GONE
            binding.imgfavoritoactivado.visibility=View.VISIBLE
        }



        // ++++++++++++++++++   boton flotante de llamar y llamada de telefono   ++++++++++++++++++++
        binding.btnllamar.setOnClickListener(){
            val phoneNumber = agenda.telefono // número de teléfono al que deseas llamar
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: $phoneNumber")
            startActivity(intent)
        }


        // ++++++++++++++++++   boton flotante de lmensaje WhatsApp   ++++++++++++++++++++
        binding.btnwass.setOnClickListener(){

            val numeroTelefono = agenda.telefono // Número de teléfono al que deseas enviar el mensaje
            val mensaje = "Hola, ¿cómo estás?" // Mensaje a enviar

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, mensaje)
            intent.putExtra("jid", "$numeroTelefono@s.whatsapp.net") // Agregar el número de teléfono con el prefijo s.whatsapp.net

            // Verificar si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "WhatsApp no está instalado en este dispositivo.", Toast.LENGTH_SHORT).show()
            }

        }

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

        // boton para imagen corazon negro
        binding.imgfavorito.setOnClickListener(){
            //++++++++++++++++++++++++++++++++INSTANCIAR CLASE PARA SESION
            val sesion: Sesion = Sesion(this)


            binding.imgfavorito.visibility = View.GONE
           binding.imgfavoritoactivado.visibility=View.VISIBLE

            //Primero borramos los datos de sesion
            sesion.borrarDatosSesion()

            // guardar datos en sesion, mi persona favorita
            sesion.guardarDatosSesion(binding.txtnombre.text.toString())
            Toast.makeText(this, "Guardado en Favorito:" + sesion.leerDatosSesion(), Toast.LENGTH_LONG).show()
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }

        // boton imagen corazon rojo
        binding.imgfavoritoactivado.setOnClickListener(){

            sesion.borrarDatosSesion()

            binding.imgfavorito.visibility = View.VISIBLE
            binding.imgfavoritoactivado.visibility=View.GONE

            Toast.makeText(this, "Quitado de Favorito", Toast.LENGTH_LONG).show()
        }

    }


    // MENU:  menu_compartir lo preparamos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_compartir, menu)


        return true
    }

    // Flechita de retroceder para atras y opcion de compartir
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Aquí maneja la acción del botón de retroceso
                finish() // Esta es una forma de retroceder la pantalla
                true
            }
            R.id.menu_compartir -> {
                val vintent:Intent =Intent().apply {
                    action=Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,agenda.nombre + " : " + agenda.telefono)
                    type="text/plain"
                }
                val shareintent:Intent=Intent.createChooser(vintent,null)
                startActivity(shareintent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}