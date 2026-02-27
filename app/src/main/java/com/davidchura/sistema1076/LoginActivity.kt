package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.lifecycleScope
import com.davidchura.sistema1076.model.Cliente
import com.davidchura.sistema1076.pages.login.LoginViewModel
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens
import com.davidchura.sistema1076.utils.UserStore
import com.davidchura.sistema1076.utils.clienteActivo
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                val scope = rememberCoroutineScope()
                var showDialog by remember { mutableStateOf(false) }
                var dialogMessage by remember { mutableStateOf("") }
                var isLoading by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) {
                     innerPadding ->
                    Column(
                        Modifier.fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = MaterialTheme.dimens.medium),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isLoading){
                            CircularProgressIndicator()
                        } else {
                            OutlinedTextField(
                                label = { Text(text = "Correo o Teléfono") },
                                value = viewModel.correotelefono,
                                onValueChange = { viewModel.correotelefono = it },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
                            OutlinedTextField(
                                label = { Text(text = "Contraseña") },
                                value = viewModel.clave,
                                onValueChange = { viewModel.clave = it },
                                modifier = Modifier.fillMaxWidth(),
                                visualTransformation = PasswordVisualTransformation()
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = viewModel.estadoCheck,
                                    onCheckedChange = { viewModel.estadoCheck = it }
                                )
                                Text(text = "Guardar sesión")
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
                            Button(onClick = {
                                isLoading = true
                                scope.launch {
                                    val respuesta = viewModel.realizarLogin()
                                    val (message, success) = evaluarRespuesta(respuesta)
                                    dialogMessage = message
                                    showDialog = true
                                    isLoading = false
                                    if (success) {
                                        startActivity(Intent(this@LoginActivity, InicioActivity::class.java))
                                        finish()
                                    }
                                }
                            }) {
                                Text("Iniciar sesión")
                            }
                        }

                    }
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Inicio de Sesión") },
                        text = { Text(dialogMessage) },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Aceptar")
                            }
                        }
                    )
                }
            }
        }
    }

    fun evaluarRespuesta(respuesta: String): Pair<String, Boolean> {
        return when (respuesta) {
            "-1" -> "La cuenta no existe" to false
            "-2" -> "Contraseña incorrecta" to false
            "Error" -> "Ha ocurrido un error" to false
            else -> {
                clienteActivo = Gson().fromJson(respuesta, Array<Cliente>::class.java).first()
                if (viewModel.estadoCheck) {
                    val userStore = UserStore(this)
                    lifecycleScope.launch {
                        userStore.guardarDatosUsuario(respuesta)
                    }
                }
                "Bienvenido" to true
            }
        }
    }
}