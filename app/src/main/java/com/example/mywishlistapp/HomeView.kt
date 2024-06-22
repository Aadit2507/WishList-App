package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mywishlistapp.data.DummyWish
import com.example.mywishlistapp.data.Wish



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController : NavController,
    viewModel: WishViewModel

){
    val context = LocalContext.current
    Scaffold (
            topBar = {AppBarView(title = "WishList"  )
                },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                  // Toast.makeText(context, "Add button clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add WishList")
            }
        }


    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishlist.value, key = {wish -> wish.id}) {
                wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                    viewModel.deleteWish(wish)
                    }
                        true
                    }
                )
                SwipeToDismiss(state = dismissState,
                    background ={
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection ==
                                DismissDirection.EndToStart) Color.Red else Color.White
                            , label = ""
                        )
                       val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal  = 20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete WishList",
                                tint = Color.White)
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart ),
                    dismissThresholds = {
                        FractionalThreshold(0.5f)
                    },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                    }
                    }

                    )



                }

            }
        }
    }


@Composable
fun WishItem(wish : Wish , onclick : ()->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
        )
        .clickable {
            onclick()
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 30.dp), // Use cardElevation
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text =wish.title, fontWeight  = FontWeight.ExtraBold )
            Text(text =wish.description )

        }

    }
}
