package com.example.senakapp.ui.components

import android.icu.text.CaseMap.Title
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

import androidx.compose.ui.Modifier

@Composable
fun TitleBar(
    title: String,
    modifier: Modifier = Modifier

) {
    Text(
        text = title,
        fontSize = 22.sp,
    )

}



@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true,)

@Composable
fun TitleBarPreview() {
    TitleBar(
        title = "Title",

    )
}