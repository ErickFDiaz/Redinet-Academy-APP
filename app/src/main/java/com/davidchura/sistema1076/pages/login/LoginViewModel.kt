package com.davidchura.sistema1076.pages.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidchura.sistema1076.utils.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService{
    @FormUrlEncoded
    @POST("login.php")
    suspend fun getLogin(
        @Field("correotelefono") correotelefono: String,
        @Field("clave") clave: String
    ): String
}

object RetrofitClientLogin {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }
}

class LoginViewModel: ViewModel() {
    private val api = RetrofitClientLogin.loginService
    var correotelefono by mutableStateOf("")
    var clave by mutableStateOf("")
    var estadoCheck by mutableStateOf(false)

    suspend fun realizarLogin():String {
        return try {
            api.getLogin(correotelefono, clave)
        } catch (e: Exception){
            "Error"
        }
    }
}