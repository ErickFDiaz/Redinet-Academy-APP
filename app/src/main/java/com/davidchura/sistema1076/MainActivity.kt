package com.davidchura.sistema1076


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                Column(modifier = Modifier.fillMaxWidth().padding(top = MaterialTheme.dimens.large),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.frase),
                        style = MaterialTheme.typography.titleLarge)
                        //style = TextStyle(fontSize = 24.sp,fontWeight = FontWeight.Bold))
                    Text(text = stringResource(R.string.autor))
                }

                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Image(painterResource(R.drawable.inlearnin),
                        contentDescription = stringResource(R.string.texto_logo),
                        modifier = Modifier.height(240.dp))
                    Text(text = stringResource(R.string.saludo),
                        style = MaterialTheme.typography.displayLarge)
                        //style = TextStyle(fontSize = 72.sp, fontWeight = FontWeight.Bold))
                    Button(onClick = {
                        startActivity(Intent(this@MainActivity, EmpezarActivity::class.java))
                    }) {
                        Text(stringResource(R.string.empezar))
                    }
                }

                Column (modifier = Modifier.fillMaxSize().padding(bottom = MaterialTheme.dimens.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom){
                    Text(text = stringResource(R.string.derechos))
                }
            }
        }
    }
}
