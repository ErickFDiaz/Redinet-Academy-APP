package com.davidchura.sistema1076.model

data class Cliente(
    val idcliente: Int,
    val empresa: String,
    val nombres: String,
    val cargo: String,
    val ciudad: String,
    val pais: String,
)