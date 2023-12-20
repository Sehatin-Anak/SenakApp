package com.example.senakapp.ui.screen.auth

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senakapp.R
import com.example.senakapp.data.UserData
import com.example.senakapp.ui.screen.destinations.AuthScreenDestination
import com.example.senakapp.ui.screen.destinations.HomeScreenDestination
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun AuthScreen(navigator: DestinationsNavigator) {

        AuthContent(navigator = navigator)

}


@Composable
fun AuthContent(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
Image(
    painter = painterResource(id = R.drawable.senak_logo),
    contentDescription ="Senak-Logo",
    modifier = Modifier

        .padding(bottom = 64.dp, top = 64.dp)


)

        Spacer(modifier = Modifier.padding(12.dp))

        AuthCard(
            title = "Google",
            image = R.drawable.google_logo,
            navigator = navigator

            )

    }
}




@Composable
fun AuthCard(title: String, image: Int, modifier: Modifier = Modifier, navigator: DestinationsNavigator){

    val justLoggedOut = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.server_client_id))
        .requestScopes(Scope(Scopes.EMAIL), Scope(Scopes.PROFILE))
        .requestProfile()
        .requestId()
        .requestServerAuthCode(context.getString(R.string.server_client_id), true)
        .requestEmail()
        .build()

    val mGsoClient = GoogleSignIn.getClient(context, gso)

    val lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(LocalContext.current)

    if (lastSignedInAccount != null && !justLoggedOut.value) {
        // Pengguna sudah masuk sebelumnya dan bukan setelah logout
        Log.d("YourActivityLasSignin", "signInResult:success code=$lastSignedInAccount")
        navigator?.navigate(HomeScreenDestination())

    }


    // Inisialisasi launcher untuk onActivityResult
    val signInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            val data: Intent? = result.data
            if (data != null && result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task, navigator)
            }
        } else {
            Log.d("YourActivity", "signInResult:failed code=" + result.resultCode)
        }
    }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 12.dp, end = 12.dp)
            .clickable {
                val signInIntent = mGsoClient.signInIntent
                signInLauncher.launch(signInIntent)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            )
        {
            Image(painter = painterResource(id = image), contentDescription = "Auth Image", modifier = Modifier
                .size(60.dp)
                .padding(4.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Text(text = title)
        }

    }



}
private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>, navigator: DestinationsNavigator) {
    try {
        val account = completedTask.getResult(ApiException::class.java)
        Log.d("YourActivity", "signInResult:success code=" + account)
        account.displayName?.let {
            Log.d("YourActivity", "signInResult:success name=" + it)
            Log.d("YourActivity", "signInResult:success email=" + account.email)
            Log.d("YourActivity", "signInResult:success id=" + account.id)
            Log.d("YourActivity", "signInResult:success idToken=" + account.idToken)


        }


        navigator?.navigate(HomeScreenDestination(


        )

        )
        // Signed in successfully, show authenticated UI.
        //updateUI(account

    } catch (e: ApiException) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
        Log.w("YourActivity", "signInResult:failed code=" + e.statusCode)
        // Handle the failed sign-in
    }


}









@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun AuthScreenPreview() {



}