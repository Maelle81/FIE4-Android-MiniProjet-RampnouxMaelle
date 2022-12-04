package com.example.firstapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SerieDetails(navController: NavController, viewModel: MainViewModel, id: Int) {
    val urlImg = "https://image.tmdb.org/t/p/w1280/"
    val serie by viewModel.serie.collectAsState()

    viewModel.detailsSerie(id)

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .height(584.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                    item {
                        Column(verticalArrangement = Arrangement.SpaceAround) {
                            Surface(
                                shape = RoundedCornerShape(
                                    bottomEnd = 10.dp,
                                    bottomStart = 10.dp
                                )
                            ) {

                                AsyncImage(
                                    model = urlImg + serie.backdrop_path,
                                    contentDescription = "Affiche" + serie.name,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                Text(
                                    text = serie.name,
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                                Text(text = serie.first_air_date)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(color = MaterialTheme.colors.secondary)

                                ) {
                                    LazyRow() {
                                        items(serie.genres) { genre ->
                                            Text(text = genre.name + " ")
                                        }
                                    }
                                }

                            }
                            Spacer(Modifier.height(10.dp))
                            SynospisSerie(viewModel)
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "Têtes d'affiche",
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.primaryVariant
                            )
                            CastSerie(viewModel)
                        }
                    }
                }

            }
        }

    )
}


@Composable
fun SynospisSerie(viewModel: MainViewModel) {
    val serie by viewModel.serie.collectAsState()

    Column() {
        Text(
            text = "Synospis",
            style = MaterialTheme.typography.h6
        )
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)

        ) {
            Text(text = serie.overview)
        }
    }

}

@Composable
fun CastSerie(viewModel: MainViewModel) {

    val serie by viewModel.serie.collectAsState()
    val urlImgprofile = "https://image.tmdb.org/t/p/h632/"
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)

    ) {
        items(serie.credits.cast) { cast ->
            Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = urlImgprofile + cast.profile_path,
                        contentDescription = "Image de " + cast.name
                    )
                    Text(
                        text = cast.name,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                    )
                    Text(
                        text = cast.character,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                        fontStyle = FontStyle.Italic
                    )
                }

            }

        }
    }

}