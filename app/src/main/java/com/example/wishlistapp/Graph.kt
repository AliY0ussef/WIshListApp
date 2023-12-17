package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.database.WishDataBase
import com.example.wishlistapp.database.WishRepo

object Graph {
    private lateinit var dataBase : WishDataBase

    val wisRepo by lazy {
        WishRepo(wishDao = dataBase.wishDao())
    }

    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, WishDataBase::class.java, "wishList.db").build()
    }
}