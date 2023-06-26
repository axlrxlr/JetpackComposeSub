package com.mizu.jetpackcomposesub

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mizu.jetpackcomposesub.database.FavoriteAnime
import com.mizu.jetpackcomposesub.database.FavoriteViewModel
import com.mizu.jetpackcomposesub.ui.components.FavoriteListItem


@Composable
fun MyFavoriteList(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    componentActivity: ComponentActivity,
){
    val data = remember { mutableStateOf(listOf<FavoriteAnime>()) }

    LaunchedEffect(true) {
        favoriteViewModel.getFavoriteAnime()?.observe(componentActivity) { favorites ->
            data.value = favorites
        }
    }
    Scaffold(
        topBar = { TopBarFavorite(navController = navController)}
    ) {
        if(data.value.isNotEmpty()){
            LazyColumn(modifier = Modifier.padding(it)){
                itemsIndexed(data.value){ _, item ->
                    FavoriteListItem(data = item, navController = navController, favoriteViewModel = favoriteViewModel, componentActivity = componentActivity)
                }
            }
        }else{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.empty_list),
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFavorite(
    navController: NavController
){
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = colorResource(id = R.color.blue), // Replace Color.Red with your desired background color
        titleContentColor = Color.White, // Replace Color.White with your desired content color
        actionIconContentColor = Color.White,
        navigationIconContentColor = Color.White
    )
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = stringResource(R.string.your_favorite), fontWeight = FontWeight.SemiBold)
        },
        colors = topAppBarColors,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}