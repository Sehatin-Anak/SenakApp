package com.example.senakapp.data

import com.example.senakapp.R

object RecommendationCard {

    val recommendationCard = listOf<Recommendation>(

        Recommendation(
            id = 1,
            "Fruits",
           R.drawable.strawberry
        ),
        Recommendation(
            id = 2,
            "Vegetables",
            R.drawable.vegetables
        ),
        Recommendation(
            id = 3,
            "Snack",
            R.drawable.snack
        ),
    )
}