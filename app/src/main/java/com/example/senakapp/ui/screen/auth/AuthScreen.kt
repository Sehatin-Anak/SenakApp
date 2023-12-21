package com.example.senakapp.ui.screen.auth


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.service.autofill.UserData
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
import com.example.senakapp.model.auth.LoginResponse
import com.example.senakapp.model.biodata.VerifyChildResponse
import com.example.senakapp.ui.screen.destinations.AuthScreenDestination
import com.example.senakapp.ui.screen.destinations.BiodataScreenDestination
import com.example.senakapp.ui.screen.destinations.HomeScreenDestination
import com.example.senakapp.utils.ApiResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.runBlocking

@RootNavGraph(start = true)
@Destination
@Composable
fun AuthScreen(navigator: DestinationsNavigator) {

        AuthContent(navigator = navigator)

}


@SuppressLint("SuspiciousIndentation", "RestrictedApi")
@Composable
fun AuthContent(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<AuthViewModel>()
    var isLoginAndVerificationDone by remember { mutableStateOf(false) }

    val lastToken = viewModel.getToken()
    if (lastToken != null && !isLoginAndVerificationDone) {
        LaunchedEffect(Unit) {
            viewModel.performGoogleLogin(lastToken)
            viewModel.verifyChild(viewModel.getIdUser().toString())
            isLoginAndVerificationDone = true
        }
    }




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
        val verifyChildResult by viewModel.verifyChildResult.collectAsState()
        val loginResult by viewModel.loginResult.collectAsState()


            if (loginResult is ApiResponse.Success && verifyChildResult is ApiResponse.Success) {
                // Navigasi ke HomeScreen jika berhasil masuk dan verifikasi anak berhasil
                navigator.navigate(HomeScreenDestination(),
                    builder = {
                        popUpTo(HomeScreenDestination.baseRoute) {
                            inclusive = true
                        }
                    }

                )

                Log.d("AuthContent", "Login and verify success: ${(loginResult as ApiResponse.Success<LoginResponse>).data}")
                Log.d("AuthContent", "Login and verify success: ${(verifyChildResult as ApiResponse.Success<VerifyChildResponse>).data}")


                Log.d("AuthContent", "Login and verify success: ${(loginResult as ApiResponse.Success<LoginResponse>).data}")
            } else if (verifyChildResult is ApiResponse.Error && loginResult is ApiResponse.Success) {
                // Navigasi ke BiodataScreen jika verifikasi anak gagal
                navigator.navigate(BiodataScreenDestination(),
                    builder = {
                        popUpTo(BiodataScreenDestination.baseRoute) {
                            inclusive = true
                        }
                    }

                )
                Log.d("AuthContent", "Login success and verify child error: ${(verifyChildResult as ApiResponse.Error).message}")
                Log.d("AuthContent", "Login success and verify child error: ${(loginResult as ApiResponse.Success<LoginResponse>).data}")



                Log.e("AuthContent", "Verify child error: ${(verifyChildResult as ApiResponse.Error).message}")
            } else if ( loginResult is ApiResponse.Error ) {
                // Logika tambahan jika diperlukan ketika kondisi tidak terpenuhi

                navigator.navigate(AuthScreenDestination()) {
                    popUpTo(AuthScreenDestination.baseRoute) {
                        inclusive = true
                    }
                }
                Log.d("AuthContent", "Login loading: ${loginResult is ApiResponse.Loading}")
            }

        }

    }









@Composable
fun AuthCard(title: String, image: Int, modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<AuthViewModel>()
    val loginResult by viewModel.loginResult.collectAsState(initial = ApiResponse.Loading)

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







    val signInLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
                val data: Intent? = result.data
                if (data != null && result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    handleSignInResult(task, navigator)
                    viewModel.saveTokenAsync(task.result?.idToken.toString())
                    viewModel.saveIdUserAsync(task.result?.id.toString())



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
            Image(
                painter = painterResource(id = image),
                contentDescription = "Auth Image",
                modifier = Modifier
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
        Log.d("YourActivity", "signInResult:success Tescode=" + account)
        account.displayName?.let {
            Log.d("YourActivity", "signInResult:success name=" + it)
            Log.d("YourActivity", "signInResult:success email=" + account.email)
            Log.d("YourActivity", "signInResult:success id=" + account.id)
            Log.d("YourActivity", "signInResult:success idToken=" + account.idToken)

            // Mendapatkan ID pengguna dari GoogleSignInAccount
            val userId = account.id
//            Log.d("YourActivity", "User ID: $userId")

            // Lanjutkan dengan menyimpan ID pengguna atau melakukan tindakan lain yang diinginkan.

        }



        navigator.navigate(HomeScreenDestination)
    } catch (e: ApiException) {
        // Tangani kegagalan masuk
        Log.w("YourActivity", "signInResult:failed code=" + e.statusCode)
        // Handle the failed sign-in
    }
}















@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun AuthScreenPreview() {



}