package com.example.pregunta2examen1
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Persona (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val email: String,
    val password: String
)