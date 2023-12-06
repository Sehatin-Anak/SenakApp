package com.example.senakapp.ui.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.R

@Composable
fun BannerTrack(
    modifier: Modifier = Modifier

) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.size(width = 350.dp, height = 100.dp),



        ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(12.dp)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()


                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

            )

            {

                Column {
                    Text(text = "Track Gizi",
                    fontSize =24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )

                    Text(text = "Seminggu Ini",
                        fontSize =24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )

                }



                Button(onClick = { /*TODO*/ },

                ) {
                    Text(text = "View More")
                }
            }
        }
    }



}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun BannerTrackPreview() {
    BannerTrack()
}
