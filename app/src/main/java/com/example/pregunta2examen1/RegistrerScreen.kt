package com.example.pregunta2examen1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RegistrerScreen (viewModel: RegistrerViewModel,  navcontroller: NavController) {
    val state = viewModel.state


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Registro y Lista de Usuarios", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        TextField(
            value = state.personaName,
            onValueChange = { viewModel.changeName(it) },
            placeholder = { Text(text = "Nombre del Usuario") }
        )
        TextField(
            value = state.personaEmail,
            onValueChange = { viewModel.changeEmail(it) },
            placeholder = { Text(text = "E-Mail") }
        )
        TextField(
            value = state.personaPassword,
            onValueChange = { viewModel.changePassword(it) },
            placeholder = { Text(text = "Password") }
        )

        Button(onClick = { viewModel.createPersona() }) {
            Text(
                text = "Agregar Usuario"

            )

        }
        Button(onClick = {  }) {
            Text(
                text = "Regresar ala Pantalla login",
                modifier = Modifier.clickable(onClick = {
                    navcontroller.navigate("login_page")
                })
            )
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.personas) {
                AsistenteItem(persona=it, modifier = Modifier.fillMaxWidth(),
                    onEdit= {
                        viewModel.editPersona(it)
                    }, onDelete= {
                        viewModel.deletePersona(it);
                    })


            }
        }
    }
}