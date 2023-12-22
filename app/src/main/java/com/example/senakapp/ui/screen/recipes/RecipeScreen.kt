package com.example.senakapp.ui.screen.recipes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.senakapp.ui.components.carditem.RecommendationCardItem
import com.example.senakapp.ui.screen.destinations.DetailRecipesScreenDestination
import com.example.senakapp.ui.theme.signikaFont
import com.example.senakapp.utils.ApiResponse
import com.ramcosta.composedestinations.annotation.Destination

import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun RecipesScreen(navigator: DestinationsNavigator?) {
    RecipeContent(modifier = Modifier, navigator = navigator)

}

@Composable
fun RecipeContent(modifier: Modifier, navigator: DestinationsNavigator?) {
    val viewModel = hiltViewModel<RecipeViewModel>()
    val foodRecommendations by viewModel.foodRecommendations.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.getFoodRecommendations(viewModel.getIdUser() ?: "", 100)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {


                    when (foodRecommendations) {
                        is ApiResponse.Loading -> {
                            // Tampilkan loading indicator for the initial list
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
                                        navigator?.navigate(DetailRecipesScreenDestination(foodItem.id))
                                    }
                                }
                            }
                        }
                        is ApiResponse.Error -> {
                            // Tampilkan pesan error for the initial list
                            CircularProgressIndicator()
                        }
                        else -> {
                            Text(text = "Else")
                        }
                    }
                }
            }






@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {
    /*change navigator to null to show screen preview*/
    RecipesScreen(navigator = null)
}