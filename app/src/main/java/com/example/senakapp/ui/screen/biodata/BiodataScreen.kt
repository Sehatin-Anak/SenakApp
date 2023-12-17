package com.example.senakapp.ui.screen.biodata


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.senakapp.ui.theme.signikaFont

@Composable
fun BiodataScreen() {
    BiodataContent()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataContent(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var old by remember { mutableStateOf(TextFieldValue("1")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

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
            fontFamily = signikaFont,
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

            OutlinedTextField(
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                placeholder = {
                    Text("Nama", fontFamily = signikaFont)
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = old,
                        onValueChange = {

                        },
                        placeholder = {
                            Text("Umur", fontFamily = signikaFont)
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.fillMaxWidth().padding(16.dp).menuAnchor()
                    )

                    DropdownMenuItem(
                        text = { Text("1 - 3 Tahun", fontFamily = signikaFont) },
                        onClick = {
                            old = TextFieldValue("1")
                            isDropdownExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("4 - 6 Tahun", fontFamily = signikaFont) },
                        onClick = {
                            old = TextFieldValue("2")
                            isDropdownExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("7 - 9 Tahun", fontFamily = signikaFont) },
                        onClick = {
                            old = TextFieldValue("3")
                            isDropdownExpanded = false
                        }
                    )
                }
            }


            OutlinedTextField(
                value = gender,
                onValueChange = { newText ->
                    gender = newText
                },
                placeholder = {
                    Text("Jenis Kelamin", fontFamily = signikaFont)
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Button(
                onClick = {
                          Log.d("Old", old.text)
                    Log.d("Name", name.text)
                    Log.d("Gender", gender.text)


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min   )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save", fontFamily = signikaFont)
            }
        }










    }



}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
fun BiodataScreenPreview() {
    BiodataScreen()
}
