package com.example.senakapp.ui.components.carditem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.senakapp.data.Recommendation
import com.example.senakapp.model.DataItem
import com.example.senakapp.ui.theme.signikaFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationCardItem(  data : DataItem, onClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            // Gunakan Image() alih-alih AsyncImage(), karena imageBitmap dari AsyncImage tidak cocok dengan Image()

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {


                AsyncImage(
                    model = data.img,
                    contentDescription = data.name,
                    modifier = Modifier
                        .width(150.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.Crop,

                )
                Spacer(modifier = Modifier.width(8.dp))




                Column(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = data.category,
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "${data.reviewCount} Orang merekomendasikan",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )


                    Text(
                        text = "⭐(${data.agregateRating})",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.Bold,
                    )





            }





            }




            }
        }
    }






@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun RecommendationCardPreview() {



}