package com.example.senakapp.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.ui.components.carditem.RecommendationCardItem
import com.example.senakapp.ui.screen.destinations.ArticlesScreenDestination
import com.example.senakapp.ui.screen.destinations.AuthScreenDestination
import com.example.senakapp.ui.theme.signikaFont
import com.example.senakapp.utils.ApiResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {


    HomeContent(navigator = navigator)


}



@SuppressLint("FlowOperatorInvokedInComposition", "RestrictedApi")
@Composable
fun HomeContent(modifier: Modifier = Modifier, navigator: DestinationsNavigator?) {


    val viewModel = hiltViewModel<HomeViewModel>()
    val foodRecommendations by viewModel.foodRecommendations.collectAsState()
    val verifyChildResult by viewModel.verifyChildResult.collectAsState()




    val TAG = "HomeScreenAuth"

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(ContextCompat.getString(LocalContext.current, R.string.server_client_id))
        .requestEmail()
        .build()
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(LocalContext.current)
    val mGoogleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)

    viewModel.saveTokenAsync(account?.idToken.toString())


    Log.d(TAG, "HomeContent: ${viewModel.getToken()}")
    if (viewModel.getToken() == account?.idToken){
Log.d(TAG, "HomeContent: Token sama")
        LaunchedEffect(Unit) {
viewModel.getIdUser()?.let { viewModel.getFoodRecommendations(it, 10) }
            viewModel.getIdUser()?.let { viewModel.verifyChild(it) }



        }

    }else{
        mGoogleSignInClient.signOut()
        navigator?.navigate(AuthScreenDestination(),
            builder =  {
                popUpTo(AuthScreenDestination.baseRoute)
            }
        )
    }

    LaunchedEffect(verifyChildResult) {
        when (verifyChildResult) {
            is ApiResponse.Success -> {
                val verifyChildData = (verifyChildResult as ApiResponse.Success<VerifyChildResponse>).data.data

                Log.d("HomeContent", "Verify Child success: $verifyChildData")
                // Now you can use the verifyChildData in your UI logic
            }
            is ApiResponse.Error -> {
                // Handle error if needed
                Log.e("HomeContent", "Verify Child error: ${(verifyChildResult as ApiResponse.Error).message}")
            }
            else -> {
                // Handle other cases if needed
            }
        }
    }
    when(verifyChildResult){
        is ApiResponse.Success -> {
            val verifyChildData = (verifyChildResult as ApiResponse.Success<VerifyChildResponse>).data.data


            // Now you can use the verifyChildData in your UI logic
        }
        is ApiResponse.Error -> {
            // Handle error if needed
            Log.e("HomeContent", "Verify Child error: ${(verifyChildResult as ApiResponse.Error).message}")
        }
        else -> {
            // Handle other cases if needed
        }
    }
    val verifyChildData = verifyChildResult?.let {
        if (it is ApiResponse.Success) {
            it.data.data
        } else {
            null
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Text(
            text = "Haloo, Selamat datang ${account?.displayName}",
            fontFamily = signikaFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = Color(android.graphics.Color.parseColor("#91C788")),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        )


       Card(
           colors = CardDefaults.cardColors(),
              modifier = Modifier
                  .fillMaxWidth()
                  .height(200.dp)
                  .padding(16.dp)
       ) {
           Box(
               modifier = Modifier.fillMaxSize()

           ) {
               Row(
                   modifier = Modifier
                       .fillMaxSize(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Column(
                       modifier = Modifier
                           .width(150.dp)
                           .padding(start = 12.dp, top = 4.dp),
                       verticalArrangement = Arrangement.Center
                   ) {
                       Text(
                           text = "A R T I K E L",
                           style = MaterialTheme.typography.bodyMedium,
                           textAlign = TextAlign.Start,
                           color = Color(android.graphics.Color.parseColor("#FF806E")),
                           modifier = Modifier.padding(top = 10.dp, start = 8.dp, bottom = 4.dp),
                           fontSize = 12.sp,
                           fontWeight = FontWeight.SemiBold,
                           fontFamily = signikaFont
                       )
                       Text(
                           text = "Baca artikel terbaru dari kami",
                           style = MaterialTheme.typography.titleLarge,
                           textAlign = TextAlign.Start,
                           modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                           fontWeight = FontWeight.Bold,
                           fontSize = 16.sp,
                           fontFamily = signikaFont
                       )

                       Button(
                           onClick = {

                                     navigator?.navigate(ArticlesScreenDestination)

                           },
                           modifier = Modifier
                               .padding(bottom = 8.dp, start = 4.dp, end = 4.dp, top = 4.dp)
                               .fillMaxWidth(),
                           shape = RoundedCornerShape(12.dp),

                           ) {
                           Text(text = "Baca Sekarang", fontFamily = signikaFont, fontSize = 12.sp)
                       }
                   }

                   Image(painter = painterResource(id = R.drawable.bannerimage), contentDescription = "Banner Image", modifier = Modifier
                       .fillMaxHeight()
                       .padding(end = 12.dp)
                   )





               }
           }


       }





        Spacer(modifier = Modifier.size(16.dp))



        Text(text = "Rekomendasi untuk ${verifyChildData?.name} 💖",
            fontSize =22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            fontFamily = signikaFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        when (foodRecommendations) {
            is ApiResponse.Loading -> {
                // Tampilkan loading indicator
                CircularProgressIndicator()
            }
            is ApiResponse.Success -> {
                val data = (foodRecommendations as ApiResponse.Success).data
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    items(data.data) { foodItem ->
                        // Tampilkan data makanan di sini
                        RecommendationCardItem(foodItem) {
                            Log.d("HomeScreen", "HomeContent: ${foodItem.name}")
                        }
                    }
                }
            }
            is ApiResponse.Error -> {
                // Tampilkan pesan error
                Text("Failed to fetch data: ${(foodRecommendations as ApiResponse.Error).message}")
                Log.d("HomeScreen", "HomeContent: ${(foodRecommendations as ApiResponse.Error).message}")
            }
            else -> {
                Text(text = "Else")
            }
        }











    }








}



@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)

@Composable
fun HomeScreenPreview() {
//

}
