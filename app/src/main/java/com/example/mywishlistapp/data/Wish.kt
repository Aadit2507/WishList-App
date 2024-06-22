package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(
        autoGenerate = true
    )
    val id :  Long = 0L,
    @ColumnInfo(name = "wish_title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")
    val description : String = ""
)


    object DummyWish{
        val wishList = listOf(
            Wish(title  = "Google Watich ", description = "buy a google Watch "),
            Wish(title  = "sci fi Book ", description = "read a book "),
            Wish(title  = "watch anime ", description = "watch 5 ep of demon slayer"),
            Wish(title  = "clean room", description = "osjflisjfla fafasfak aio;sfk;fk ")
            )
}