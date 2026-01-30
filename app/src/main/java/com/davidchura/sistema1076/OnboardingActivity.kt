package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1076.ui.theme.OnboardingEnd
import com.davidchura.sistema1076.ui.theme.OnboardingStart
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1076Theme {
                OnboardingScreen(
                        onSkipClick = { navigateToMain() },
                        onNextClick = { navigateToMain() }
                )
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@Composable
fun OnboardingScreen(onSkipClick: () -> Unit, onNextClick: () -> Unit) {
    Box(
            modifier =
                    Modifier.fillMaxSize()
                            .background(
                                    Brush.verticalGradient(
                                            colors = listOf(OnboardingStart, OnboardingEnd)
                                    )
                            )
    ) {
        Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Illustration
            Image(
                    painter =
                            painterResource(
                                    id = R.drawable.inlearnin
                            ), // Reusing existing image as placeholder
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Content Card
            Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
            ) {
                Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                            text = stringResource(R.string.onboarding_title),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                            text = stringResource(R.string.onboarding_description),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Pager Indicators (Static for now)
                    Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                                modifier =
                                        Modifier.size(24.dp, 6.dp)
                                                .clip(RoundedCornerShape(3.dp))
                                                .background(Color(0xFF5F61F0))
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.Gray))
                        Spacer(modifier = Modifier.size(6.dp))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.Gray))
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Buttons
                    Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onSkipClick) {
                            Text(
                                    text = stringResource(R.string.onboarding_skip),
                                    color = Color.Gray,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                            )
                        }

                        Button(
                                onClick = onNextClick,
                                shape = RoundedCornerShape(8.dp),
                                colors =
                                        ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF5F61F0)
                                        ),
                                modifier = Modifier.height(48.dp)
                        ) {
                            Text(
                                    text = stringResource(R.string.onboarding_next),
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
