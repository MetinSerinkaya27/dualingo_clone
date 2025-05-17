package com.example.proje

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class WordViewModel (application: Application) : AndroidViewModel(application) {

    private val dao = WordDatabase.getDatabase(application).WordDao()

    private val _wordList = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>> get() = _wordList

    fun refreshWords() {
        viewModelScope.launch {
            _wordList.postValue(dao.getAllWordsNow()) // suspend fonksiyon
        }
    }
}