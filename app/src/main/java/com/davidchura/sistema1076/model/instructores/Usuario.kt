package com.davidchura.sistema1076.model.instructores

data class Usuario(
        val _id: String,
        val name: String,
        val email: String,
        val role: String,
        val createdAt: String? = null,
        val updatedAt: String? = null
)
