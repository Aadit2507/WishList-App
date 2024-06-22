package com.example.mywishlistapp


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title : String,
    onbackNavigationClicked : ()-> Unit= {}
) {
    val navigationIcon: (@Composable () -> Unit)? = {
        if (!title.contains("WishList")) {
            IconButton(onClick = { onbackNavigationClicked() },Modifier.padding(top = 25.dp,end = 8.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        } else{
            null
        }
    }


    TopAppBar (

        title = {
            Text(text = title ,

                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.white),
                modifier = Modifier.fillMaxWidth()
                    .padding(   top = 25.dp)
                    .heightIn(max = 24.dp).wrapContentWidth(Alignment.Start)
              )

        },
        elevation = 10.dp,
        modifier = Modifier.height(70.dp),
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )
}




















