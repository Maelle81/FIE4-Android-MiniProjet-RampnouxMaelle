package com.example.firstapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen(WindowClass: WindowSizeClass, navController: NavController) {
    when (WindowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(Modifier.fillMaxSize()) {
                Profil(navController)
            }
        }
        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PhotoProfil()
                    Texte()
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Contact()
                    Spacer(Modifier.height(30.dp))
                    Bouton(navController)
                }
            }
        }
    }
}

@Composable
fun Profil(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                PhotoProfil()
                Texte()
            }
            Spacer(Modifier.height(50.dp))
            Contact()
            Spacer(Modifier.height(150.dp))
            Bouton(navController)
        }


    }

}

@Composable
fun Texte() {

    Text(
        text = "Anne Onyme",
        style = MaterialTheme.typography.h4
    )
    Text(
        text = "Maître de conférence en informatique",
        style = MaterialTheme.typography.subtitle1
    )
    Text(
        text = "Ecole d'ingénieur en informatique",
        style = MaterialTheme.typography.subtitle1,
        fontStyle = FontStyle.Italic
    )

}

@Composable
fun PhotoProfil() {
    Image(
        painterResource(R.drawable.photoprofil2),
        contentDescription = "Photo de profil",
        modifier = Modifier
            .size(137.dp)
            .clip(CircleShape),

        )
}

@Composable
fun Contact() {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painterResource(id = R.drawable.gmail),
                contentDescription = "gmail",
                modifier = Modifier.size(20.dp)

            )
            Text(
                text = "anne.onym@gmail.com",
                style = MaterialTheme.typography.body1

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painterResource(id = R.drawable.linkedin),
                contentDescription = "linkedin",
                modifier = Modifier.size(20.dp)

            )
            Text(
                text = "www.linkedin.com/in/anne-onym",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun Bouton(navController: NavController) {
    Button(onClick = { navController.navigate("Films") })
    {
        Text(text = "Démarrer")
    }
}

