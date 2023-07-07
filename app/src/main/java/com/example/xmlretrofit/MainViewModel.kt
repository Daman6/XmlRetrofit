package com.example.xmlretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel()
{
//    private val repository = Repository()
//
//    private val _posts = MutableStateFlow<List<XMLModel>>(emptyList())
//    val post: StateFlow<List<XMLModel>> = _posts
//
//    fun fetchPosts() {
//        viewModelScope.launch {
//            try {
//                val cards = repository.getPosts()
//                _posts.value = cards
//            } catch (e: Exception) {
//                // Handle error
//                Log.e("sndjsnd",e.message.toString())
//            }
//        }
//    }

}