package com.davidchura.sistema1076.pages

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidchura.sistema1076.model.instructores.Curso
import com.davidchura.sistema1076.network.instructores.InstructoresRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CursosViewModel : ViewModel() {
    private val _cursos = MutableStateFlow<List<Curso>>(emptyList())
    val cursos: StateFlow<List<Curso>> = _cursos.asStateFlow()

    fun fetchCursos(idUsuario: String) {
        if (idUsuario.isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = InstructoresRetrofitClient.webService.getCursosPorUsuario(idUsuario)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.success) {
                        _cursos.value = apiResponse.data
                    }
                } else {
                    Log.e("CursosViewModel", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("CursosViewModel", "Exception: ${e.message}")
            }
        }
    }
}
