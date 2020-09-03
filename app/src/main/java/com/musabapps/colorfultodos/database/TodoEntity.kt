package com.musabapps.colorfultodos.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todotable")
data class TodoEntity(
    val text: String,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
