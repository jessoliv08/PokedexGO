package com.example.pokedexgo.dao

import android.content.Context
import com.example.pokedex.DAO.getAllJsonList
import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.PokemonSummary
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PokemonDAO @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getAllPokemonList(): Array<PokemonSummary>? {
        val pokemonSummaryArrayType = object : TypeToken<Array<PokemonSummary>>() {}.type
        return getAllJsonList(context, Constants.POKEMON_LIST, pokemonSummaryArrayType)
    }
}