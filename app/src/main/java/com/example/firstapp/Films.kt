package com.example.firstapp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun Films(viewModel: MainViewModel, navController: NavController) {

    val movies by viewModel.movies.collectAsState()
    val urlImg = "https://image.tmdb.org/t/p/w342/"


    if (movies.isEmpty()) viewModel.listMovies()
    Scaffold(
        topBar = { TopBarMovie(viewModel) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(0.dp)
            )
            {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)

                )
                {
                    items(movies) { movie ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 8.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = urlImg + movie.poster_path,
                                    contentDescription = "Image de " + movie.title,
                                    modifier = Modifier
                                        .clickable(onClick = { navController.navigate("movie/" + movie.id + "?append_to_response=credits") })
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = movie.original_title,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colors.primary
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(color = MaterialTheme.colors.secondary)
                                        .padding(bottom = 2.dp)
                                ) {
                                    Text(
                                        text = " " + movie.release_date + " ",
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun TopBarMovie(viewModel: MainViewModel) {
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
                        onSearch = { viewModel.searchMovies(query) }
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






