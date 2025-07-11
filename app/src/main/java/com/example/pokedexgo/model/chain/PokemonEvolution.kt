package com.example.pokedexgo.model.chain

import com.example.pokedexgo.model.pokemon.PokemonSummary

class PokemonEvolution(
    val fromPokemon: PokemonSummary,
    val toPokemon: PokemonSummary,
    val descriptionDetails: EvolutionDetails?
) {
    val typeOneId = arrayOfNulls<Int>(2)
    val typeTwoId = arrayOfNulls<Int>(2)
    var trigger: String? = null
}