package br.senai.sp.jandira.lionschoolthayla.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.lionschoolthayla.R
import br.senai.sp.jandira.lionschoolthayla.components.Header
import br.senai.sp.jandira.lionschoolthayla.components.LionSchoolThaylaTheme
import br.senai.sp.jandira.lionschoolthayla.model.Aluno
import br.senai.sp.jandira.lionschoolthayla.repository.StudentsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsListScreen(navigationController: NavHostController, course: String?) {

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
                Header(text = "DS")
                Divider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(1.dp),
                    color = Color(0xFFFFC23D)
                )

                // Barra de pesquisa
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFFFFC23D),
                        focusedBorderColor = Color(0xFFFFC23D),
                        cursorColor = Color(0xFF3347B0)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(
                            text = "Find a student",
                            color = Color.Gray
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF3347B0)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Título da lista
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "List",
                        tint = Color(0xFFFFC23D),
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Students List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3347B0)
                    )
                }

                // Lista de alunos
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(StudentsRepository.getStudents()) { aluno ->
                        StudentCard(
                            aluno = aluno,
                            navigationController = navigationController
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCard(aluno: Aluno, navigationController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                navigationController.navigate("student_detail_screen/${aluno.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF9FA9E1)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de status
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(16.dp)
                    .background(
                        color = when (aluno.status) {
                            "Finalizado" -> Color(0xFFFFC23D)
                            else -> Color(0xFF3347B0)
                        }
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Foto do aluno
            Card(
                modifier = Modifier.size(80.dp),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lion_user4),
                    contentDescription = "Student photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Informações do aluno
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = aluno.nome,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Registration",
                        tint = Color(0xFFFFC23D),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = aluno.matricula,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Graduation year",
                        tint = Color(0xFFFFC23D),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = aluno.anoConclusao,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
                }
                }
            }


@Preview(showSystemUi = true)
@Composable
fun StudentsListScreenPreview() {
    LionSchoolThaylaTheme {
        // StudentsListScreen(navigationController = rememberNavController())
    }
}