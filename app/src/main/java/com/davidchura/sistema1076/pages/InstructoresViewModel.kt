package com.davidchura.sistema1076.pages

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidchura.sistema1076.model.instructores.Usuario
import com.davidchura.sistema1076.network.instructores.InstructoresRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstructoresViewModel : ViewModel() {
    private val _instructores = MutableStateFlow<List<Usuario>>(emptyList())
    val instructores: StateFlow<List<Usuario>> = _instructores.asStateFlow()

    init {
        fetchInstructores()
    }

    private fun fetchInstructores() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = InstructoresRetrofitClient.webService.getUsuarios()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.success) {
                        _instructores.value = apiResponse.data
                    }
                } else {
                    Log.e("InstructoresViewModel", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("InstructoresViewModel", "Exception: ${e.message}")
            }
        }
    }
}
