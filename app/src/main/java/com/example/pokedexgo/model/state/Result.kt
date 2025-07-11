package com.example.pokedexgo.model.state

import androidx.annotation.StringRes
import com.example.pokedexgo.model.pokemon.PokemonDetails
import com.example.pokedexgo.model.pokemon.PokemonSummary

sealed interface ResultPokemon {
    data class Success(val dataWithFilter: List<PokemonSummary>) : ResultPokemon
    data class Error(@StringRes val message: Int) : ResultPokemon
    data class Empty(@StringRes val message: Int) : ResultPokemon
    object Loading : ResultPokemon
}

sealed interface ResultPokemonDetail {
    data class Success(val pokemon: PokemonDetails) : ResultPokemonDetail
    data class Error(@StringRes val message: Int) : ResultPokemonDetail
    object Loading : ResultPokemonDetail
}