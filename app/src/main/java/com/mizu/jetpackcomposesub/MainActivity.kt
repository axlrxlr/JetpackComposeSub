package com.mizu.jetpackcomposesub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mizu.jetpackcomposesub.database.FavoriteViewModel
import com.mizu.jetpackcomposesub.ui.theme.JetpackComposeSubTheme
import com.mizu.jetpackcomposesub.viewmodel.AnimeViewModel

class MainActivity : ComponentActivity() {

    private val animeViewModel by viewModels<AnimeViewModel>()
    private lateinit var favoriteViewModel : FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSubTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
                val componentActivity = this
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    animeViewModel.getAnimeList()
                    NavHost(navController = navController, startDestination = "listAnime"){
                        composable("listAnime"){
                            MyAnimeList(animeList = animeViewModel.animeListItem, navController = navController, favoriteViewModel = favoriteViewModel, componentActivity = componentActivity, animeViewModel = animeViewModel)
                        }
                        composable("profile"){
                            MyProfile(navController = navController)
                        }
                        composable("detailAnime/{id}", arguments = listOf(navArgument("id"){type= NavType.IntType})){
                            navBackStackEntry -> MyAnimeDetail(id = navBackStackEntry.arguments?.getInt("id")!!, navController = navController)
                        }
                    }

                }
            }
        }
    }
}