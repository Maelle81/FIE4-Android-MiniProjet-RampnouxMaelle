package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.ui.theme.FirstAppTheme

/* clé api: d17906cc459a7ecbe6215166507b874a*/

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val viewmodel: MainViewModel by viewModels()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            FirstAppTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Scaffold(

                        //topBar = {TopBar(viewmodel, currentDestination)},
                        content = { padding ->

                            Column(
                                modifier = Modifier.padding(0.dp)
                            ) {
                                NavHost(
                                    modifier = Modifier.padding(0.dp),
                                    navController = navController,
                                    startDestination = "Screen"
                                )
                                {
                                    composable("Screen") {
                                        Screen(windowSizeClass, navController)
                                    }
                                    composable(route = BottomBarScreen.Films.route) {
                                        Films(viewModel = MainViewModel(), navController)
                                    }
                                    composable(route = BottomBarScreen.Series.route) {
                                        Series(viewModel = MainViewModel(), navController)
                                    }
                                    composable(route = BottomBarScreen.Acteurs.route) {
                                        Acteurs(viewModel = MainViewModel(), navController)
                                    }
                                    composable(
                                        "movie/{id}?append_to_response=credits&language=fr",
                                        arguments = listOf(navArgument("id") {
                                            type = NavType.IntType
                                        })
                                    ) { backStartEntry ->
                                        backStartEntry.arguments?.let {
                                            FilmDetails(
                                                navController,
                                                viewModel = MainViewModel(),
                                                it.getInt("id")
                                            )
                                        }
                                    }
                                    composable(
                                        "tv/{id}?append_to_response=credits&language=fr",
                                        arguments = listOf(navArgument("id") {
                                            type = NavType.IntType
                                        })
                                    ) { backStartEntry ->
                                        backStartEntry.arguments?.let {
                                            SerieDetails(
                                                navController,
                                                viewModel = MainViewModel(),
                                                it.getInt("id")
                                            )
                                        }
                                    }
                                }
                            }

                        },
                        bottomBar = { BottomBar(navController, currentDestination) }
                    )


                }
            }
        }
    }
}

/*
//Malgré mais nombreuses tentatives je n'ai pas réussi faire des recherches depuis le main activity
//le top Bar est implémenté depuis Acteurs,Films et Series.
@Composable
fun TopBarMainActivity(viewModel: MainViewModel, currentDestination: NavDestination?) {
    var queryValid by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val movies by viewModel.movies.collectAsState()


    if (currentDestination != null && currentDestination?.hierarchy?.any { it.route == "Screen" } == false) {
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
            navigationIcon = {
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
            }
        )
    }

}*/



@Composable
fun BottomBar(navController: NavController, currentDestination: NavDestination?) {
    if (currentDestination != null && currentDestination?.hierarchy?.any { it.route == "Screen" } == false) {
        BottomNavigation {
            val screens = listOf(
                BottomBarScreen.Films,
                BottomBarScreen.Series,
                BottomBarScreen.Acteurs,
            )
            screens.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = screen.icon), contentDescription = null) },
                    label = { Text(text = screen.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


