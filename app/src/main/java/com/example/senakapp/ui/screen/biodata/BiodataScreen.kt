package com.example.senakapp.ui.screen.biodata


import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
import com.example.senakapp.model.biodata.BiodataRequest
import com.example.senakapp.model.biodata.BiodataRequestResponse
import com.example.senakapp.ui.screen.destinations.HomeScreenDestination
import com.example.senakapp.ui.theme.signikaFont
import com.example.senakapp.utils.ApiResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(route = "biodata")
@Composable
fun BiodataScreen(navigator: DestinationsNavigator) {
    BiodataContent(navigator = navigator)

}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataContent(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {


    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()



    val account = GoogleSignIn.getLastSignedInAccount(LocalContext.current)

    val viewModel = hiltViewModel<BiodataViewModel>()


    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var ageCategory by remember { mutableStateOf(TextFieldValue("")) }
    var childWeight by remember { mutableStateOf(TextFieldValue("")) }
    var childHeight by remember { mutableStateOf(TextFieldValue("")) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    val verifyChildResult by viewModel.verifyChildResult.collectAsState()
LaunchedEffect(Unit){
    viewModel.verifyChild(account?.id.toString())
}

    when (verifyChildResult) {
        is ApiResponse.Loading -> {
            // Show loading indicator if needed
        }
        is ApiResponse.Success -> {
            // Verification successful, navigate to home screen
            navigator.navigate(HomeScreenDestination(), builder = {
                popUpTo(HomeScreenDestination.baseRoute)
            })
            navigator?.popBackStack()
        }
        is ApiResponse.Error -> {
            // Handle verification error if needed
            val errorMessage = (verifyChildResult as ApiResponse.Error).message
            Log.e("BiodataContent", "Verification Error: $errorMessage")
        }
        else -> {
            // Handle other cases if needed
        }
    }







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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )


            OutlinedTextField(
                value = childWeight,
                onValueChange = { childWeight = it },
                isError = childWeight.text.length >= 4,
                placeholder = {
                    Text("Berat", fontFamily = signikaFont)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            OutlinedTextField(
                value = childHeight,
                onValueChange = { childHeight = it },
                isError = childHeight.text.length >= 4,
                placeholder = {
                    Text("Tinggi", fontFamily = signikaFont)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

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














isButtonEnabled = name.text.isNotEmpty() && childWeight.text.isNotEmpty() && childHeight.text.isNotEmpty() && age.text.isNotEmpty() && ageCategory.text.isNotEmpty()

            Button(





                onClick = {

                    account?.id?.let {
                        viewModel.postBiodata(
                            it, BiodataRequest(
                                name = name.text,
                                weight = childWeight.text.toInt(), // Ganti dengan nilai sesuai kebutuhan
                                tall = childHeight.text.toInt(), // Ganti dengan nilai sesuai kebutuhan
                                ageCategory = ageCategory.text.toInt(),
                                age = age.text.toInt()
                            )

                        )
                        Log.d("BiodataContent", "$it")
                    }




                    Log.d("BiodataContent", "Button Clicked")
                    Log.d("BiodataContent", "Name: ${name.text}")
                    Log.d("BiodataContent", "Weight: ${childWeight.text}")
                    Log.d("BiodataContent", "Height: ${childHeight.text}")
                    Log.d("BiodataContent", "Age: ${age.text}")
                    Log.d("BiodataContent", "Age Category: ${ageCategory.text}")



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


        val postBiodataResult by viewModel.postBiodataResult.collectAsState()
        when (postBiodataResult) {
            is ApiResponse.Loading -> {
                // Tampilkan loading indicator jika diperlukan
            }
            is ApiResponse.Success -> {
                val response = (postBiodataResult as ApiResponse.Success<BiodataRequestResponse>).data
                // Lakukan sesuatu dengan respons yang diterima
                Log.d("BiodataContent", "Response: $response")
                Log.d("BiodataContent", "Response: ${response.message}")
                navigator.navigate(HomeScreenDestination(),
                    builder = {
                        popUpTo(HomeScreenDestination.baseRoute)
                    }


                )
                navigator?.popBackStack()
            }
            is ApiResponse.Error -> {
                val errorMessage = (postBiodataResult as ApiResponse.Error).message
                // Tangani kesalahan jika diperlukan
                Log.e("BiodataContent", "Error: $errorMessage")
            }
            // Tambahan kode untuk menangani keadaan lain jika diperlukan
            else -> {
              Log.d ("BiodataContent", "Empty")
            }
        }









    }



}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
fun BiodataScreenPreview() {

}
