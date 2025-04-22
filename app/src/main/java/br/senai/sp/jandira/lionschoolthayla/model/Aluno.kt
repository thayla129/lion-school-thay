package br.senai.sp.jandira.lionschoolthayla.model

data class Aluno(
    val id: Int,
    val nome: String,
    val matricula: String,
    val curso: String,
    val disciplinas: List<Disciplina>,
    val foto: String,
    val status: String,
    val anoConclusao: String
)
