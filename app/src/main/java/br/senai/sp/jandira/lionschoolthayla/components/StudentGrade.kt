package br.senai.sp.jandira.lionschoolthayla.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionschoolthayla.model.Disciplina
import br.senai.sp.jandira.lionschoolthayla.ui.theme.LionSchoolThaylaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentGrade(disciplina: Disciplina) {
    val baseGraphWidth = 230
    val bar = (disciplina.media / 100 * baseGraphWidth).toInt()

    val barColor = when {
        disciplina.media < 60.0 -> Color(0xFFE25F67)  // Vermelho para notas baixas
        disciplina.media < 80.0 -> Color(0xFFF6B817) // Amarelo para notas médias
        else -> Color(0xFF30B988)                    // Verde para notas altas
    }

    Log.i("StudentGrade", "Bar width: $bar")

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Sigla da disciplina
        Text(
            text = disciplina.sigla,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.width(48.dp)
        )

        // Barra de progresso
        Box(
            modifier = Modifier
                .width(baseGraphWidth.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
        ) {
            Card(
                modifier = Modifier
                    .width(bar.dp)
                    .height(32.dp),
                colors = CardDefaults.cardColors(containerColor = barColor),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                shape = RoundedCornerShape(4.dp)
            ) {}
        }

        // Média da disciplina
        Text(
            text = "%.1f".format(disciplina.media),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            color = Color.White,
            modifier = Modifier.width(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StudentGradePreview() {
    LionSchoolThaylaTheme {
        Surface(
            color = Color(0xFF3347B0) // Cor de fundo para visualização
        ) {
            StudentGrade(
                disciplina = Disciplina(
                    id = 1,
                    sigla = "ADM",
                    media = 92.0,
                    status = "Aprovado"
                )
            )
        }
    }
}