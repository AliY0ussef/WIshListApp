package com.example.wishlistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishlistapp.database.data.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDataBase : RoomDatabase() {
    abstract fun wishDao() : WishDao
}