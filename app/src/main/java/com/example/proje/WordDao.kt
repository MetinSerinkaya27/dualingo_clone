package com.example.proje

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Insert
    suspend fun insert(Word:Word)

    @Query("DELETE FROM Word")
    suspend fun deleteAll()

    @Query("SELECT * FROM Word ORDER BY WordID ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM Word ORDER BY WordID ASC")
    suspend fun getAllWordsNow(): List<Word>

    @Query("SELECT*FROM Word ORDER BY RANDOM() LIMIT :limit")
    suspend fun getwordrand(limit:Int):List<Word>

    @Delete
    suspend fun delete(Word:Word)

    @Query("SELECT *FROM Word WHERE EngWordName=:engwordname LIMIT 1")
    suspend fun find(engwordname:String) :Word?

    @Query("SELECT * FROM Word WHERE LENGTH(EngWordName) = 5")
    suspend fun getWordsForWordle() : List<Word>
}