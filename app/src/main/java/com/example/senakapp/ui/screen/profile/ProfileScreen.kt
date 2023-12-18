package com.example.senakapp.ui.screen.profile



import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.senakapp.R
import com.example.senakapp.ui.components.carditem.ProfileItem
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "profile")
@Composable
fun ProfileScreen() {
    ProfileContent()

}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Profile",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = Color.Green,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 16.dp)
        )







        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(bottom = 32.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/300",
                contentDescription = "ProfileImage",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Text(text = "Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 16.dp)
        )


        Spacer(modifier = Modifier.height(16.dp)) // Jarak antara gambar profil dan Card




        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProfileItem(
                image = R.drawable.logout,
                title = "Logout",
                contentDesc = "Logout",
onClick = {
                    Log.d("ProfileScreen", "Logout")
                }

            )
        }

        }









    }


@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}