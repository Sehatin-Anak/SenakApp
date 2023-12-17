package com.example.senakapp.ui.components.carditem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.R
import com.example.senakapp.data.Recommendation

import com.example.senakapp.ui.theme.signikaFont


@Composable
fun RecommendationCardItem(recommendation: Recommendation, modifier: Modifier = Modifier) {

    Card(

        modifier = Modifier
            .size(width = 150.dp, height = 150.dp)
            .clip(MaterialTheme.shapes.large)


    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Image(painter = painterResource(id = recommendation.image), contentDescription = "Recommendation Image", modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)

            )
            Text(recommendation.title,
                fontFamily = signikaFont,

                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                )




        }

    }



}



@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun RecommendationCardPreview() {

    RecommendationCardItem(
        recommendation = Recommendation(
            1,
            title = "Title",
            image = R.drawable.strawberry
        )
    )
}