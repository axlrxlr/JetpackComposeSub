package com.mizu.jetpackcomposesub

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mizu.jetpackcomposesub.api.DetailResponse
import com.mizu.jetpackcomposesub.database.FavoriteViewModel
import com.mizu.jetpackcomposesub.viewmodel.AnimeViewModel


@Composable
fun MyAnimeDetail(
    modifier: Modifier = Modifier,
    viewModel: AnimeViewModel = viewModel(modelClass = AnimeViewModel::class.java),
    id: Int,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    componentActivity : ComponentActivity
){
    viewModel.getAnimeDetail(id)
    var liked:Boolean by remember { mutableStateOf(false) }
    favoriteViewModel.getFavoriteAnime()?.observe(componentActivity){
        liked = it.any{e-> e.id == id }
    }
    val animeRating = when(viewModel.animeDetailItem.rating){
        "g" -> "G"
        "pg" -> "PG"
        "pg_13" -> "PG-13"
        "r" -> "R"
        "r+" -> "R+"
        "rx" -> "RX"
        else -> "N/A"
    }
    val animeStatus = when(viewModel.animeDetailItem.status){
        "finished_airing" -> "Completed"
        "currently_airing" -> "On Going"
        "not_yet_aired" -> "Coming Soon"
        else -> "N/A"
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarDetail(navController = navController, liked = liked, favoriteViewModel = favoriteViewModel, data = viewModel.animeDetailItem) }
    ) {
        Column (modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = it.calculateTopPadding())
            .fillMaxSize())
        {
            val painter = rememberAsyncImagePainter(
                viewModel.animeDetailItem.mainPicture.large,
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Card(
                    modifier = Modifier.width(150.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .height(250.dp),
                        painter = painter,
                        contentDescription = viewModel.animeDetailItem.title,
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = viewModel.animeDetailItem.title,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "Status :",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = animeStatus,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "Rating :",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = animeRating,
                        fontSize = 24.sp,
                    )

                }

            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Genre :",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = viewModel.animeDetailItem.genres.joinToString { genre -> genre.name },
                    fontSize = 24.sp,
                    lineHeight = 25.sp
                )
                Divider(
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Synopsis :",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = viewModel.animeDetailItem.synopsis,
                    fontSize = 24.sp,
                    lineHeight = 25.sp
                )

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDetail(
    navController: NavController,
    liked: Boolean,
    favoriteViewModel: FavoriteViewModel,
    data: DetailResponse
){
    var likedState = liked
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = colorResource(id = R.color.blue), // Replace Color.Red with your desired background color
        titleContentColor = Color.White, // Replace Color.White with your desired content color
        actionIconContentColor = Color.White,
        navigationIconContentColor = Color.White
    )
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = stringResource(R.string.anime_details), fontWeight = FontWeight.SemiBold)
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
        },
        actions = {
            IconButton(onClick = {
                if(likedState){
                    favoriteViewModel.removeFavorite(data.id)
                }else{
                    favoriteViewModel.addToFavorite(data.id, data.title, data.mainPicture.large)
                }
                likedState = !likedState
            }) {
                Icon(
                    imageVector = if(likedState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Back"
                )
            }
        }
    )
}