package com.example.wishlistapp.ui

import androidx.compose.runtime.Composable
import com.example.wishlistapp.wishviewmodel.WishViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlistapp.Screen

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination =  Screen.HomeScreen.route
        ){

        composable(Screen.HomeScreen.route){
            HomeView(viewModel, navController)
        }

        composable(Screen.AddUpdateScreen.route + "/{id}",
            arguments = listOf(
                    navArgument("id"){
                        type = NavType.LongType
                        defaultValue = 0L
                        nullable = false
                    }
                )
            ){entry ->
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            AddUpdateView(id = id, viewModel = viewModel, navController = navController)
        }
    }
}