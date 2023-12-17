package com.example.wishlistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishlistapp.database.data.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish : Wish)

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    @Query("Select * from `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Query("Select * from `wish-table` where id=:id")
    abstract fun getAWish(id: Long) : Flow<Wish>
}