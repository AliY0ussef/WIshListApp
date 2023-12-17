package com.example.wishlistapp.ui

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wishlistapp.R
import com.example.wishlistapp.Screen
import com.example.wishlistapp.database.data.Wish
import com.example.wishlistapp.wishviewmodel.WishViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    wishViewModel: WishViewModel = viewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = { TopBarView(title = stringResource(id = R.string.home_wish)) },
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddUpdateScreen.route + "/0L")
                },
                modifier = Modifier.padding(all = 10.dp),
                backgroundColor = colorResource(id = R.color.teal_700),
                contentColor = colorResource(id = R.color.white)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        val wishList = wishViewModel.getAllWishes.collectAsState(initial = listOf())
        //val wishList = DummyWish.wishList //this is for the dummy wishList

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { wish -> wish.id }) { wish ->

                val dismissState = rememberDismissState(
                    confirmStateChange = {dismissValue->
                        if(dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart){
                            wishViewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    directions = setOf(DismissDirection.EndToStart),
                    state = dismissState,
                    background = {
                        val colour by animateColorAsState(
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                                colorResource(id = R.color.Red)
                            } else {
                                Color.Transparent
                            },
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(colour).padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White
                            )
                        }
                    },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddUpdateScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WishItem(
    wish : Wish,
    onClick : () -> Unit
    ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.white),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = wish.wishName,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = wish.wishDescription,
                color = colorResource(id = R.color.black)
            )
        }
    }
}