package com.davidchura.sistema1076

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import com.davidchura.sistema1076.ui.theme.dimens

class TerminosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                Column(modifier = Modifier
                    .padding(MaterialTheme.dimens.medium)
                    .verticalScroll(rememberScrollState())) {
                    Text(
                        stringResource(R.string.title_activity_terminos),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(stringResource(R.string.terminos_texto))
                    Button(onClick = {
                        finish()
                    }) {
                        Text(text = stringResource(R.string.cerrar))
                    }
                }
            }
        }
    }
}
