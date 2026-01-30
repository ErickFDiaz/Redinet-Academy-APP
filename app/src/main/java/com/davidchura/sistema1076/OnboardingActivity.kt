package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

class OnboardingActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        Sistema1076Theme {
                                OnboardingScreen(
                                        onSkipClick = { navigateToMain() },
                                        onFinishClick = { navigateToMain() }
                                )
                        }
                }
        }

        private fun navigateToMain() {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
        }
}

data class OnboardingPage(
        @DrawableRes val image: Int,
        @StringRes val title: Int,
        @StringRes val description: Int,
        val startColor: Color,
        val endColor: Color
)

@Composable
fun OnboardingScreen(onSkipClick: () -> Unit, onFinishClick: () -> Unit) {
        val pages =
                listOf(
                        OnboardingPage(
                                image = R.drawable.onboarding_illustration_1,
                                title = R.string.onboarding_title_1,
                                description = R.string.onboarding_description_1,
                                startColor = OnboardingStart,
                                endColor = OnboardingEnd
                        ),
                        OnboardingPage(
                                image = R.drawable.onboarding_illustration_2,
                                title = R.string.onboarding_title_2,
                                description = R.string.onboarding_description_2,
                                startColor = Color(0xFFFCECDB),
                                endColor = Color.White
                        ),
                        OnboardingPage(
                                image = R.drawable.onboarding_illustration_3,
                                title = R.string.onboarding_title_3,
                                description = R.string.onboarding_description_3,
                                startColor =
                                        OnboardingStart, // Using default for now as Page 3 is not
                                // specified
                                endColor = OnboardingEnd
                        )
                )

        val pagerState = rememberPagerState(pageCount = { pages.size })
        val scope = rememberCoroutineScope()

        Box(
                modifier =
                        Modifier.fillMaxSize()
                                .background(
                                        Brush.verticalGradient(
                                                colors =
                                                        listOf(
                                                                pages[pagerState.currentPage]
                                                                        .startColor,
                                                                pages[pagerState.currentPage]
                                                                        .endColor
                                                        )
                                        )
                                )
        ) {
                Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                ) {
                        Spacer(modifier = Modifier.weight(1f))

                        HorizontalPager(
                                state = pagerState,
                                modifier =
                                        Modifier.fillMaxWidth()
                                                .weight(8f) // Adjust weight to fit content
                        ) { pageIndex ->
                                val page = pages[pageIndex]
                                Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxSize()
                                ) {
                                        // Illustration
                                        Image(
                                                painter = painterResource(id = page.image),
                                                contentDescription = null, // decorative
                                                modifier = Modifier.fillMaxWidth().height(300.dp)
                                        )
                                }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

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
                                        val currentPage = pages[pagerState.currentPage]

                                        Text(
                                                text = stringResource(currentPage.title),
                                                style = MaterialTheme.typography.headlineSmall,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center,
                                                color = Color.Black
                                        )

                                        Spacer(modifier = Modifier.height(16.dp))

                                        Text(
                                                text = stringResource(currentPage.description),
                                                style = MaterialTheme.typography.bodyMedium,
                                                textAlign = TextAlign.Center,
                                                color = Color.Gray
                                        )

                                        Spacer(modifier = Modifier.height(24.dp))

                                        // Pager Indicators
                                        Row(
                                                horizontalArrangement = Arrangement.Center,
                                                modifier = Modifier.fillMaxWidth()
                                        ) {
                                                repeat(pages.size) { iteration ->
                                                        val color =
                                                                if (pagerState.currentPage ==
                                                                                iteration
                                                                )
                                                                        Color(0xFF5F61F0)
                                                                else Color.Gray
                                                        val width =
                                                                if (pagerState.currentPage ==
                                                                                iteration
                                                                )
                                                                        24.dp
                                                                else 6.dp
                                                        Box(
                                                                modifier =
                                                                        Modifier.size(
                                                                                        width =
                                                                                                width,
                                                                                        height =
                                                                                                6.dp
                                                                                )
                                                                                .clip(
                                                                                        RoundedCornerShape(
                                                                                                3.dp
                                                                                        )
                                                                                )
                                                                                .background(color)
                                                        )
                                                        if (iteration < pages.size - 1)
                                                                Spacer(
                                                                        modifier =
                                                                                Modifier.size(6.dp)
                                                                )
                                                }
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
                                                                text =
                                                                        stringResource(
                                                                                R.string
                                                                                        .onboarding_skip
                                                                        ),
                                                                color = Color.Gray,
                                                                fontSize = 16.sp,
                                                                fontWeight = FontWeight.Normal
                                                        )
                                                }

                                                Button(
                                                        onClick = {
                                                                if (pagerState.currentPage <
                                                                                pages.size - 1
                                                                ) {
                                                                        scope.launch {
                                                                                pagerState
                                                                                        .animateScrollToPage(
                                                                                                pagerState
                                                                                                        .currentPage +
                                                                                                        1
                                                                                        )
                                                                        }
                                                                } else {
                                                                        onFinishClick()
                                                                }
                                                        },
                                                        shape = RoundedCornerShape(8.dp),
                                                        colors =
                                                                ButtonDefaults.buttonColors(
                                                                        containerColor =
                                                                                Color(0xFF5F61F0)
                                                                ),
                                                        modifier = Modifier.height(48.dp)
                                                ) {
                                                        Text(
                                                                text =
                                                                        stringResource(
                                                                                R.string
                                                                                        .onboarding_next
                                                                        ),
                                                                color = Color.White,
                                                                fontSize = 16.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                modifier =
                                                                        Modifier.padding(
                                                                                horizontal = 16.dp
                                                                        )
                                                        )
                                                }
                                        }
                                }
                        }
                }
        }
}
