package com.musabapps.colorfultodos.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todotable")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val date: Date
)
