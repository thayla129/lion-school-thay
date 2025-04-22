package br.senai.sp.jandira.lionschoolthayla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.lionschoolthayla.ui.screens.CoursesScreen
import br.senai.sp.jandira.lionschoolthayla.ui.screens.MainScreen
import br.senai.sp.jandira.lionschoolthayla.ui.screens.StudentDetail
import br.senai.sp.jandira.lionschoolthayla.ui.screens.StudentsListScreen
import br.senai.sp.jandira.lionschoolthayla.ui.theme.LionSchoolThaylaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Habilitando edge-to-edge para todas versões

        setContent {
            LionSchoolThaylaTheme {
                // Configuração da navegação
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        composable("main_screen") {
                            MainScreen(navController)
                        }
                        composable("courses_screen") {
                            CoursesScreen(navController)
                        }
                        composable("students_screen/{course}") { backStackEntry ->
                            val course = backStackEntry.arguments?.getString("course")
                            StudentsListScreen(navController, course)
                        }
                        composable("student_detail_screen/{studentId}") { backStackEntry ->
                            val studentId = backStackEntry.arguments?.getString("studentId")
                            StudentDetail(navController, studentId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LionSchoolThaylaTheme {
        Greeting("Android")
    }
}