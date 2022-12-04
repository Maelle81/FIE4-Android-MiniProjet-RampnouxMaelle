package com.example.firstapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val movie = MutableStateFlow(Movie())
    val serie = MutableStateFlow(Serie())
    val apikey = "d17906cc459a7ecbe6215166507b874a"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            movies.value = service.getFilmsParMotCle(apikey, motcle).results
        }
    }

    fun listMovies() {
        viewModelScope.launch {
            movies.value = service.getListesFilms(apikey).results
        }
    }

    fun detailsMovie( id : Int) {
        viewModelScope.launch {
            movie.value = service.getDescriptionFilm(id, apikey)
        }
    }

    fun listSeries() {
        viewModelScope.launch {
            series.value = service.getListesSeries(apikey).results
        }
    }

    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            series.value = service.getSeriesParMotCle(apikey, motcle).results
        }
    }

    fun detailsSerie( id : Int) {
        viewModelScope.launch {
            serie.value = service.getDescriptionSerie(id, apikey)
        }
    }

    fun listActors() {
        viewModelScope.launch {
           actors.value = service.getListesActeurs(apikey).results
        }
    }

    fun searchActors(motcle: String) {
        viewModelScope.launch {
            actors.value = service.getActeursParMotCle(apikey, motcle).results
        }
    }
}
