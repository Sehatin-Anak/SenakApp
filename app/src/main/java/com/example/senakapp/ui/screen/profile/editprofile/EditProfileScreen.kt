package com.example.senakapp.ui.screen.profile.editprofile


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
import com.example.senakapp.model.biodata.UpdateBiodataRequest
import com.example.senakapp.ui.theme.signikaFont
import com.example.senakapp.utils.ApiResponse
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun EditProfileScreen(navigator: DestinationsNavigator) {

    val viewModel = hiltViewModel<EditProfileViewModel>()
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var ageCategory by remember { mutableStateOf(TextFieldValue("")) }
    val updateBiodata by viewModel.updateBiodata.collectAsState()

    var isButtonEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
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

        Image(painter = painterResource(id = R.drawable.anak), contentDescription = "")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = age,
                onValueChange = {
                    age = it

                    // Check if the input is not empty before converting to int
                    if (it.text.isNotEmpty()) {
                        when (it.text) {
                            "1" -> ageCategory = TextFieldValue("1")
                            "2" -> ageCategory = TextFieldValue("1")
                            "3" -> ageCategory = TextFieldValue("1")
                            "4" -> ageCategory = TextFieldValue("2")
                            "5" -> ageCategory = TextFieldValue("2")
                            "6" -> ageCategory = TextFieldValue("2")
                            "7" -> ageCategory = TextFieldValue("3")
                            "8" -> ageCategory = TextFieldValue("3")
                            "9" -> ageCategory = TextFieldValue("3")
                            else -> ageCategory = TextFieldValue("1")
                        }
                    } else {
                        // Handle the case when the input is empty, you may set a default value or show an error message.
                        ageCategory = TextFieldValue("1")
                    }
                },
                isError = age.text.length > 1,
                placeholder = {
                    Text("Umur", fontFamily = signikaFont)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            isButtonEnabled = age.text.isNotEmpty() && if (age.text.length > 1) {
                false
            } else {
                true
            }

            Button(

                onClick = {

                    val userId = viewModel.getIdUser()
                    if (userId != null) {
                        viewModel.putBiodata(
                            userId,
                            UpdateBiodataRequest(
                                age = age.text.toInt(),
                                ageCategory = ageCategory.text.toInt(),
                            )
                        )
                        Log.d("EditProfileScreen", "onClick: $userId")
                        Log.d("EditProfileScreen", "onClick: ${age.text}")
                        Log.d("EditProfileScreen", "onClick: ${ageCategory.text}")

                        navigator.popBackStack()
                    } else {
                        // Handle the case where userId is null, if needed
                    }
                    navigator.popBackStack()


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = isButtonEnabled,
            ) {
                Text("Save", fontFamily = signikaFont)
            }
        }

    }


    when (updateBiodata) {
        is ApiResponse.Loading -> {
            Log.d("EditProfileScreen", "Loading")
        }

        is ApiResponse.Success -> {
            Log.d("EditProfileScreen", "Success")
        }

        is ApiResponse.Error -> {
            Log.d("EditProfileScreen", "${(updateBiodata as ApiResponse.Error).message}}")
        }

        is ApiResponse.Empty -> {
            Log.d("EditProfileScreen", "Empty")
        }

    }
}




@Preview
@Composable
fun PreviewEditProfileScreen() {

}


