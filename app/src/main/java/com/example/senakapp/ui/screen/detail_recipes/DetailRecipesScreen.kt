package com.example.senakapp.ui.screen.detail_recipes

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.senakapp.model.detailrecipes.DetailRecipesResponse
import com.example.senakapp.ui.theme.signikaFont
import com.example.senakapp.utils.ApiResponse
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun DetailRecipesScreen(foodId: Int, navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<DetailRecipesViewModel>()

    LaunchedEffect(Unit) {
        viewModel.getDetailRecipes(foodId)
    }

    val detailRecipesResult by viewModel.detailRecipesResult.collectAsState()


    when (detailRecipesResult) {
        is ApiResponse.Loading -> {
            // Tampilkan loading indicator jika diperlukan
        }
        is ApiResponse.Success -> {
            // Mendapatkan data hasil pengambilan
            val response = (detailRecipesResult as ApiResponse.Success<DetailRecipesResponse>).data

            // Memanggil UI dengan data yang diperoleh
            DetailRecipesContent(response, navigator)

        }
        is ApiResponse.Error -> {
            // Tangani kesalahan jika diperlukan
            val errorMessage = (detailRecipesResult as ApiResponse.Error).message
            // Tampilkan pesan kesalahan ke pengguna atau lakukan tindakan yang sesuai
        }
        // tambahkan blok lain jika diperlukan
        else -> {
            Log.d("DetailRecipesScreen", "DetailRecipesScreen: ${detailRecipesResult} ")
        }
    }








}


@Composable
fun DetailRecipesContent(detailRecipes: DetailRecipesResponse, navigator: DestinationsNavigator){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AsyncImage(
            model = detailRecipes.data.img,
            contentDescription = detailRecipes.data.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(bottom = 12.dp),
            contentScale = ContentScale.Crop,

            )



        Text(
            text = "${detailRecipes.data.name}",
            fontFamily = signikaFont,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)

        )


        val ageCategoryText = when (detailRecipes.data.ageCategory) {
            1 -> "Cocok untuk umur 1 - 3 Tahun"
            2 -> "Cocok untuk umur 4 - 6 Tahun"
            3 -> "Cocok untuk umur 7 - 9 Tahun"
            else -> "Cocok untuk umur yang beragam"
        }



        Text(
            text = ageCategoryText,
            fontFamily = signikaFont,
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)

        )



        Text(
            text = "Bahan-bahan",
            fontFamily = signikaFont,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)

        )

        for (ingredient in detailRecipes.data.ingredients) {
            Text(
                text = " ●   ${ingredient.ingredient}",
                fontFamily = signikaFont,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            )
        }

        Text(
            text = "Cara Memasak",
            fontFamily = signikaFont,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)

        )

        for (instruction in detailRecipes.data.instructions) {
            Text(
                text = " ${instruction.stepOrder}. ${instruction.instruction}",
                fontFamily = signikaFont,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 16.dp)
            )
        }

        val nutritionInfo = detailRecipes.data.nutritionInfo

        val nutritionText = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 24.sp)) {
                append("Informasi Nutrisi\n\n")
            }

            append("Kalori: ${nutritionInfo.calories}\n")
            append("Lemak: ${nutritionInfo.fat}g\n")
            append("Lemak Jenuh: ${nutritionInfo.saturatedFat}g\n")
            append("Kolesterol: ${nutritionInfo.cholesterol}mg\n")
            append("Sodium: ${nutritionInfo.sodium}mg\n")
            append("Karbohidrat: ${nutritionInfo.carbohydrates}g\n")
            append("Serat: ${nutritionInfo.fiber}g\n")
            append("Gula: ${nutritionInfo.sugar}g\n")
            append("Protein: ${nutritionInfo.protein}g\n")
        }

        Text(
            text = nutritionText,
            fontFamily = signikaFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)
        )

    }








        }






data class Ingredient(
    val id: Int,
    val ingredient: String,
    val foodRecomId: Int,
    val amount: String // Menambahkan properti untuk menyimpan jumlah bahan
)


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun DetailRecipesPreview() {

    val ingredients = listOf(
        Ingredient(1162, "2 1/2 garlic cloves", 168, "2 1/2"),
        Ingredient(1163, "3 butter", 168, "3"),
        Ingredient(1164, "2 milk", 168, "2"),
        Ingredient(1165, "1/2 salt", 168, "1/2"),
        Ingredient(1166, "1 parmesan cheese", 168, "1"),
        Ingredient(1167, "1/2 black pepper", 168, "1/2"),
        Ingredient(1168, "1/2 potatoes", 168, "1/2"),
        Ingredient(1169, "1/2 potatoes", 168, "1/2"),
        Ingredient(1170, "1/2 potatoes", 168, "1/2"),


    )


}