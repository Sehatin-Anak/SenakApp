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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
import com.example.senakapp.data.RecommendationCard
import com.example.senakapp.ui.components.carditem.RecommendationCardItem
import com.example.senakapp.ui.screen.destinations.ArticlesScreenDestination
import com.example.senakapp.ui.screen.destinations.AuthScreenDestination
import com.example.senakapp.ui.theme.signikaFont
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

    val TAG = "HomeScreenAuth"

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(ContextCompat.getString(LocalContext.current, R.string.server_client_id))
        .requestEmail()
        .build()
    val account: GoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(LocalContext.current)!!
    val mGoogleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)

    Log.d(TAG, "HomeContent: ${account.idToken}")
    if (viewModel.getToken() != account.idToken){
        mGoogleSignInClient.signOut()
        navigator?.navigate(AuthScreenDestination(),
            builder =  {
                popUpTo(AuthScreenDestination.baseRoute)
            }

        )



    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end=12.dp),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Text(
            text = "Haloo, Selamat datang ${account.displayName}",
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
                       modifier = Modifier.width(150.dp).padding(start = 12.dp, top = 4.dp),
                       verticalArrangement = Arrangement.Center
                   ) {
                       Text(
                           text = "A R T I C L E S",
                           style = MaterialTheme.typography.bodyMedium,
                           textAlign = TextAlign.Start,
                           color = Color(android.graphics.Color.parseColor("#FF806E")),
                           modifier = Modifier.padding(top = 10.dp, start = 8.dp),
                           fontSize = 12.sp,
                           fontWeight = FontWeight.SemiBold,
                           fontFamily = signikaFont
                       )
                       Text(
                           text = "Processed Foods is it healthy ?",
                           style = MaterialTheme.typography.titleLarge,
                           textAlign = TextAlign.Start,
                           modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                           fontWeight = FontWeight.Bold,
                           fontSize = 17.sp,
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
                           Text(text = "Read More ➤", fontFamily = signikaFont, fontSize = 12.sp)
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



        Text(text = "Recommendation",
            fontSize =22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            fontFamily = signikaFont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(RecommendationCard.recommendationCard) { recommendation ->
                RecommendationCardItem(recommendation)
            }
        }




    }








}



@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)

@Composable
fun HomeScreenPreview() {
//

}
