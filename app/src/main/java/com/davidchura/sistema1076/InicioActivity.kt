package com.davidchura.sistema1076

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.davidchura.sistema1076.pages.*
import com.davidchura.sistema1076.ui.theme.Sistema1076Theme

data class MenuItemOp(
        val title: String,
        val icon: ImageVector,
        val tint: Color,
        val badge: String? = null,
        val onClick: () -> Unit
)

data class MenuSectionOp(val title: String, val items: List<MenuItemOp>)

class InicioActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()

                setContent {
                        var isDarkMode by remember { mutableStateOf(false) }

                        Sistema1076Theme {
                                val menuSections =
                                        listOf(
                                                MenuSectionOp(
                                                        title = "General",
                                                        items =
                                                                listOf(
                                                                        MenuItemOp(
                                                                                "Cursos",
                                                                                Icons.Filled
                                                                                        .ShoppingCart,
                                                                                Color(0xFF673AB7),
                                                                                "Popular"
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                TiendaActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        },
                                                                        MenuItemOp(
                                                                                "Clientes",
                                                                                Icons.Filled.Person,
                                                                                Color(0xFF3F51B5)
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                ClientesActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        },
                                                                        MenuItemOp(
                                                                                "Proveedores",
                                                                                Icons.Filled
                                                                                        .AccountBox,
                                                                                Color(0xFF009688)
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                ProveedoresActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        }
                                                                )
                                                ),
                                                MenuSectionOp(
                                                        title = "Comunidad & Personal",
                                                        items =
                                                                listOf(
                                                                        MenuItemOp(
                                                                                "Directores",
                                                                                Icons.Filled
                                                                                        .Notifications,
                                                                                Color(0xFFE91E63)
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                DirectoresActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        },
                                                                        MenuItemOp(
                                                                                "Empleados",
                                                                                Icons.Filled.Face,
                                                                                Color(0xFF2196F3)
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                EmpleadosActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        },
                                                                        MenuItemOp(
                                                                                "Instructores",
                                                                                Icons.Filled.Star,
                                                                                Color(0xFF00BCD4)
                                                                        ) {
                                                                                startActivity(
                                                                                        Intent(
                                                                                                this@InicioActivity,
                                                                                                com.davidchura
                                                                                                                .sistema1076
                                                                                                                .pages
                                                                                                                .InstructoresActivity::class
                                                                                                        .java
                                                                                        )
                                                                                )
                                                                        }
                                                                )
                                                ),
                                                MenuSectionOp(
                                                        title = "Configuración",
                                                        items =
                                                                listOf(
                                                                        MenuItemOp(
                                                                                "Salir",
                                                                                Icons.Filled.Close,
                                                                                Color(0xFFF44336)
                                                                        ) { finish() }
                                                                )
                                                )
                                        )

                                // The Scaffold replaces the previous main view wrapper keeping
                                // colors consistent and adding the bottom bar.
                                Scaffold(
                                        bottomBar = {
                                                NavigationBar(
                                                        containerColor =
                                                                MaterialTheme.colorScheme.surface,
                                                        tonalElevation = 8.dp
                                                ) {
                                                        NavigationBarItem(
                                                                icon = {
                                                                        Icon(
                                                                                Icons.Filled.Home,
                                                                                contentDescription =
                                                                                        "Inicio"
                                                                        )
                                                                },
                                                                label = { Text("Inicio") },
                                                                selected = true,
                                                                onClick = {}
                                                        )
                                                        NavigationBarItem(
                                                                icon = {
                                                                        Icon(
                                                                                Icons.Filled.Star,
                                                                                contentDescription =
                                                                                        "Aprendizaje"
                                                                        )
                                                                },
                                                                label = { Text("Aprendizaje") },
                                                                selected = false,
                                                                onClick = {}
                                                        )
                                                        NavigationBarItem(
                                                                icon = {
                                                                        Icon(
                                                                                Icons.Filled.Person,
                                                                                contentDescription =
                                                                                        "Perfil"
                                                                        )
                                                                },
                                                                label = { Text("Perfil") },
                                                                selected = false,
                                                                onClick = {}
                                                        )
                                                }
                                        }
                                ) { innerPadding ->
                                        LazyColumn(
                                                modifier =
                                                        Modifier.fillMaxSize()
                                                                .padding(innerPadding)
                                                                .background(
                                                                        MaterialTheme.colorScheme
                                                                                .background
                                                                )
                                        ) {
                                                item {
                                                        // Header
                                                        Column {
                                                                Spacer(
                                                                        modifier =
                                                                                Modifier.height(
                                                                                        24.dp
                                                                                )
                                                                )
                                                                Row(
                                                                        verticalAlignment =
                                                                                Alignment
                                                                                        .CenterVertically,
                                                                        modifier =
                                                                                Modifier.fillMaxWidth()
                                                                                        .padding(
                                                                                                horizontal =
                                                                                                        16.dp,
                                                                                                vertical =
                                                                                                        24.dp
                                                                                        )
                                                                ) {
                                                                        AsyncImage(
                                                                                model =
                                                                                        "https://blobacademy-cdn-ageydhh8cdb8ewck.a03.azurefd.net/redinetacademy/WebAcademyAll/Login_RedinetAcademy_light.png",
                                                                                contentDescription =
                                                                                        "Profile",
                                                                                contentScale =
                                                                                        ContentScale
                                                                                                .Crop,
                                                                                modifier =
                                                                                        Modifier.size(
                                                                                                        50.dp
                                                                                                )
                                                                                                .clip(
                                                                                                        CircleShape
                                                                                                )
                                                                                                .background(
                                                                                                        Color.LightGray
                                                                                                )
                                                                        )
                                                                        Spacer(
                                                                                modifier =
                                                                                        Modifier.width(
                                                                                                12.dp
                                                                                        )
                                                                        )
                                                                        Column(
                                                                                modifier =
                                                                                        Modifier.weight(
                                                                                                1f
                                                                                        )
                                                                        ) {
                                                                                Text(
                                                                                        text =
                                                                                                "Hola,",
                                                                                        color =
                                                                                                Color.Gray,
                                                                                        fontSize =
                                                                                                14.sp
                                                                                )
                                                                                Text(
                                                                                        text =
                                                                                                stringResource(
                                                                                                        R.string
                                                                                                                .title_activity_inicio
                                                                                                ),
                                                                                        fontWeight =
                                                                                                FontWeight
                                                                                                        .Bold,
                                                                                        fontSize =
                                                                                                18.sp,
                                                                                        color =
                                                                                                MaterialTheme
                                                                                                        .colorScheme
                                                                                                        .onBackground
                                                                                )
                                                                        }
                                                                        IconButton(
                                                                                onClick = {},
                                                                                modifier =
                                                                                        Modifier.background(
                                                                                                        Color(
                                                                                                                0xFF673AB7
                                                                                                        ),
                                                                                                        CircleShape
                                                                                                )
                                                                                                .size(
                                                                                                        40.dp
                                                                                                )
                                                                        ) {
                                                                                Icon(
                                                                                        imageVector =
                                                                                                Icons.Filled
                                                                                                        .ArrowForward,
                                                                                        contentDescription =
                                                                                                "Go",
                                                                                        tint =
                                                                                                Color.White,
                                                                                        modifier =
                                                                                                Modifier.size(
                                                                                                        20.dp
                                                                                                )
                                                                                )
                                                                        }
                                                                }
                                                        }
                                                }

                                                item {
                                                        // Dark Mode Toggle Switch
                                                        Card(
                                                                shape = RoundedCornerShape(16.dp),
                                                                colors =
                                                                        CardDefaults.cardColors(
                                                                                containerColor =
                                                                                        if (isDarkMode
                                                                                        )
                                                                                                Color.DarkGray
                                                                                        else
                                                                                                Color(
                                                                                                        0xFFF3F4F6
                                                                                                )
                                                                        ),
                                                                modifier =
                                                                        Modifier.fillMaxWidth()
                                                                                .padding(
                                                                                        horizontal =
                                                                                                16.dp,
                                                                                        vertical =
                                                                                                8.dp
                                                                                )
                                                        ) {
                                                                Row(
                                                                        verticalAlignment =
                                                                                Alignment
                                                                                        .CenterVertically,
                                                                        horizontalArrangement =
                                                                                Arrangement
                                                                                        .SpaceBetween,
                                                                        modifier =
                                                                                Modifier.fillMaxWidth()
                                                                                        .padding(
                                                                                                horizontal =
                                                                                                        20.dp,
                                                                                                vertical =
                                                                                                        8.dp
                                                                                        )
                                                                ) {
                                                                        Text(
                                                                                text =
                                                                                        "Modo Oscuro",
                                                                                fontWeight =
                                                                                        FontWeight
                                                                                                .Bold,
                                                                                fontSize = 16.sp,
                                                                                color =
                                                                                        if (isDarkMode
                                                                                        )
                                                                                                Color.White
                                                                                        else
                                                                                                Color.DarkGray
                                                                        )
                                                                        Switch(
                                                                                checked =
                                                                                        isDarkMode,
                                                                                onCheckedChange = {
                                                                                        isDarkMode =
                                                                                                it
                                                                                },
                                                                                colors =
                                                                                        SwitchDefaults
                                                                                                .colors(
                                                                                                        checkedThumbColor =
                                                                                                                Color.White,
                                                                                                        checkedTrackColor =
                                                                                                                Color.Gray,
                                                                                                )
                                                                        )
                                                                }
                                                        }
                                                }

                                                // Rendering the predefined menu groups, just like
                                                // the conceptual image.
                                                menuSections.forEach { section ->
                                                        item {
                                                                Text(
                                                                        text = section.title,
                                                                        fontWeight =
                                                                                FontWeight.Bold,
                                                                        fontSize = 18.sp,
                                                                        color =
                                                                                MaterialTheme
                                                                                        .colorScheme
                                                                                        .onBackground,
                                                                        modifier =
                                                                                Modifier.padding(
                                                                                        start =
                                                                                                16.dp,
                                                                                        top = 24.dp,
                                                                                        bottom =
                                                                                                8.dp
                                                                                )
                                                                )
                                                        }

                                                        itemsIndexed(section.items) { index, item ->
                                                                Row(
                                                                        verticalAlignment =
                                                                                Alignment
                                                                                        .CenterVertically,
                                                                        modifier =
                                                                                Modifier.fillMaxWidth()
                                                                                        .clickable {
                                                                                                item.onClick()
                                                                                        }
                                                                                        .padding(
                                                                                                horizontal =
                                                                                                        16.dp,
                                                                                                vertical =
                                                                                                        16.dp
                                                                                        )
                                                                ) {
                                                                        Icon(
                                                                                imageVector =
                                                                                        item.icon,
                                                                                contentDescription =
                                                                                        item.title,
                                                                                tint = item.tint,
                                                                                modifier =
                                                                                        Modifier.size(
                                                                                                24.dp
                                                                                        )
                                                                        )
                                                                        Spacer(
                                                                                modifier =
                                                                                        Modifier.width(
                                                                                                16.dp
                                                                                        )
                                                                        )
                                                                        Text(
                                                                                text = item.title,
                                                                                fontWeight =
                                                                                        FontWeight
                                                                                                .Medium,
                                                                                fontSize = 16.sp,
                                                                                color =
                                                                                        MaterialTheme
                                                                                                .colorScheme
                                                                                                .onBackground,
                                                                                modifier =
                                                                                        Modifier.weight(
                                                                                                1f
                                                                                        )
                                                                        )
                                                                        if (item.badge != null) {
                                                                                Box(
                                                                                        modifier =
                                                                                                Modifier.background(
                                                                                                                Color(
                                                                                                                        0xFFFF7043
                                                                                                                ),
                                                                                                                RoundedCornerShape(
                                                                                                                        12.dp
                                                                                                                )
                                                                                                        )
                                                                                                        .padding(
                                                                                                                horizontal =
                                                                                                                        10.dp,
                                                                                                                vertical =
                                                                                                                        4.dp
                                                                                                        ),
                                                                                        contentAlignment =
                                                                                                Alignment
                                                                                                        .Center
                                                                                ) {
                                                                                        Text(
                                                                                                text =
                                                                                                        item.badge,
                                                                                                color =
                                                                                                        Color.White,
                                                                                                fontSize =
                                                                                                        12.sp,
                                                                                                fontWeight =
                                                                                                        FontWeight
                                                                                                                .Bold
                                                                                        )
                                                                                }
                                                                        }
                                                                }
                                                                // Render divider
                                                                if (index < section.items.size - 1
                                                                ) {
                                                                        Box(
                                                                                modifier =
                                                                                        Modifier.fillMaxWidth()
                                                                                                .padding(
                                                                                                        horizontal =
                                                                                                                16.dp
                                                                                                )
                                                                                                .height(
                                                                                                        1.dp
                                                                                                )
                                                                                                .background(
                                                                                                        Color.LightGray
                                                                                                                .copy(
                                                                                                                        alpha =
                                                                                                                                0.3f
                                                                                                                )
                                                                                                )
                                                                        )
                                                                }
                                                        }
                                                }

                                                item { Spacer(modifier = Modifier.height(32.dp)) }
                                        }
                                }
                        } // Tema
                } // SetContent
        }
}
