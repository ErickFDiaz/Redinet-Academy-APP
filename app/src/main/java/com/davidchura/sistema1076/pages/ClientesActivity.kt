package com.davidchura.sistema1076.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1076.model.Cliente
import com.davidchura.sistema1076.network.RetrofitClient
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClientesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ClientesScreen(modifier: Modifier = Modifier) {
    var clientes by remember { mutableStateOf<List<Cliente>>(emptyList()) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getClientes()
                if (response.isSuccessful) {
                    clientes = response.body() ?: emptyList()
                } else {
                    Log.e("ClientesActivity", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ClientesActivity", "Error: ${e.message}")
            }
        }
    }

    LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
    ) { itemsIndexed(clientes) { index, cliente -> ClienteItem(cliente, index) } }
}

@Composable
fun ClienteItem(cliente: Cliente, index: Int) {
    // Define gradients
    val gradients =
            listOf(
                    Brush.linearGradient(
                            colors =
                                    listOf(
                                            Color(0xFF536DFE),
                                            Color(0xFF303F9F)
                                    ) // Blue -> Dark Blue
                    ),
                    Brush.linearGradient(
                            colors =
                                    listOf(
                                            Color(0xFFFF7043),
                                            Color(0xFFD84315)
                                    ) // Orange -> Dark Orange
                    ),
                    Brush.linearGradient(
                            colors =
                                    listOf(
                                            Color(0xFF26A69A),
                                            Color(0xFF00796B)
                                    ) // Teal -> Dark Teal
                    ),
                    Brush.linearGradient(
                            colors =
                                    listOf(
                                            Color(0xFFFFA726),
                                            Color(0xFFEF6C00)
                                    ) // Amber -> Dark Amber
                    )
            )

    val currentGradient = gradients[index % gradients.size]

    Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
                modifier =
                        Modifier.fillMaxWidth()
                                .heightIn(min = 180.dp)
                                .background(currentGradient)
                                .padding(24.dp)
        ) {
            Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header Row (Empresa + Badge)
                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                            text = cliente.empresa.uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.9f),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            modifier = Modifier.weight(1f)
                    )

                    Surface(
                            shape = RoundedCornerShape(50),
                            color = Color.White.copy(alpha = 0.3f),
                            modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                                text = cliente.ciudad,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Main Content (Name)
                Text(
                        text = cliente.nombres,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                )

                // Footer (Email)
                Column {
                    Text(
                            text = "Correo Electronico",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                            text = cliente.correo,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
