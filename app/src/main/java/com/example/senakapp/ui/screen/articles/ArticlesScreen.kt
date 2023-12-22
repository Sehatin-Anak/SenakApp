package com.example.senakapp.ui.screen.articles

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.model.DataItemArticles
import com.example.senakapp.ui.components.ArticlesCardItem
import com.example.senakapp.ui.screen.destinations.DetailArticleScreenDestination
import com.example.senakapp.utils.ApiResponse
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ArticlesScreen(navigator: DestinationsNavigator?){
    ArticleContent(navigator = navigator)

}

@Composable
fun ArticleContent(navigator: DestinationsNavigator? ){
    val viewModel = hiltViewModel<ArticlesViewModel>()

    val getArticles by viewModel.articles.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getArticles()
    }

    when(getArticles){
        is ApiResponse.Loading -> {
            CircularProgressIndicator()
        }
        is ApiResponse.Success -> {
            val articles = (getArticles as ApiResponse.Success).data
           LazyColumn{
                items(articles.data.size){
                    ArticlesCardItem(articles.data[it], onClick = {
                        Log.d("ArticlesScreen", "ArticleContent: ${articles.data[it]}")

val data = articles.data[it]

                        navigator?.navigate(DetailArticleScreenDestination(
                            DataItemArticles(
                                articles.data[it].createdAt,
                                articles.data[it].author,
                                articles.data[it].id,
                                articles.data[it].title,
                                articles.data[it].publicationDate,
                                articles.data[it].url,
                                articles.data[it].content,
                                articles.data[it].updatedAt
                            )

                        ))

                    })
                }
           }
        }
        is ApiResponse.Error -> {
            CircularProgressIndicator()
        }

        else -> {
            Text(text = "Unknown error", textAlign = TextAlign.Center)
        }
    }



    {
        
    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun ArticleScreenPreview(){

    ArticlesScreen(navigator = null)

}