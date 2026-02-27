package com.davidchura.sistema1076.pages

import android.os.Bundle
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1076.model.instructores.Curso
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme

class CursosActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                val idUsuario = intent.getStringExtra("idUsuario") ?: ""
                enableEdgeToEdge()
                setContent {
                        Sistema1076Theme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                        CursosScreen(
                                                idUsuario = idUsuario,
                                                modifier = Modifier.padding(innerPadding)
                                        )
                                }
                        }
                }
        }
}

@Composable
fun CursosScreen(
        idUsuario: String,
        modifier: Modifier = Modifier,
        viewModel: CursosViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
        val cursos by viewModel.cursos.collectAsState()

        LaunchedEffect(idUsuario) { viewModel.fetchCursos(idUsuario) }

        LazyColumn(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
        ) { itemsIndexed(cursos) { index, curso -> CursoItem(curso, index) } }
}

@Composable
fun CursoItem(curso: Curso, index: Int) {
        val gradients =
                listOf(
                        Brush.linearGradient(
                                colors = listOf(Color(0xFF8E24AA), Color(0xFF6A1B9A))
                        ), // Purples
                        Brush.linearGradient(
                                colors = listOf(Color(0xFF3949AB), Color(0xFF283593))
                        ), // Indigo
                        Brush.linearGradient(
                                colors = listOf(Color(0xFF00ACC1), Color(0xFF00838F))
                        ), // Cyan
                        Brush.linearGradient(
                                colors = listOf(Color(0xFF43A047), Color(0xFF2E7D32))
                        ) // Green
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
                                        .heightIn(min = 160.dp)
                                        .background(currentGradient)
                                        .padding(24.dp)
                ) {
                        Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                        ) {
                                Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                ) {
                                        Text(
                                                text = "CURSO",
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
                                                        text = curso.categoria.uppercase(),
                                                        modifier =
                                                                Modifier.padding(
                                                                        horizontal = 12.dp,
                                                                        vertical = 4.dp
                                                                ),
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = Color.White,
                                                        fontWeight = FontWeight.SemiBold
                                                )
                                        }
                                }

                                Text(
                                        text = curso.titulo,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold
                                )

                                Column {
                                        Text(
                                                text = "Precio",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = Color.White.copy(alpha = 0.8f)
                                        )
                                        Text(
                                                text = "S/ ${curso.precio}",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = Color.White,
                                                fontWeight = FontWeight.Medium
                                        )
                                }
                        }
                }
        }
}
