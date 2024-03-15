package com.example.agenda.activities.utils

import android.content.Context
import android.content.SharedPreferences

class Sesion(context: Context) {


    companion object {
        const val PERSONA_FAVORITA = "PERSONA_FAVORITA"
    }
    private var sharedPref: SharedPreferences? = null

    init {
        sharedPref = context.getSharedPreferences("mi_sesion", Context.MODE_PRIVATE)
    }

    fun guardarDatosSesion(contacto:String){
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.putString(PERSONA_FAVORITA, contacto) //PERSONA_FAVORITA es la clave puedo guardar mas info con distintas claves
            editor.apply()
        }

    }
    fun borrarDatosSesion(){
        sharedPref?.edit()?.remove(PERSONA_FAVORITA)?.apply()
    }
    fun leerDatosSesion():String{
        return sharedPref?.getString(PERSONA_FAVORITA, null).toString()
    }
}