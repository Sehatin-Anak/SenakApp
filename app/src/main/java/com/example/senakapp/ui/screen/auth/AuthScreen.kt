package com.example.senakapp.ui.screen.auth


import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.R
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


    val lastToken = remember { viewModel.getToken() }

    LaunchedEffect(Unit) {
        if (lastToken != null) {

            viewModel.performGoogleLogin(lastToken)
        }
        viewModel.verifyChild(viewModel.getIdUser() ?: "Error get id")

        Log.d("AuthContent", "AuthContent: ${viewModel.getIdUser()}")



    }



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.senak_logo),
            contentDescription = "Senak-Logo",
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

        Log.d("AuthContent", "verifyChild: ${verifyChildResult} ")
        Log.d("AuthContent", "verifyLogin ${loginResult}")


        when {
            loginResult is ApiResponse.Success && verifyChildResult is ApiResponse.Success -> {
                // Jika login sukses dan verify child sukses, navigasi ke HomeScreen
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)


                Log.d("AuthContent", "Harusnya nav ke home: ${verifyChildResult} ")
            }

            loginResult is ApiResponse.Error && verifyChildResult is ApiResponse.Success -> {
                // Jika login gagal dan verify child sukses, navigasi ke AuthScreen
                navigator.popBackStack()
                navigator.navigate(AuthScreenDestination)

                Log.d("AuthContent", "Harusnya nav ke auth: ${verifyChildResult} ")
            }

            loginResult is ApiResponse.Success && verifyChildResult is ApiResponse.Error -> {
                // Jika login sukses dan verify child gagal, navigasi ke BiodataScreen
                navigator.popBackStack()
                navigator.navigate(BiodataScreenDestination)


                Log.d("AuthContent", "Harusnya nav ke bio: ${verifyChildResult} ")
            }

            loginResult is ApiResponse.Error && verifyChildResult is ApiResponse.Error -> {
                // Jika login gagal dan verify child gagal, navigasi ke AuthScreen
                navigator.popBackStack()
                navigator?.navigate(AuthScreenDestination)

            }
            // Tambahkan logika lain sesuai kebutuhan
            else -> {
                // Handle kasus lain jika diperlukan
            }
        }
    }


    }










    @Composable
    fun AuthCard(
        title: String,
        image: Int,
        modifier: Modifier = Modifier,
        navigator: DestinationsNavigator
    ) {
        val viewModel = hiltViewModel<AuthViewModel>()

        val verifyChildResult by viewModel.verifyChildResult.collectAsState(initial = ApiResponse.Empty)
        LaunchedEffect(Unit) {
            viewModel.verifyChild(viewModel.getIdUser() ?: "Error get id")
        }

        when (verifyChildResult) {
            is ApiResponse.Loading -> {
                // Tampilkan loading indicator jika diperlukan
            }

            is ApiResponse.Success -> {
                // Mendapatkan data hasil pengambilan
                val response = (verifyChildResult as ApiResponse.Success<VerifyChildResponse>).data

                // Memanggil UI dengan data yang diperoleh
                Log.d("AuthCard", "Verify Success: ${response}")
            }

            is ApiResponse.Error -> {
                // Tangani kesalahan jika diperlukan
                val errorMessage = (verifyChildResult as ApiResponse.Error).message
                // Tampilkan pesan kesalahan ke pengguna atau lakukan tindakan yang sesuai
            }
            // tambahkan blok lain jika diperlukan
            else -> {
                Log.d("AuthCard", "AuthCard: ${verifyChildResult} ")
            }
        }

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
                        navigator.navigate(HomeScreenDestination(),
                            navigator.popBackStack()

                        )

                        if (verifyChildResult is ApiResponse.Success<VerifyChildResponse>) {
                            navigator.navigate(HomeScreenDestination(),
                                builder = {
                                    navigator.popBackStack()
                                }

                            )
                            navigator.popBackStack()
                        }


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
        navigator.popBackStack()
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