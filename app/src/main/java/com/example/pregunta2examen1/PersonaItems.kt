package com.example.pregunta2examen1

import androidx.room.Embedded
import androidx.room.Relation

data class PersonaItems (
    @Embedded val persona: Persona,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPerson"
    )
    val items: List<Item>
)



