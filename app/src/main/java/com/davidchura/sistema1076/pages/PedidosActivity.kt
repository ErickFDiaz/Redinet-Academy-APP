package com.davidchura.sistema1076.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1076.model.Pedido
import com.davidchura.sistema1076.network.RetrofitClient
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PedidosActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val idCliente = intent.getStringExtra("idcliente") ?: ""

        setContent {
            Sistema1076Theme {
                Scaffold(
                        topBar = {
                            TopAppBar(
                                    title = { Text("Pedidos del Cliente") },
                                    navigationIcon = {
                                        IconButton(onClick = { finish() }) {
                                            Icon(
                                                    Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = "Back"
                                            )
                                        }
                                    },
                                    colors =
                                            TopAppBarDefaults.topAppBarColors(
                                                    containerColor =
                                                            MaterialTheme.colorScheme
                                                                    .primaryContainer,
                                                    titleContentColor =
                                                            MaterialTheme.colorScheme
                                                                    .onPrimaryContainer
                                            )
                            )
                        }
                ) { innerPadding ->
                    PedidosScreen(idCliente = idCliente, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PedidosScreen(idCliente: String, modifier: Modifier = Modifier) {
    var pedidos by remember { mutableStateOf<List<Pedido>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(idCliente) {
        if (idCliente.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                try {
                    val response = RetrofitClient.webService.getPedidosCliente(idCliente)
                    if (response.isSuccessful) {
                        pedidos = response.body() ?: emptyList()
                    } else {
                        Log.e("PedidosActivity", "Error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("PedidosActivity", "Error: ${e.message}")
                } finally {
                    isLoading = false
                }
            }
        } else {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Cargando pedidos...")
        }
    } else if (pedidos.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay pedidos para este cliente.")
        }
    } else {
        LazyColumn(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
        ) { items(pedidos) { pedido -> PedidoItem(pedido) } }
    }
}

@Composable
fun PedidoItem(pedido: Pedido) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White) // Example card color
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            // Row 1: ID and Date
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                        text = "ID: ${pedido.idpedido}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                )
                Text(
                        text = pedido.fechapedido.substringBefore(" "), // Show only date part
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 2: Destinatario
            Text(
                    text = "Destinatario: ${pedido.destinatario}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Row 3: City and Cargo
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                        text = "Ciudad: ${pedido.ciudaddestinatario}",
                        style = MaterialTheme.typography.bodyMedium
                )
                Text(
                        text = "Cargo: $${pedido.cargo}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF2E7D32), // Green for money
                        fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer: Empleado
            Text(
                    text = "Atendido por: ${pedido.empleado}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
            )
        }
    }
}
