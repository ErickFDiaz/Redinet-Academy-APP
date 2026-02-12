package com.davidchura.sistema1076.network

import com.davidchura.sistema1076.model.Cliente
import retrofit2.Response
import retrofit2.http.GET

interface ServicioApiService {
    @GET("clientes.php") suspend fun getClientes(): Response<List<Cliente>>
}
