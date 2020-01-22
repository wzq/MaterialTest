package com.example.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel(){

//    val news: LiveData<NewsResult> = MutableLiveData<NewsResult>().apply{
//        viewModelScope.launch {
//            value = Linker.api.getDaliyInfo()
//        }
//    }


    val data = MutableLiveData<List<String>>().apply {
        val list = arrayListOf<String>()
        (0..20).forEach { list.add("item-$it") }
        value = list
    }
}