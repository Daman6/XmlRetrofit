package com.example.xmlretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.xmlretrofit.models.Programme
import com.example.xmlretrofit.models.Tv
import com.example.xmlretrofit.ui.theme.XmlRetrofitTheme
import com.example.xmlretrofit.util.Resource
import com.example.xmlretrofit.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.security.auth.login.LoginException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XmlRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val resourceState by viewModel.res.collectAsState()
                    ProgramRowItems(resourceState)
//                    val dateString = "2023-07-04T21:46:22.000+0000"
//                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
//                    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//
//                    val dateTime = inputFormat.parse(dateString)
//                    Log.e("TAG", "onCreate: ${dateTime.time}")
//                      MySliderDemo()

                }
            }
        }
    }
}


@Composable
fun ProgramRowItems(resourceState: Resource<Tv>) {
    when (resourceState.status) {
        Status.LOADING -> {
            CircularProgressIndicator()
        }

        Status.SUCCESS -> {
            val tvProgram = resourceState.data!!
            LazyRow {
                items(tvProgram.programme!!) {
                    CardUi(programme = it)
                }
            }
        }

        Status.ERROR -> {
            val errorMessage = resourceState.message
            Text(text = "Error: $errorMessage")
        }
    }
}

@Composable
fun CardUi(programme: Programme, modifier: Modifier = Modifier) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val duration = programme.length / 60
    val cardWidth = when {
        duration >= 50 -> screenWidth * 0.8f
        duration >= 30 -> screenWidth * 0.6f
        else -> screenWidth * 0.5f
    }

//    val date = convertTimestampToDate(programme.start)
//    if (isToday(date)) {
////        if (currentTimeProgrammesOnly(enddate)) {
    Column(Modifier.padding(horizontal = 10.dp)){
        Text(text = convertTimestampToTime(programme.start), color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.width(cardWidth))
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(Color.Red),
            modifier = Modifier.wrapContentSize()
        ) {
            Column(
                Modifier
                    .width(cardWidth)
                    .padding(10.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = programme.title + " :",
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = programme.desc,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Clip
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(imageVector = Icons.Default.Info, contentDescription = "info",Modifier.size(15.dp), tint = Color.White)
                }
            }
        }
    }
//        }
//    }
}


fun convertTimestampToTime(timestamp: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm a ", Locale.getDefault())
    val dateTime = inputFormat.parse(timestamp)
    return outputFormat.format(dateTime!!)
}

fun convertTimestampToDate(timestamp: String): Long {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val dateTime = inputFormat.parse(timestamp)
    return dateTime!!.time
}

fun isToday(timestamp: Long): Boolean {
    val currentcalendar = Calendar.getInstance()
    val programCalendar = Calendar.getInstance()
    programCalendar.timeInMillis = timestamp
    return programCalendar.get(Calendar.DATE) == currentcalendar.get(Calendar.DATE)
}


fun currentTimeProgrammesOnly(timestamp: Long): Boolean {
    val currentcalendar = Calendar.getInstance()
    val programCalendar = Calendar.getInstance()
    programCalendar.timeInMillis = timestamp

    if ( programCalendar.timeInMillis.compareTo(currentcalendar.timeInMillis) == 1 || programCalendar.timeInMillis.compareTo(currentcalendar.timeInMillis) == 0 ) {
        return true
    }
    return false
}


//fun todayProgrammesList(programme: Programme): List<Programme> {
//    val date = convertTimestampToDate(programme.start)
//    val programmList: MutableList<Programme> = mutableListOf()
//    if (isToday(date)) {
//        programmList.add(
//            Programme(
//                programme.start,
//                programme.stop,
//                programme.channel,
//                programme.title,
//                programme.desc,
//                programme.length
//            )
//        )
//    }
//    return programmList
//}
