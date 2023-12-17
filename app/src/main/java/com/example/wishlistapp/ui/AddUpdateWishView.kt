package com.example.wishlistapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.R
import com.example.wishlistapp.database.data.Wish
import com.example.wishlistapp.wishviewmodel.WishViewModel
import kotlinx.coroutines.launch

@Composable
fun AddUpdateView(
    id : Long,
    viewModel: WishViewModel,
    navController: NavController
){
    val scaffoldState = rememberScaffoldState()
    val snackMessage = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()

    if(id != 0L){
        val wish = viewModel.getAWish(id).collectAsState(initial = Wish(0, "", ""))
        viewModel.wishTitleState = wish.value.wishName
        viewModel.wishDescriptionState = wish.value.wishDescription
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = {TopBarView(title =
        if(id != 0L) stringResource(id = R.string.update_wish)
            else stringResource(id = R.string.add_wish)
        ) {navController.navigateUp()} },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishEditText(
                value = viewModel.wishTitleState,
                label = "Title",
                onValueChanged = {title ->
                    viewModel.onWishTitleChanged(title)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishEditText(
                value = viewModel.wishDescriptionState,
                label = "Description",
                onValueChanged = {description ->
                    viewModel.onWishDescriptionChanged(description)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                        if(id != 0L){
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    wishName = viewModel.wishTitleState.trim(),
                                    wishDescription = viewModel.wishDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Your Wish has been updated"
                        } else {
                            viewModel.addWish(
                                Wish(
                                    wishName = viewModel.wishTitleState.trim(),
                                    wishDescription = viewModel.wishDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Your Wish has been created"
                        }
                    }else{
                       snackMessage.value = "Please enter a Wish to create"
                    }

                    if(snackMessage.value == "Please enter a Wish to create"){
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        }
                    }else {
                        scope.launch {
                            navController.navigateUp()
                        }
                    }
                }
            ) {
                Text(
                    text = "Add Wish",
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@Composable
fun WishEditText(
    value : String,
    label : String,
    onValueChanged : (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { androidx.compose.material3.Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )
    )
}