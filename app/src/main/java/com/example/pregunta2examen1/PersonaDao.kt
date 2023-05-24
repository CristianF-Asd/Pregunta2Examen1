package com.example.pregunta2examen1

import androidx.room.Dao
import androidx.room.*
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersona(persona: Persona)

    @Query("SELECT * FROM persona")
    fun getAllPersona(): Flow<List<Persona>>

    @Delete
    suspend fun deletePersona(persona: Persona)

}