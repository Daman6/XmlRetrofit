package com.example.xmlretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.xmlretrofit.ui.theme.RetrofitInstance
import com.example.xmlretrofit.ui.theme.XmlRetrofitTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XmlRetrofitTheme {
                val viewModel: MainViewModel by viewModels()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    CreditCardScreen(viewModel
                    val apiService = RetrofitInstance.apiService
                    runBlocking {
                        val response = apiService.getPosts()
                        if (response.isSuccessful) {
                            val tvGuide = response.body()
                            tvGuide?.let {
                                Log.e("DamanTag","Channel Display Name: ${it.generatorInfoName.toString()}")
                                Log.e("DamanTag","Programme Title: ${it.channel!!.url.toString()}")
                                Log.e("DamanTag","Programme Title: ${it.channel!!.displayname.toString()}")
                                Log.e("DamanTag","Programme Title: ${it.programme!!.get(1).title.toString()}")
//                                Log.e("TAg","Programme Title: ${it.channel!!.id.toString()}")
//                                Log.e("TAg","Programme Sub Title: ${it.programmes.get(0).subTitle}")
//                                Log.e("TAg","Programme Description: ${it.programmes.get(0).desc}")
                            }
                        } else {
                            Log.e("DamanTag","Error: ${response.message()}")
                        }
                    }
                }
            }
        }
    }
}
//@Composable
//fun CreditCardScreen(viewModel: MainViewModel) {
//    val creditCards:List<XMLModel> by viewModel.post.collectAsState(initial = emptyList())
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchPosts()
//    }
//
//    Column {
//
//            // Display the list of credit cards
//            LazyColumn {
//                items(creditCards) { creditCard ->
//                    Text(text = creditCard.language.toString(), color = Color.Black)
//                }
//            }
//
//    }
//}
