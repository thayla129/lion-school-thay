package br.senai.sp.jandira.lionschoolthayla.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.lionschoolthayla.R
import br.senai.sp.jandira.lionschoolthayla.components.Header
import br.senai.sp.jandira.lionschoolthayla.components.LionSchoolThaylaTheme
import br.senai.sp.jandira.lionschoolthayla.model.Course
import br.senai.sp.jandira.lionschoolthayla.repository.CourseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavHostController) {
    LionSchoolThaylaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Header(text = "")

                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFFFFC23D),
                        focusedBorderColor = Color(0xFFFFC23D),
                        cursorColor = Color(0xFF3347B0)
                    ),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF3347B0)
                    },
                    placeholder = {
                        Text(text = "Find your course")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title Section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Courses",
                        tint = Color(0xFFFFC23D),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Courses",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3347B0)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Courses List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(CourseRepository.getCourses()) { course ->
                        CourseCard(
                            course = course,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(course: Course, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                navController.navigate("students_screen/${course.sigla}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF3347B0)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 2.dp, color = Color(0xFFFFC23D))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = course.icone),
                    contentDescription = "Course icon",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = course.sigla,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFC23D)
                )
            }

            Text(
                text = course.nome,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = course.descricao,
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.watch_later_24),
                    contentDescription = "Duration",
                    tint = Color(0xFFFFC23D),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = course.cargaHoraria,
                    fontSize = 16.sp,
                    color = Color(0xFFFFC23D)
                )
            }
        }
    }
}

@Preview
@Composable
fun CourseCardPreview() {
    LionSchoolThaylaTheme {
        CourseCard(
            course = Course(
                id = 1,
                sigla = "DS",
                icone = R.drawable.lion_developer,
                nome = "Desenvolvimento de Sistemas",
                descricao = "Aprenda a desenvolver aplicações web e mobile.",
                cargaHoraria = "3 SEMESTRES"
            ),
            navController = rememberNavController()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoursesScreenPreview() {
    LionSchoolThaylaTheme {
        CoursesScreen(navController = rememberNavController())
    }
}