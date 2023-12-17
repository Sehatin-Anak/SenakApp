package com.example.senakapp.ui.screen.biodata


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
    var old by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }
    var isDropdownExpandedOld by remember { mutableStateOf(false) }
    var isDropdownExpandedGender by remember { mutableStateOf(false) }

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
                onValueChange = { name = it },
                isError = name.text.length < 3,
                placeholder = {
                    Text("Nama", fontFamily = signikaFont)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )



            ExposedDropdownMenuBox(
                expanded = isDropdownExpandedOld,

                onExpandedChange = { newValue ->
                    isDropdownExpandedOld = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Transparent)
            ) {
                OutlinedTextField(

                    value = old,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpandedOld)
                    },
                    placeholder = {
                        Text(text = "Umur Anak")
                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()

                )

                ExposedDropdownMenu(
                    expanded = isDropdownExpandedOld,
                    onDismissRequest = {
                        isDropdownExpandedOld = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "1 - 3 Tahun")
                        },
                        onClick = {
                            old = TextFieldValue("1 - 3 Tahun")
                            isDropdownExpandedOld = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "4 - 6 Tahun")
                        },
                        onClick = {
                            old = TextFieldValue("4 - 6 Tahun")
                            isDropdownExpandedOld = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "7 - 9 Tahun")
                        },
                        onClick = {
                            old = TextFieldValue("7 - 9 Tahun")
                            isDropdownExpandedOld = false
                        }
                    )
                }

            }


            ExposedDropdownMenuBox(
                expanded = isDropdownExpandedGender,

                onExpandedChange = { newValue ->
                    isDropdownExpandedGender = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(

                    value = gender,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpandedGender)
                    },
                    placeholder = {
                        Text(text = "Jenis Kelamin")
                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )

                ExposedDropdownMenu(
                    expanded = isDropdownExpandedGender,
                    onDismissRequest = {
                        isDropdownExpandedGender = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Male")
                        },
                        onClick = {
                            gender = TextFieldValue("Male")
                            isDropdownExpandedGender = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Female")
                        },
                        onClick = {
                            gender = TextFieldValue("Female")
                            isDropdownExpandedGender = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Other")
                        },
                        onClick = {
                            gender = TextFieldValue("Other")
                            isDropdownExpandedGender = false
                        }
                    )
                }

            }





            Button(





                onClick = {

                    when(old.text){
                        "1 - 3 Tahun" -> old = TextFieldValue("1")
                        "4 - 6 Tahun" -> old = TextFieldValue("2")
                        "7 - 9 Tahun" -> old = TextFieldValue("3")
                    }
                          Log.d("Old", old.text)





                    Log.d("Name", name.text)
                    Log.d("Gender", gender.text)


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
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
