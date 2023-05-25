package com.example.pregunta2examen1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID

class  RegistrerViewModel( private val dao:PersonaDao) : ViewModel(){

    var state by mutableStateOf(RegistrerState())
        private set

    init{
        viewModelScope.launch {
            dao.getAllPersona().collectLatest {
                state = state.copy(
                    personas = it
                )
            }
        }
    }

    fun getUserfromEmail(email: String,password: String) = dao.getPerson(email)

    fun changeName(name: String){
        state = state.copy(
            personaName = name
        )
    }

    fun changeEmail(email: String){
        state = state.copy(
            personaEmail = email
        )
    }
    fun changePassword(password: String){
        state = state.copy(
            personaPassword = password
        )
    }
    fun deletePersona(persona: Persona){
        viewModelScope.launch {
            dao.deletePersona(persona)
        }
    }
    fun editPersona(persona: Persona){
        state = state.copy(
            personaId = persona.id,
            personaName = persona.name,
            personaEmail = persona.email,
            personaPassword = persona.password

        )

    }

    fun createPersona(){
        val persona = Persona(
            state.personaId?: UUID.randomUUID().toString(),
            state.personaName,
            state.personaEmail,
            state.personaPassword
        )
        viewModelScope.launch {
            dao.insertPersona(persona)
        }
        state = state.copy(
            personaId = null,
            personaName = "",
            personaEmail = "",
            personaPassword = ""
        )


    }


}