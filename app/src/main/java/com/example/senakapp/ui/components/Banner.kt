package com.example.senakapp.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

import com.example.senakapp.R
import java.nio.channels.AsynchronousChannel


@Composable
fun Banner(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier.clip(MaterialTheme.shapes.large)
) {



    Card(
colors = CardDefaults.cardColors(


),
        modifier = Modifier.size(width = 250.dp, height = 150.dp),



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
                    modifier = Modifier.width(150.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        color = Color(android.graphics.Color.parseColor("#FF806E")),
                        modifier = Modifier.padding(top = 10.dp, start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(bottom = 8.dp, start = 4.dp, end = 4.dp)
                            .fillMaxWidth()





                        ) {
                        Text(text = "Read More")
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
    Banner(title = "ARTICLE",
        subtitle = "Processed Foods is it healthy ?",

        modifier = Modifier.padding(8.dp))
}

