package com.davidchura.sistema1076.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.davidchura.sistema1076.model.Empleado
import com.davidchura.sistema1076.ui.theme.Color4
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EmpleadosService{
    @GET("empleados.php")
    suspend fun getEmpleados(): List<Empleado>
}

class EmpleadosActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = Retrofit.Builder()
            .baseUrl("https://servicios.campus.pe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmpleadosService::class.java)
        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {  },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, null)
                                }
                            },

                        )

                    }) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        var isLoading by remember { mutableStateOf(true) }
                        var listaEmpleados by remember {
                            mutableStateOf<List<Empleado>?>(null)
                        }

                        LaunchedEffect(key1 = Unit) {
                            listaEmpleados = api.getEmpleados()
                            isLoading = false
                        }

                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            if(isLoading) {
                                //LinearProgressIndicator()
                                CircularProgressIndicator()
                            }
                            else{
                                LazyRow() {
                                    items(items = listaEmpleados!!){ empleado ->
                                        DibujarEmpleado(empleado, modifier = Modifier.fillParentMaxSize())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DibujarEmpleado(empleado: Empleado, modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomStart) {
    AsyncImage(
        model = "https://servicios.campus.pe/" + empleado.foto,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        Column(modifier = Modifier.padding(MaterialTheme.dimens.medium)) {
            Text(
                text = empleado.nombres + " " + empleado.apellidos,
                style = MaterialTheme.typography.titleLarge,
                color = Color4,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(
                        MaterialTheme.dimens.medium, MaterialTheme.dimens.extraSmall
                    )
            )
            Text(text = empleado.cargo,
                color = Color.Black,
                modifier = Modifier
                    .background(Color4)
                    .padding(
                        MaterialTheme.dimens.medium, MaterialTheme.dimens.extraSmall
                    ))
        }
    }
}