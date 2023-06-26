package com.mizu.jetpackcomposesub

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mizu.jetpackcomposesub.ui.theme.JetpackComposeSubTheme
import com.mizu.jetpackcomposesub.viewmodel.AnimeViewModel


@Composable
fun MyAnimeDetail(
    modifier: Modifier = Modifier,
    viewModel: AnimeViewModel = viewModel(modelClass = AnimeViewModel::class.java),
    id: Int,
    navController: NavController
){
    viewModel.getAnimeDetail(id)
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
        topBar = { TopBarDetail(navController = navController) }
    ) {
        Column (modifier = modifier
            .padding(top = it.calculateTopPadding())
            .height(250.dp)){
            val painter = rememberAsyncImagePainter(
                viewModel.animeDetailItem.mainPicture.large,
            )
            Row(
                modifier = Modifier.padding(8.dp)
            ){
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

        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyAnimeDetailPreview() {
    JetpackComposeSubTheme {
        MyAnimeDetail(id = 52034, navController = rememberNavController())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDetail(
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
        }
    )
}