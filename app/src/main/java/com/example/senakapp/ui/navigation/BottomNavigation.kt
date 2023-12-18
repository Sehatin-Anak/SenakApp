package com.example.senakapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.senakapp.R
import com.example.senakapp.ui.screen.NavGraphs
import com.example.senakapp.ui.screen.appCurrentDestinationAsState
import com.example.senakapp.ui.screen.destinations.ArticlesScreenDestination
import com.example.senakapp.ui.screen.destinations.Destination
import com.example.senakapp.ui.screen.destinations.HomeScreenDestination

import com.example.senakapp.ui.screen.destinations.ProfileScreenDestination
import com.example.senakapp.ui.screen.destinations.RecipesScreenDestination
import com.example.senakapp.ui.screen.startAppDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector? = null,
    @DrawableRes val iconResId: Int? = null,
    @StringRes val label: Int
) {
    Home(
        direction = HomeScreenDestination,
        icon = Icons.Filled.Home,
        iconResId = null,
        label = R.string.home_screen
    ),
    Recipes(
        direction = RecipesScreenDestination,
        iconResId = R.drawable.recipe_filled,
        icon = null,
        label = R.string.recipes_screen
    ),

    Articles(
        direction = ArticlesScreenDestination,
        iconResId = R.drawable.article_outlined,
        icon = null,
        label = R.string.articles_screen
    ),
    Profile(
        direction = ProfileScreenDestination,
        icon = Icons.Filled.Person,
        label = R.string.profile_screen
    )
}

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    NavigationBar {
        BottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {

                    destination.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null
                        )
                    } ?: destination.iconResId?.let {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = it),
                            contentDescription = null
                        )
                    }

                },
                label = {
                    Text(text = stringResource(id = destination.label))
                }
            )
        }
    }
}



