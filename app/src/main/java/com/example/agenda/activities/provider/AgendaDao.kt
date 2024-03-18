package com.example.agenda.activities.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.agenda.activities.utils.DatabaseManager

class AgendaDao (context:Context){
    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(agenda: Agenda): Agenda {
        val db = databaseManager.writableDatabase

        var values = ContentValues()
        values.put(Agenda.COLUMN_NAME_NOMBRE, agenda.nombre)
        values.put(Agenda.COLUMN_NAME_APELLIDOS, agenda.apellidos)
        values.put(Agenda.COLUMN_NAME_DIRECCION, agenda.direccion)
        values.put(Agenda.COLUMN_NAME_TELEFONO,agenda.telefono)
        values.put(Agenda.COLUMN_NAME_FNACIMIENTO,agenda.fnacimiento)
        values.put(Agenda.COLUMN_NAME_CORREO,agenda.correo)

        var newRowId = db.insert(Agenda.TABLE_NAME, null, values)
        Log.i("DATABASE", "nuevo id: $newRowId")

        db.close()

        agenda.id= newRowId.toInt()
        return agenda
    }

    fun update(agenda: Agenda) {
        val db = databaseManager.writableDatabase

        var values = ContentValues()
        values.put(Agenda.COLUMN_NAME_NOMBRE, agenda.nombre)
        values.put(Agenda.COLUMN_NAME_APELLIDOS, agenda.apellidos)
        values.put(Agenda.COLUMN_NAME_DIRECCION, agenda.direccion)
        values.put(Agenda.COLUMN_NAME_TELEFONO,agenda.telefono)
        values.put(Agenda.COLUMN_NAME_FNACIMIENTO,agenda.fnacimiento)
        values.put(Agenda.COLUMN_NAME_CORREO,agenda.correo)


        var updatedRows = db.update(Agenda.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${agenda.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(agenda: Agenda) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Agenda.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${agenda.id}", null)
        Log.i("DATABASE", "Delete lineas: $deletedRows")

        db.close()
    }

    fun deleteAll() {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Agenda.TABLE_NAME, null, null)
        Log.i("DATABASE", "Todo borrado")

        db.close()
    }



    @SuppressLint("Range")
    fun find(id: Int): Agenda? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Agenda.TABLE_NAME,
            Agenda.COLUMN_NAMES,
            "${DatabaseManager.COLUMN_NAME_ID} = $id",
            null,
            null,
            null,
            null
        )

        var agenda: Agenda? = null

        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val nombre = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_NOMBRE))
            val apellidos = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_APELLIDOS))
            val direccion = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_DIRECCION))
            val telefono = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_TELEFONO))
            val fnacimiento=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_FNACIMIENTO))
            val correo=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_CORREO))

            agenda = Agenda(id, nombre,apellidos ,direccion,telefono,fnacimiento,correo)
        }

        cursor.close()
        db.close()

        return agenda
    }

    @SuppressLint("Range")
    fun findAll(): List<Agenda> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Agenda.TABLE_NAME,
            Agenda.COLUMN_NAMES,
            null,
            null,
            null,
            null,
            null
        )

        var list: MutableList<Agenda> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val nombre = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_NOMBRE))
            val apellidos = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_APELLIDOS))
            val direccion = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_DIRECCION))
            val telefono = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_TELEFONO))
            val fnacimiento=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_FNACIMIENTO))
            val correo=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_CORREO))

            val agenda: Agenda = Agenda(id, nombre, apellidos,direccion,telefono,fnacimiento,correo)
            list.add(agenda)
        }

        cursor.close()
        db.close()

        return list
    }


    @SuppressLint("Range")
    fun findAlllike(nombre:String): List<Agenda> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Agenda.TABLE_NAME,
            Agenda.COLUMN_NAMES,
            "${Agenda.COLUMN_NAME_NOMBRE} like '%$nombre%'",
            null,
            null,
            null,
            null
        )

        var list: MutableList<Agenda> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val nombre = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_NOMBRE))
            val apellidos = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_APELLIDOS))
            val direccion = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_DIRECCION))
            val telefono = cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_TELEFONO))
            val fnacimiento=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_FNACIMIENTO))
            val correo=cursor.getString(cursor.getColumnIndex(Agenda.COLUMN_NAME_CORREO))

            val agenda: Agenda = Agenda(id, nombre, apellidos,direccion,telefono,fnacimiento,correo)
            list.add(agenda)

        }

        cursor.close()
        db.close()

        return list

    }
}