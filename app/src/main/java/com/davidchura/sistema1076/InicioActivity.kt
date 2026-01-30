package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidchura.sistema1076.pages.ClientesActivity
import com.davidchura.sistema1076.pages.DirectoresActivity
import com.davidchura.sistema1076.pages.EmpleadosActivity
import com.davidchura.sistema1076.pages.ProveedoresActivity
import com.davidchura.sistema1076.pages.TiendaActivity
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens

data class MenuCardOpcion(
    val titulo: String,
    val icon: ImageVector
)

class InicioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*
        val titulos = arrayOf("Proveedores", "Empleados", "Tienda",
            "Clientes", "Directores", "Salir")
        */

        val MenuCardOpciones = listOf(
            MenuCardOpcion("Proveedores", Icons.Filled.AccountBox),
            MenuCardOpcion("Empleados", Icons.Filled.Face),
            MenuCardOpcion("Tienda", Icons.Filled.ShoppingCart),
            MenuCardOpcion("Clientes", Icons.Filled.Person),
            MenuCardOpcion("Directores", Icons.Filled.Notifications),
            MenuCardOpcion("Salir", Icons.Filled.Close),
        )

        setContent {
            Sistema1076Theme {
                Column() {
                    Box(contentAlignment = Alignment.BottomEnd,
                        modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = "https://www.telemundo.com/sites/nbcutelemundo/files/images/article/cover/2021/03/01/mujer-supermercado.jpg",
                            contentDescription = "Imagen desde la web",
                            modifier = Modifier.size(400.dp),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(Color.Black.copy(alpha = 0.70f))
                        )
                        Text(
                            modifier = Modifier.padding(MaterialTheme.dimens.large),
                            text = stringResource(R.string.title_activity_inicio),
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                    }

                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small),
                        modifier = Modifier.padding(MaterialTheme.dimens.medium)) {
                        items(MenuCardOpciones.size){ index ->
                            Card(modifier = Modifier.clickable{
                                seleccionarCard(index)
                            }) {
                                Column(modifier = Modifier.padding(MaterialTheme.dimens.large)) {
                                    Icon(MenuCardOpciones[index].icon, contentDescription = null)
                                    Text(MenuCardOpciones[index].titulo)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun seleccionarCard(index: Int) {
        Log.d("POSICION", index.toString())
        when(index){
            0 -> startActivity(Intent(this, ProveedoresActivity::class.java))
            1 -> startActivity(Intent(this, EmpleadosActivity::class.java))
            2 -> startActivity(Intent(this, TiendaActivity::class.java))
            3 -> startActivity(Intent(this, ClientesActivity::class.java))
            4 -> startActivity(Intent(this, DirectoresActivity::class.java))
            5 -> finish()
        }
    }
}
