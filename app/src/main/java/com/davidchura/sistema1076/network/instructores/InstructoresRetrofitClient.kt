package com.davidchura.sistema1076.network.instructores

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstructoresRetrofitClient {
    private const val BASE_URL =
            "https://inapp-parcial-back-admin-production-2143.up.railway.app/api/1.0/"

    val webService: InstructoresApiService by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InstructoresApiService::class.java)
    }
}
