package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme
import kotlinx.coroutines.delay

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var startAnimation by remember { mutableStateOf(false) }
            val offsetY by animateDpAsState(
                targetValue = if(startAnimation) 0.dp else (1500).dp,
                animationSpec = tween(durationMillis = 1000),
            )
            val scale by animateFloatAsState(
                targetValue = if(startAnimation) 1f else 3f,
                animationSpec = tween(durationMillis = 2000)
            )
            val alpha by animateFloatAsState(
                targetValue = if(startAnimation) 1f else 0f,
                animationSpec = tween(durationMillis = 3000)
            )
            Sistema1076Theme {
                LaunchedEffect(key1 = true) {
                    startAnimation = true
                    delay(3000)
                    startActivity(Intent(this@IntroActivity, MainActivity::class.java))
                }
                Column (modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Image(painterResource(R.drawable.inlearnin),
                        contentDescription = stringResource(R.string.texto_logo),
                        modifier = Modifier.height(240.dp)
                            .offset{IntOffset(0, offsetY.value.toInt())}
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale,
                                alpha = alpha
                            )
                    )
                }
            }
        }
    }
}
