package com.example.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.Linker
import com.example.test.data.NewsResult
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){

    val news: LiveData<NewsResult> = MutableLiveData<NewsResult>().apply{
        viewModelScope.launch {
            value = Linker.api.getDaliyInfo()
        }
    }
}