package com.example.wishlistapp.wishviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.Graph
import com.example.wishlistapp.database.WishRepo
import com.example.wishlistapp.database.data.Wish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepo: WishRepo = Graph.wisRepo
) : ViewModel(){

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newTitle : String){
        wishTitleState = newTitle
    }

    fun onWishDescriptionChanged(newDescription : String){
        wishDescriptionState = newDescription
    }

    fun addWish(wish : Wish){
        viewModelScope.launch {
            wishRepo.addWish(wish)
        }
    }

    fun updateWish(wish : Wish){
        viewModelScope.launch {
            wishRepo.updateWish(wish)
        }
    }

    fun deleteWish(wish : Wish){
        viewModelScope.launch {
            wishRepo.deleteWish(wish)
        }
    }

    lateinit var getAllWishes : Flow<List<Wish>>
    init {
        viewModelScope.launch {
            getAllWishes = wishRepo.getAllWishes()
        }
    }

    fun getAWish(id : Long) : Flow<Wish>{
        return wishRepo.getAWish(id)
    }
}