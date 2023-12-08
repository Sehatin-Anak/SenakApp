package com.example.senakapp.ui.screen.biodata


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.R

@Composable
fun BiodataScreen() {
    BiodataContent()

}

@Composable
fun BiodataContent(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var old by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }

    Column (
         modifier = Modifier
            .fillMaxWidth()

        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,



        ) {

        Text(
            text = "Biodata Anak",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = Color(android.graphics.Color.parseColor("#61A3BA")),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )

        Image(painter = painterResource(id = R.drawable.anak ), contentDescription ="" )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                placeholder = {
                    Text("Nama")
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            TextField(
                value = old,
                onValueChange = { newText ->
                    old = newText
                },
                placeholder = {
                    Text("Umur")
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            TextField(
                value = gender,
                onValueChange = { newText ->
                    gender = newText
                },
                placeholder = {
                    Text("Jenis Kelamin")
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Button(
                onClick = { /* Aksi yang akan dilakukan saat tombol ditekan */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save")
            }
        }










    }



}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun BiodataScreenPreview() {
    BiodataScreen()
}
