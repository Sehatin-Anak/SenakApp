package com.example.senakapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.data.RecommendationCard
import com.example.senakapp.ui.components.carditem.RecommendationCardItem
import com.example.senakapp.ui.components.homescreen.Banner
import com.example.senakapp.ui.screen.destinations.ProfileScreenDestination
import com.example.senakapp.ui.theme.signikaFont
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {


    HomeContent()


}



@Composable
fun HomeContent(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Text(
            text = "Sehatin Anak Yuk!",
            fontFamily = signikaFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = Color(android.graphics.Color.parseColor("#91C788")),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        )

        Banner(
            title = "A R T I C L E",
            subtitle ="Processed Foods is it healthy ?",
            modifier = Modifier
                .size(width = 350.dp, height = 150.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))



        Text(text = "Recommendation",
            fontSize =22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            fontFamily = signikaFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(RecommendationCard.recommendationCard) { recommendation ->
                RecommendationCardItem(recommendation)
            }
        }




    }








}



@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)

@Composable
fun HomeScreenPreview() {
    HomeContent()

}
