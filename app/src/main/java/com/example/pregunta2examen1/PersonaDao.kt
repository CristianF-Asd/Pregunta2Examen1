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

    @Query("SELECT * FROM persona WHERE email = :email")
    fun getPerson(email: String): Flow<Persona>

    @Delete
    suspend fun deletePersona(persona: Persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item:Item)


    @Transaction
    @Query("SELECT * FROM persona WHERE id = :id")
    suspend fun getPersonWithItems(id: String): List<PersonaItems>



}