package com.example.senakapp.ui.screen.profile



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.senakapp.R
import com.example.senakapp.ui.screen.destinations.AuthScreenDestination
import com.example.senakapp.ui.theme.signikaFont
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileScreen(navigator: DestinationsNavigator?) {
    ProfileContent(navigator = navigator)

}

@SuppressLint("RestrictedApi")
@Composable
fun ProfileContent(navigator: DestinationsNavigator?) {
    val viewModel = hiltViewModel<ProfileViewModel>()

    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(LocalContext.current)
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(LocalContext.current, R.string.server_client_id))
        .requestEmail()
        .build()

    val mGoogleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {




        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(bottom = 32.dp)
        ) {
            AsyncImage(
                model = account?.photoUrl,
                contentDescription = "ProfileImage",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Text(text = account?.displayName.toString(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 16.dp)
        )


        Spacer(modifier = Modifier.height(16.dp)) // Jarak antara gambar profil dan Card




        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        Log.d("ProfileScreen", "Clicked")

                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier
                )

                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    fontFamily = signikaFont,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)

                )

                Text(
                    text = ">",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Jarak antara gambar profil dan Card

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        mGoogleSignInClient.signOut()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("ProfileScreen", "SignOut Complete")
                                } else {
                                    Log.d("ProfileScreen", "SignOut Failed: ${task.exception?.message}")
                                }
                            }
                        viewModel.deleteToken()


                       navigator?.navigate(AuthScreenDestination(),

                           builder =  {
                               popUpTo(AuthScreenDestination.baseRoute)
                           }

                       )

                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier
                )

                Text(
                    text = "Logout",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    fontFamily = signikaFont,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)

                )

                Text(
                    text = ">",
                    fontSize = 20.sp
                )
            }
        }


    }









    }



@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navigator = null)
}