package com.example.firstapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET ("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key")apikey: String, @Query("query") motcle:String) : TmdbResult

    @GET ("trending/movie/week")
    suspend fun getListesFilms(@Query("api_key")apikey: String) : TmdbResult

    @GET("movie/{id}?append_to_response=credits&language=fr")
    suspend fun getDescriptionFilm(@Path("id")id : Int, @Query("api_key")apikey: String) : Movie

    @GET ("trending/tv/week")
    suspend fun getListesSeries(@Query("api_key")apikey: String) : TmdbSerie

    @GET ("search/tv")
    suspend fun getSeriesParMotCle(@Query("api_key")apikey: String, @Query("query") motcle:String) : TmdbSerie

    @GET("tv/{id}?append_to_response=credits&language=fr")
    suspend fun getDescriptionSerie(@Path("id")id : Int, @Query("api_key")apikey: String) : Serie

    @GET ("trending/person/week")
    suspend fun getListesActeurs(@Query("api_key")apikey: String) : TmdbActor

    @GET ("search/person")
    suspend fun getActeursParMotCle(@Query("api_key")apikey: String, @Query("query") motcle:String) : TmdbActor

}