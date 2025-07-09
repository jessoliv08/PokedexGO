package com.example.pokedexgo.util

import com.example.pokedex.util.Constants

fun Int.toFilledId(): String {
    return when {
        this / 100 > 0 -> "$this"
        this / 10 > 0 -> "${Constants.ZERO}$this"
        else -> "${Constants.ZERO}${Constants.ZERO}$this"
    }
}