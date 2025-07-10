package com.example.pokedexgo.ui.generic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.pokedexgo.ui.theme.PokemonSectionColor

@Composable
fun MatchColor(resourceName: String): Color {
    val context = LocalContext.current
    val resId = remember(resourceName) {
        context.resources.getIdentifier(resourceName, "color", context.packageName)
    }
    return if (resId != 0) {
        colorResource(resId)
    } else {
        PokemonSectionColor
    }
}