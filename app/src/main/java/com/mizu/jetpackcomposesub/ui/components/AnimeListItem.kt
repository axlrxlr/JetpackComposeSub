package com.mizu.jetpackcomposesub.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mizu.jetpackcomposesub.MainActivity
import com.mizu.jetpackcomposesub.R
import com.mizu.jetpackcomposesub.api.MainPicture
import com.mizu.jetpackcomposesub.api.Node
import com.mizu.jetpackcomposesub.database.FavoriteViewModel
import com.mizu.jetpackcomposesub.ui.theme.JetpackComposeSubTheme

@Composable
fun AnimeListItem(
    data: Node,
    modifier: Modifier = Modifier,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    componentActivity: ComponentActivity
){

    var liked:Boolean by remember { mutableStateOf(false) }
    favoriteViewModel.getFavoriteAnime()?.observe(componentActivity){
        liked = it.any{e-> e.id == data.id }
    }

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate("detailAnime/${data.id}")
            },
        elevation = CardDefaults.cardElevation()
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .clickable {
                navController.navigate("detailAnime/${data.id}")
            }
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                BoxWithConstraints {
                    val aspectRatio = maxWidth / maxHeight
                    val painter = rememberAsyncImagePainter(
                        data.mainPicture.large,
                    )
                    Image(
                        painter = painter,
                        contentDescription = data.title,
                        contentScale = if (aspectRatio > 1) {
                            ContentScale.FillWidth
                        } else {
                            ContentScale.FillHeight
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(aspectRatio)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = 600f
                        )
                    )
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clickable {
                    navController.navigate("detailAnime/${data.id}")
                },
                contentAlignment = Alignment.BottomStart
            ){
                Text(text = data.title, style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
            }

            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .clickable {
                    if(liked){
                        favoriteViewModel.removeFavorite(data.id)
                    }else{
                        favoriteViewModel.addToFavorite(data.id, data.title, data.mainPicture.large)
                    }
                    liked = !liked
                }
            ) {
                val tint by animateColorAsState(
                    if (!liked) Color.White
                    else Color.Red
                )
                Icon(
                    if (!liked) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                    contentDescription = "Favorite Button",
                    tint = tint
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeListItemPreview() {
    JetpackComposeSubTheme {
        AnimeListItem(Node(id = 1, title = "Judul Anime", mainPicture = MainPicture("","")), navController = rememberNavController(), favoriteViewModel = viewModel(), componentActivity = ComponentActivity())
    }
}

val AnimeDummy = listOf(
    DummyData(1, R.drawable.oshinoko, "Oshi No Ko"),
    DummyData(2, R.drawable.oshinoko, "Oshi No Ko"),
    DummyData(3, R.drawable.oshinoko, "Oshi No Ko"),
    DummyData(4, R.drawable.oshinoko, "Oshi No Ko"),
    DummyData(5, R.drawable.oshinoko, "Oshi No Ko"),
)

data class DummyData(
    val id : Long,
    val image : Int,
    val title : String,
)