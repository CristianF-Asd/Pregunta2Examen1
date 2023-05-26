package com.example.pregunta2examen1

import android.app.Person
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class  RegistrerViewModel( private val dao:PersonaDao) : ViewModel(){
    private val _showToast = mutableStateOf(false)
    val showToast: MutableState<Boolean> = _showToast

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

    fun getUserfromEmail(navcontroller: NavController, email: String, password: String)
    {
        viewModelScope.launch {
            val person = dao.getPerson(email).first()
            println(person.id)
            if(person.email==email && person.password==password){
                navcontroller.navigate("item_page/${person.email}")
            }else{
                _showToast.value = true
            }
        }

    }

    fun showToastComplete() {
        _showToast.value = false
    }

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