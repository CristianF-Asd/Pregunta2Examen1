package com.example.pregunta2examen1

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Item (
    @PrimaryKey(autoGenerate = false)
    val id: Int =0,
    val idPerson : String,
    val itemName: String,
    val qty: String,
    val date: String
)



