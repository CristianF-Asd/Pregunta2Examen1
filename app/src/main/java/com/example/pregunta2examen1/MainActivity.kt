package com.example.pregunta2examen1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.pregunta2examen1.ui.theme.Pregunta2Examen1Theme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pregunta2Examen1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val database = Room.databaseBuilder(this, DataBase::class.java,"persona_db2").build()
                    val dao = database.dao
                    val viewModel = RegistrerViewModel(dao)


                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login_page", builder = {
                        composable("login_page") {LoginScreen(viewModel,navController)}
                        composable("register_page") { RegistrerScreen(viewModel,navController)}
                        composable("item_page"+"/{text}",
                            arguments = listOf(navArgument(name = "text"){
                                type = NavType.StringType
                            })
                        ) { ItemScreen(navController,it.arguments?.getString("text"))}

                    })

                }
            }
        }
    }
}

@Composable
fun LoginApplication() {


}

