package com.example.pokedexgo.model.generic

import androidx.annotation.StringRes
import com.example.pokedexgo.model.PokemonSummary

sealed interface ResultPokemon {
    data class Success(val dataWithFilter: List<PokemonSummary>) : ResultPokemon
    data class Error(@StringRes val message: Int) : ResultPokemon
    data class Empty(@StringRes val message: Int) : ResultPokemon
    object Loading : ResultPokemon
}