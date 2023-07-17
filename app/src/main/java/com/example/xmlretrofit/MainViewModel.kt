package com.example.xmlretrofit

import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlretrofit.models.Programme
import com.example.xmlretrofit.models.Tv
import com.example.xmlretrofit.repository.MainRepository
import com.example.xmlretrofit.util.Resource
import com.example.xmlretrofit.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _res = MutableStateFlow<Resource<Tv>>(Resource.loading(null))
    val res: StateFlow<Resource<Tv>> = _res

    private val _items = mutableStateListOf<Programme>()
    var items: List<Programme> = _items

    private val _progress = mutableStateOf(0.0f)
    val progress: State<Float> get() = _progress


    init {
        getTvProgram()
        startProgressUpdates()
    }

    private fun getTvProgram() = viewModelScope.launch {
        try {
            val response = mainRepository.getTvProgram()
            if (response.isSuccessful) {
                val tvProgram = response.body()
                tvProgram?.programme?.let {
                    _items.clear()
                    _items.addAll(it)
                }
                _res.value = Resource.success(tvProgram)
            } else {
                _res.value = Resource.error(response.errorBody().toString(), response.body())
            }
        } catch (e: Exception) {
            _res.value = Resource.error(e.localizedMessage!!, null)
        }
    }

    private fun startProgressUpdates() {
        viewModelScope.launch {
            while (true) {
                Log.e("TAG", "startProgressUpdates: running ", )
                val currentTime = getCurrentTime()
                var totalDurationMillis: Long
                val matchingItem = _items.firstOrNull {
                    convertTimeToHumanReadableTime(it.stop) == currentTime
                }

                if (matchingItem != null) {
                    _items.remove(matchingItem)
                    _progress.value = 0.0f
                }

                if (_items.isNotEmpty()) {
                    totalDurationMillis = items.firstOrNull()!!.length * 1000L
                    val progressUpdateIntervalMillis = 1000L

                    val iterations = (totalDurationMillis / progressUpdateIntervalMillis).toInt()
                    val progressIncrement = 1.0f / iterations

                    repeat(iterations) {
                        if (_progress.value < 1.0f) {
                            delay(progressUpdateIntervalMillis)
                            _progress.value += progressIncrement
                        }
                    }
                }

                delay(1000)
            }
        }
    }

    fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return timeFormat.format(currentTime)
    }


    fun convertTimeToHumanReadableTime(time: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val dateTime = inputFormat.parse(time)
        return outputFormat.format(dateTime!!)
    }

}
