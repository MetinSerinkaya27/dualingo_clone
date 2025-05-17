package com.example.proje

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Word")
data class Word(
    @PrimaryKey(autoGenerate = true) val WordID:Int=0,
    @NonNull val EngWordName:String,
    @NonNull val TurWordName:String
)
