package com.example.wishlistapp.database

import com.example.wishlistapp.database.data.Wish
import kotlinx.coroutines.flow.Flow

class WishRepo(private val wishDao: WishDao){

    suspend fun addWish(wish : Wish){
        wishDao.addWish(wish)
    }

    suspend fun updateWish(wish : Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish : Wish){
        wishDao.deleteWish(wish)
    }

    fun getAllWishes() : Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWish(id : Long) : Flow<Wish> {
        return wishDao.getAWish(id)
    }
}