package com.example.senakapp.ui.screen.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.senakapp.ui.theme.signikaFont
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ArticlesScreen(navigator: DestinationsNavigator?){
    ArticleContent(navigator = navigator)

}

@Composable
fun ArticleContent(navigator: DestinationsNavigator? ){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Articles",
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontFamily = signikaFont
        )

    }

}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun ArticleScreenPreview(){

    ArticlesScreen(navigator = null)

}