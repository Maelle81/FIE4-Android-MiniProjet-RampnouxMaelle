package com.example.firstapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Acteurs(viewModel: MainViewModel, navController: NavController) {

    val actors by viewModel.actors.collectAsState()
    val urlImg = "https://image.tmdb.org/t/p/w185/"

    if (actors.isEmpty()) viewModel.listActors()
    Scaffold(
        topBar = { TopBarActeur(viewModel) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            )
            {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    items(actors) { actor ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 8.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = urlImg + actor.profile_path,
                                    contentDescription = "Image de " + actor.name,
                                    modifier = Modifier
                                        .clickable(onClick = {
                                            navController.navigate(
                                                "ActeurDetails"
                                            )
                                        })
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = actor.name,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center
                                )

                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun TopBarActeur(viewModel: MainViewModel) {
    var queryValid by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            if (!queryValid) {
                Text(text = "Fav'App")
            } else {
                TextField(
                    value = query,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { viewModel.searchActors(query) }
                    ),
                    onValueChange = { newText ->
                        query = newText
                    }
                )
            }
        },
        actions = {
            IconButton(onClick = {
                queryValid = !queryValid
            })
            {
                if (queryValid) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }
            }
        },
        modifier = Modifier.padding(bottom = 2.dp)
    )
}