package com.davidchura.sistema1076.network.instructores

import com.davidchura.sistema1076.model.instructores.ApiResponse
import com.davidchura.sistema1076.model.instructores.Curso
import com.davidchura.sistema1076.model.instructores.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InstructoresApiService {
    @GET("users")
    suspend fun getUsuarios(): Response<ApiResponse<List<Usuario>>>

    @GET("users/{id}/cursos?sort=desc")
    suspend fun getCursosPorUsuario(@Path("id") id: String): Response<ApiResponse<List<Curso>>>
}
