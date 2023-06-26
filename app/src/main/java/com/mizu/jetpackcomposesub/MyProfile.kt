package com.mizu.jetpackcomposesub

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mizu.jetpackcomposesub.ui.theme.JetpackComposeSubTheme

@Composable
fun MyProfile(navController: NavController){
    Scaffold(
        topBar = { TopBarProfile(navController) }
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Spacer(modifier = Modifier.height(16.dp) )
            Image(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.fotodiri),
                contentDescription = "Axel Rhamadani Rusdiansyah",
                contentScale = ContentScale.Crop,)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Axel Rhamadani Rusdiansyah",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "axel.rham.rus@gmail.com",
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MyProfilePreview() {
    JetpackComposeSubTheme {
        MyProfile(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(
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
            Text(text = stringResource(R.string.my_profile), fontWeight = FontWeight.SemiBold)
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