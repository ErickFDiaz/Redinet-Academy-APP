package com.davidchura.sistema1076.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1076.R
import com.davidchura.sistema1076.model.Proveedor
import com.davidchura.sistema1076.ui.theme.Color1
import com.davidchura.sistema1076.ui.theme.Color2
import com.davidchura.sistema1076.ui.theme.Color3
import com.davidchura.sistema1076.ui.theme.Color4
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProveedoresService{
    @GET("proveedores.php")
    suspend fun getProveedores(): List<Proveedor>
}

class ProveedoresActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl("https://servicios.campus.pe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProveedoresService::class.java)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color2.toArgb(),
                darkScrim = Color2.toArgb(),
            )
        )
        setContent {
            Sistema1076Theme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {Text(stringResource(R.string.title_activity_proveedores))},
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, null)
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface, // Color de fondo
                                titleContentColor = Color.White,    // Color del título
                                navigationIconContentColor = Color.White // Color del icono
                            )
                        )

                    })
                { innerPadding ->
                    var isLoading by remember { mutableStateOf(true) }
                    var listaProveedores by remember {
                        mutableStateOf<List<Proveedor>?>(null)
                    }
                    LaunchedEffect(key1 = Unit) {
                        listaProveedores = api.getProveedores()
                        isLoading = false
                    }
                    Column(modifier = Modifier.padding(innerPadding)) {
                        /*
                        Text(
                            stringResource(R.string.title_activity_proveedores),
                            style = MaterialTheme.typography.headlineLarge
                        )*/
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            if(isLoading) {
                                //LinearProgressIndicator()
                                CircularProgressIndicator()
                            }
                            else{
                                LazyColumn {
                                    /*
                                    items(listaProveedores!!.size){
                                        Text(text = listaProveedores!![it].nombreempresa)
                                        Text(text = listaProveedores!![it].nombrecontacto)
                                        Text(text = listaProveedores!![it].cargocontacto)
                                    }
                                     */
                                    items(items = listaProveedores!!){
                                        DibujarProveedor(it)
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
fun DibujarProveedor(itemProveedor: Proveedor){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(MaterialTheme.dimens.small)
        .border(BorderStroke(1.dp, Color3))
        .padding(MaterialTheme.dimens.medium)){
        Text(
            text = itemProveedor.nombreempresa,
            style = MaterialTheme.typography.titleLarge,
            color = Color4
        )
        Text(text = itemProveedor.nombrecontacto)
        Text(text = itemProveedor.cargocontacto)
    }
}