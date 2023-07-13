package com.example.xmlretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlretrofit.models.Tv
import com.example.xmlretrofit.repository.MainRepository
import com.example.xmlretrofit.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel()
{
    private val  _res = MutableStateFlow<Resource<Tv>>(Resource.loading(null))
    val res :StateFlow<Resource<Tv>> = _res

    init {
        getTvProgram()
    }
    private fun getTvProgram() = viewModelScope.launch {
        try {
            val response = mainRepository.getTvProgram()
            if (response.isSuccessful){
                _res.value = Resource.success(response.body())
            }else{
                _res.value = Resource.error(response.errorBody().toString(),response.body())
            }
        }catch (e:Exception){
            _res.value = Resource.error(e.localizedMessage!!,null)
        }
    }
}