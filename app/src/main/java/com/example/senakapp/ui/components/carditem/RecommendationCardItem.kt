package com.example.senakapp.ui.components.carditem

import android.widget.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.senakapp.data.Recommendation
import com.example.senakapp.ui.theme.signikaFont


@Composable
fun RecommendationCardItem(
    recommendation: Recommendation
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Gunakan Image() alih-alih AsyncImage(), karena imageBitmap dari AsyncImage tidak cocok dengan Image()

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {


                AsyncImage(
                    model = recommendation.image,
                    contentDescription = recommendation.title,
                    modifier = Modifier
                        .width(150.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.Crop,

                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = recommendation.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = signikaFont,
                    fontWeight = FontWeight.Bold,
                )



                Column(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
            }



            }




            }
        }
    }






@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun RecommendationCardPreview() {

    RecommendationCardItem(
       recommendation = Recommendation(
           id = 1,
           title = "Recommendation",
           image = "https://images.unsplash.com/photo-1621574539437-8b9b7b0b8b9f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmVhdXR5JTIwY2FyZHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80",

       )
    )
}