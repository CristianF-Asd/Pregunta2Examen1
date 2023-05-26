package com.example.pregunta2examen1

data class RegistrerState (

    val personas: List<Persona> = emptyList(),
    val personaId : String? = null,
    val personaName : String = "",
    val personaEmail: String = "",
    val personaPassword: String = "",

    val items: List<Item> = emptyList(),
    val itemId : String? = null,
    val itemName: String ="",
    val itemQty: String ="",
    val itemDate: String = ""

)