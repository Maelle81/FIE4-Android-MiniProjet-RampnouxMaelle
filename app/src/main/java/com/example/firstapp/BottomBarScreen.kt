package com.example.firstapp

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : Int
){
    object Films : BottomBarScreen(
        route = "films",
        title = "Films",
        icon = R.drawable.ic_baseline_movie_24

    )

    object Series : BottomBarScreen(
        route = "series",
        title = "Series",
        icon = R.drawable.ic_baseline_tv_24
    )

    object Acteurs : BottomBarScreen(
        route = "acteurs",
        title = "Acteurs",
        icon = R.drawable.ic_baseline_person_24
    )
}
