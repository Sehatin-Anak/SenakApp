package com.example.senakapp.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senakapp.R

@Composable
fun AuthScreen() {
    AuthContent()
}

@Composable
fun AuthContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
Image(
    painter = painterResource(id = R.drawable.senak_logo),
    contentDescription ="Senak-Logo",
    modifier = Modifier

        .padding(bottom = 64.dp, top = 64.dp)
)

        AuthCard(
            title = "Login with Facebook",
            image = R.drawable.fb_logo,


        )
        Spacer(modifier = Modifier.padding(12.dp))

        AuthCard(
            title = "Login with Goggle",
            image = R.drawable.google_logo,

        )

    }
}

@Composable
fun AuthCard(title: String, image: Int, modifier: Modifier = Modifier){
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 12.dp, end = 12.dp)

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

        )
        {
            Image(painter = painterResource(id = image), contentDescription = "Auth Image", modifier = Modifier

                .size(60.dp).padding(4.dp)

            )
            Spacer(modifier = Modifier.padding(8.dp))


            Text(text = title)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}