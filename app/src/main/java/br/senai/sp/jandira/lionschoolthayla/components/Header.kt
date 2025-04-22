package br.senai.sp.jandira.lionschoolthayla.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolthayla.R
import br.senai.sp.jandira.lionschoolthayla.ui.theme.LionSchoolThaylaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo da Lion School",
            modifier = Modifier.size(80.dp)
        )

        Card(
            modifier = Modifier.wrapContentSize(),
            colors = CardDefaults.cardColors(
                containerColor = if (text.isBlank()) {
                    Color.Transparent
                } else {
                    Color(0xFFFFC23D)
                }
            ),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (text.isBlank()) 0.dp else 4.dp
            )
        ) {
            if (text.isNotBlank()) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3347B0),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    LionSchoolThaylaTheme {
        Column {
            Header("DS")
            Spacer(modifier = Modifier.height(16.dp))
            Header("") // Versão sem texto
        }
    }
}