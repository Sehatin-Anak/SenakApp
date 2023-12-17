package com.example.senakapp.ui.components.carditem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senakapp.R
import com.example.senakapp.ui.theme.signikaFont

@Composable
fun ProfileItem(image: Int, title: String, contentDesc: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = title,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(painter = painterResource(id = image), contentDescription = contentDesc)
        Text(
            text = title,
            fontFamily = signikaFont,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .width(300.dp)
                .padding(12.dp)
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.Transparent, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "➤",
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Transparent, shape = CircleShape)
            )
        }
    }
}



@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileItemPreview() {
    ProfileItem(R.drawable.logout, "Logout", "Logout", onClick = {})
}
