package com.example.agenda.activities.provider

import com.example.agenda.activities.utils.DatabaseManager


class Agenda (var id: Int, var nombre: String,var apellidos:String ,var direccion:String,var telefono:String,var fnacimiento:String) {

    companion object {
        const val TABLE_NAME = "agenda"
        const val COLUMN_NAME_NOMBRE = "nombre"
        const val COLUMN_NAME_APELLIDOS = "apellidos"
        const val COLUMN_NAME_DIRECCION = "direccion"
        const val COLUMN_NAME_TELEFONO = "telefono"
        const val COLUMN_NAME_FNACIMIENTO = "fnacimiento"


        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_NOMBRE,
            COLUMN_NAME_APELLIDOS,
            COLUMN_NAME_DIRECCION,
            COLUMN_NAME_TELEFONO,
            COLUMN_NAME_FNACIMIENTO
        )
    }

    //Para imprimir
    override fun toString(): String {
        return "imprimir"
    }
}
