package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidchura.sistema1076.pages.ClientesActivity
import com.davidchura.sistema1076.pages.DirectoresActivity
import com.davidchura.sistema1076.pages.EmpleadosActivity
import com.davidchura.sistema1076.pages.ProveedoresActivity
import com.davidchura.sistema1076.pages.TiendaActivity
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme

data class MenuCardOpcion(val titulo: String, val icon: ImageVector, val color: Color)

class InicioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val menuCardOpciones =
                listOf(
                        MenuCardOpcion(
                                "Proveedores",
                                Icons.Filled.AccountBox,
                                Color(0xFF9C27B0)
                        ), // Purple
                        MenuCardOpcion("Empleados", Icons.Filled.Face, Color(0xFF4CAF50)), // Green
                        MenuCardOpcion(
                                "Tienda",
                                Icons.Filled.ShoppingCart,
                                Color(0xFFFF9800)
                        ), // Orange
                        MenuCardOpcion("Clientes", Icons.Filled.Person, Color(0xFF2196F3)), // Blue
                        MenuCardOpcion(
                                "Directores",
                                Icons.Filled.Notifications,
                                Color(0xFFE91E63)
                        ), // Pink
                        MenuCardOpcion("Salir", Icons.Filled.Close, Color(0xFF607D8B)), // Blue Grey
                )

        setContent {
            Sistema1076Theme {
                Column {
                    Box(
                            contentAlignment = Alignment.BottomStart,
                            modifier = Modifier.fillMaxWidth().height(250.dp)
                    ) {
                        AsyncImage(
                                model =
                                        "https://blobacademy-cdn-ageydhh8cdb8ewck.a03.azurefd.net/redinetacademy/WebAcademyAll/Login_RedinetAcademy_light.png",
                                contentDescription = "Imagen desde la web",
                                modifier = Modifier.matchParentSize(),
                                contentScale = ContentScale.Crop
                        )
                        Box(
                                modifier =
                                        Modifier.matchParentSize()
                                                .background(Color.Black.copy(alpha = 0.40f))
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                    text = "Bienvenido,",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                            )
                            Text(
                                    text = stringResource(R.string.title_activity_inicio),
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                            )
                        }
                    }

                    LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                    ) {
                        items(menuCardOpciones.size) { index ->
                            val opcion = menuCardOpciones[index]
                            Surface(
                                    shape = RoundedCornerShape(50), // Pill shape
                                    color = Color(0xFFF5F5F5), // Light gray background
                                    modifier =
                                            Modifier.fillMaxWidth().height(80.dp).clickable {
                                                selectingItem(index)
                                            },
                                    shadowElevation = 4.dp
                            ) {
                                Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 8.dp, end = 16.dp)
                                ) {
                                    // Colored Circle for Icon
                                    Box(
                                            contentAlignment = Alignment.Center,
                                            modifier =
                                                    Modifier.size(64.dp)
                                                            .background(opcion.color, CircleShape)
                                    ) {
                                        Icon(
                                                imageVector = opcion.icon,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                            text = opcion.titulo,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun selectingItem(index: Int) {
        Log.d("POSICION", index.toString())
        when (index) {
            0 -> startActivity(Intent(this, ProveedoresActivity::class.java))
            1 -> startActivity(Intent(this, EmpleadosActivity::class.java))
            2 -> startActivity(Intent(this, TiendaActivity::class.java))
            3 -> startActivity(Intent(this, ClientesActivity::class.java))
            4 -> startActivity(Intent(this, DirectoresActivity::class.java))
            5 -> finish()
        }
    }
}
