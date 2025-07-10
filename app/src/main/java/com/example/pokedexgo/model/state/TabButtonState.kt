package com.example.pokedexgo.model.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TabButtonState(
    val id: Int,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val isSelected: Boolean,
)