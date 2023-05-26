package com.example.pregunta2examen1

import android.annotation.SuppressLint
import android.app.DatePickerDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.platform.LocalDensity
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemScreen (navcontroller: NavController, text: String?) {

    var email by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var cantidad by remember { mutableStateOf("") }
    var tipoBotella by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Carton", "Plastico", "Vidrio")
    var selectedText by remember { mutableStateOf("") }



    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hola $text") })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Registro de Botellas", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dropdown para seleccionar el tipo de botella

                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = {Text("Tipo de Botella")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))



                MiDatePicker()

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Registrar Botella")
                }
            }
        }
    )

}

@Composable
fun MiDatePicker(){
    var fecha:String by rememberSaveable{ mutableStateOf("") }
    var anio : Int
    var mes : Int
    var dia : Int
    var nCalendar = Calendar.getInstance()
    anio = nCalendar.get(Calendar.YEAR)
    mes = nCalendar.get(Calendar.MONTH)
    dia = nCalendar.get(Calendar.DAY_OF_MONTH)

    val nDatePickerDialog = DatePickerDialog(
        LocalContext.current, {
            _, anio:Int,mes:Int,dia:Int ->
            fecha = "$dia/${mes+1}/$anio"
        },anio,mes,dia

    )
    Box(modifier = Modifier.fillMaxWidth()){
        Row() {
            OutlinedTextField(value = fecha, onValueChange = {fecha = it}, readOnly = true,label={Text(text="Selecciona Fecha")}

            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription =  null,
                modifier = Modifier.size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        nDatePickerDialog.show()
                    }
            )
        }
    }
}