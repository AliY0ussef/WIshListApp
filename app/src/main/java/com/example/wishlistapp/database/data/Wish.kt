package com.example.wishlistapp.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-name")
    val wishName : String = "",
    @ColumnInfo(name = "wish-description")
    val wishDescription : String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(
            wishName="Google Watch 2",
            wishDescription =  "An android Watch"
        ),
        Wish(
            wishName = "Oculus Quest 2",
            wishDescription = "A VR headset for playing games"
        ),
        Wish(
            wishName = "A Sci-fi, Book",
            wishDescription = "A science friction book from any best seller"
        ),
        Wish(
            wishName = "Bean bag",
            wishDescription = "A comfy bean bag to substitute for a chair"
        )
    )
}
