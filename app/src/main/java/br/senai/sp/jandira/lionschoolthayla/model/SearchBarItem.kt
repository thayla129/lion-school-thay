package br.senai.sp.jandira.lionschoolthayla.model

import androidx.compose.ui.graphics.Color

data class SearchBarItem(
    var text: String,
    var isSelected: Boolean = false,
    var color: Color
)
