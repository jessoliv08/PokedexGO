package com.example.pokedexgo.ui

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDestination(
    val pokemonId: Int = -1
): PokemonDestination {
    override fun toString(): String = "pokemon_detail/$pokemonId"
}