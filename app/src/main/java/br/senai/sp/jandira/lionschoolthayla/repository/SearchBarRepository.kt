package br.senai.sp.jandira.lionschoolthayla.repository

import androidx.compose.ui.graphics.Color
import br.senai.sp.jandira.lionschoolthayla.model.SearchBarItem

class SearchBarRepository {

    companion object {
        fun getFiltros(): ArrayList<SearchBarItem> {

            val todos = SearchBarItem("Todos", true, Color(0xFFFFC23D))
            val cursando = SearchBarItem("Cursando", false, Color(0xFFFFC23D))
            val finalizado = SearchBarItem("Finalizado", false, Color(0xFFFFC23D))

            val itemsBar = ArrayList<SearchBarItem>()
            itemsBar.add(todos)
            itemsBar.add(cursando)
            itemsBar.add(finalizado)

            return itemsBar

        }

    }
}