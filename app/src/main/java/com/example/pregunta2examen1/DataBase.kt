package com.example.pregunta2examen1
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Persona::class,Item::class], version = 2)
abstract class DataBase: RoomDatabase()  {

    abstract val dao: PersonaDao
}