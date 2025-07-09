package com.example.pokedexgo.repository

import com.example.pokedexgo.dao.PokemonDAO
import javax.inject.Inject

class PokemonRepository  @Inject constructor(
    private val pokemonDAO: PokemonDAO
) {
    fun getAllPokemon() = pokemonDAO.getAllPokemonList()
}