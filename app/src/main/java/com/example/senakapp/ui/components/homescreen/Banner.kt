package com.example.senakapp.ui.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.R
import com.example.senakapp.ui.theme.signikaFont


@Composable
fun Banner(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier.clip(MaterialTheme.shapes.large)
        .size(width = 250.dp, height = 100.dp),



) {



    Card(
colors = CardDefaults.cardColors(),
        modifier = Modifier.size(width = 300.dp, height = 150.dp),


    ) {
        Box(
            modifier = Modifier.fillMaxSize()

        ) {
            Row(
                modifier = Modifier

                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.width(200.dp).padding(start = 4.dp, top = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        color = Color(android.graphics.Color.parseColor("#FF806E")),
                        modifier = Modifier.padding(top = 10.dp, start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = signikaFont,
                        maxLines = 2
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        fontFamily = signikaFont
                    )

                    Button(
                        onClick = {



                        },
                        modifier = Modifier
                            .padding(bottom = 8.dp, start = 4.dp, end = 4.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),

                        ) {
                        Text(text = "Read More ➤", fontFamily = signikaFont)
                    }
                }
                
                Image(painter = painterResource(id = R.drawable.bannerimage), contentDescription = "Banner Image", modifier = Modifier
                    .fillMaxHeight()
                )





            }
        }
    }


}







@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun BannerPreview() {
    Banner(
        title = "ARTICLE",
        subtitle = "Processed Foods is it healthy ?",

        modifier = Modifier.padding(8.dp)
    )
}

