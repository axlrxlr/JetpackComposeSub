package com.mizu.jetpackcomposesub

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.util.query
import com.mizu.jetpackcomposesub.api.DataItem
import com.mizu.jetpackcomposesub.api.MainPicture
import com.mizu.jetpackcomposesub.api.Node
import com.mizu.jetpackcomposesub.api.Ranking
import com.mizu.jetpackcomposesub.database.FavoriteViewModel
import com.mizu.jetpackcomposesub.ui.components.AnimeListItem
import com.mizu.jetpackcomposesub.ui.theme.JetpackComposeSubTheme
import com.mizu.jetpackcomposesub.viewmodel.AnimeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAnimeList(
    modifier: Modifier = Modifier,
    animeList: List<DataItem>,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    componentActivity: ComponentActivity,
    animeViewModel: AnimeViewModel
){
    var searchQuery by remember { mutableStateOf("") }
    var onSearchMode by remember { mutableStateOf(false) }
    val currentTitle = if (onSearchMode) {
        "Anime by name '$searchQuery'"
    } else {
        "Currently Airing"
    }

    Scaffold(
        topBar = { TopBar(navController = navController)}
    ) {
        LazyColumn(modifier = Modifier.padding(it)){
            item {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = {newQuery->
                        searchQuery = newQuery
                    },
                    onSearch = { text ->
                        onSearchMode = true
                        animeViewModel.searchAnime(text)
                    },
                    active = false,
                    onActiveChange = {},
                    leadingIcon ={
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    placeholder = {
                        Text(stringResource(R.string.search_anime))
                    },
                    shape = MaterialTheme.shapes.large,
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                ) {

                }
            }
            item {
                Text(
                    modifier = modifier
                        .padding(start = 10.dp, top = 10.dp),
                    text = currentTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            if(!onSearchMode) {

                itemsIndexed(animeList){ _, item ->
                    AnimeListItem(data = item.node, navController = navController, favoriteViewModel = favoriteViewModel, componentActivity = componentActivity)
                }
            }else{
                itemsIndexed(animeViewModel.animeListItem){ _, item ->
                    AnimeListItem(data = item.node, navController = navController, favoriteViewModel = favoriteViewModel, componentActivity = componentActivity)
                }
            }
        }
    }

}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyAnimeListPreview() {
    JetpackComposeSubTheme {
        MyAnimeList(animeList = listOf(DataItem(node = Node(id = 1, title = "Judul Anime", mainPicture = MainPicture("","")), ranking = Ranking(1))), navController = rememberNavController(), favoriteViewModel = viewModel(), componentActivity = ComponentActivity(), animeViewModel = viewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
){

    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = colorResource(id = R.color.blue), // Replace Color.Red with your desired background color
        titleContentColor = Color.White, // Replace Color.White with your desired content color
        actionIconContentColor = Color.White,
    )

    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = stringResource(R.string.my_anime_list), fontWeight = FontWeight.SemiBold)
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("profile")
            }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Profil")
            }
            IconButton(onClick = {
                navController.navigate("profile")
            }) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Profil")
            }

        },
        colors = topAppBarColors

    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    JetpackComposeSubTheme {

    }
}