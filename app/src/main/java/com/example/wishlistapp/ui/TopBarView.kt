package com.example.wishlistapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wishlistapp.R

@Composable
fun TopBarView(
    title : String,
    onBackNavigation : () -> Unit = {}
){
    val navigationIcon : (@Composable () -> Unit)? =
        if(!title.contains(stringResource(id = R.string.home_wish))) {
            {
                IconButton(
                    onClick = { onBackNavigation() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colorResource(id = R.color.white)
                    )
                }
            }
        } else{
            null
        }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(5.dp)
            )
        },
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.teal_700),
        navigationIcon = navigationIcon
    )
}