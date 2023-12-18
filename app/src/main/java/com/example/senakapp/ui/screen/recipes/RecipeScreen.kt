package com.example.senakapp.ui.screen.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.senakapp.ui.theme.signikaFont
import com.ramcosta.composedestinations.annotation.Destination

import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun RecipesScreen(navigator: DestinationsNavigator?) {
    RecipeContent(modifier = Modifier, navigator = navigator)

}

@Composable
fun RecipeContent(modifier: Modifier, navigator: DestinationsNavigator?) {

    Column(
        modifier.fillMaxWidth()
    )

    {
Text(text ="Recipes", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontFamily = signikaFont,
    fontSize = 36.sp
    )
    }


}


@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {
    /*change navigator to null to show screen preview*/
    RecipesScreen(navigator = null)
}