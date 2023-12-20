package com.example.senakapp.ui.screen.biodata


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
import com.ramcosta.composedestinations.annotation.Destination


@Destination(route = "biodata")
@Composable
fun BiodataScreen() {
    BiodataContent()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataContent(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var ageCategory by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }
    var isDropdownExpandedAge by remember { mutableStateOf(false) }
    var isDropdownExpandedGender by remember { mutableStateOf(false) }

    val ageRanges = listOf(1, 2, 3,4,5,6,7,8,9)

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
                expanded = isDropdownExpandedAge,

                onExpandedChange = { newValue ->
                    isDropdownExpandedAge = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                OutlinedTextField(

                    value = age,
                    onValueChange = {},
                    isError = age.text.length < 3,
                    readOnly = true,
                    trailingIcon = {
                        TrailingIcon(expanded = isDropdownExpandedAge)
                    },
                    placeholder = {
                        Text(text = "Kategori Umur")
                    },

                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )

                ExposedDropdownMenu(
                    expanded = isDropdownExpandedAge,
                    onDismissRequest = { isDropdownExpandedAge = false },
                    modifier = Modifier.width(16.dp).offset(
                       -16.dp,-16.dp
                    )



                ) {


                    DropdownMenu(
                        expanded = isDropdownExpandedAge,
                        onDismissRequest = { isDropdownExpandedAge = false }
                    ) {
                        ageRanges.map { ageRange ->
                            DropdownMenuItem(
                                text = { Text(text = ageRange.toString()) },
                                onClick = {
                                    age = TextFieldValue(ageRange.toString())
                                    isDropdownExpandedAge = false
                                }
                            )
                        }
                    }

                    if (age.text > "0" && age.text <= "3") {
                        ageCategory = TextFieldValue("1")
                    } else if (age.text >= "4" && age.text <= "6") {
                        ageCategory = TextFieldValue("2")
                    } else if (age.text >= "7" && age.text <= "9") {
                        ageCategory = TextFieldValue("3")
                    }
val TAG = "AGE"

                    Log.d (TAG, "BiodataContentAgeCategory: ${ageCategory.text}")
                    Log.d (TAG, "BiodataContentAge: ${age.text}")

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
                    isError = gender.text.length < 3,
                    readOnly = true,
                    trailingIcon = {
                        TrailingIcon(expanded = isDropdownExpandedGender)
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

                    when(age.text){
                        "1 - 3 Tahun" -> age = TextFieldValue("1 - 3 Tahun")
                        "4 - 6 Tahun" -> age = TextFieldValue("4 - 6 Tahun")
                        "7 - 9 Tahun" -> age = TextFieldValue("7 - 9 Tahun")

                    }
                          Log.d("Old", "${age.text.toInt()}")





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
