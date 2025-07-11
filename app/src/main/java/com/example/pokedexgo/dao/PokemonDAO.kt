package com.example.pokedexgo.dao

import android.content.Context
import com.example.pokedex.DAO.getAllJsonList
import com.example.pokedex.DAO.getSingleJsonList
import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.chain.EvolutionChain
import com.example.pokedexgo.model.pokemon.PokemonDetails
import com.example.pokedexgo.model.pokemon.PokemonSummary
import com.example.pokedexgo.model.pokemon.PokemonType
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PokemonDAO @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getAllPokemonWithFilterList(): Array<PokemonSummary>? {
        val pokemonSummaryArrayType = object : TypeToken<Array<PokemonSummary>>() {}.type
        return getAllJsonList(context, Constants.POKEMON_LIST_WITH_FILTERS, pokemonSummaryArrayType)
    }

    fun getAllTypesList(): Array<PokemonType>? {
        val typeArrayType = object : TypeToken<Array<PokemonType>>() {}.type
        return getAllJsonList(context, Constants.TYPE_LIST, typeArrayType)
    }

    fun getPokemonById(id: Int): PokemonDetails? {
        val pokemonType = object : TypeToken<PokemonDetails>() {}.type
        val path = "${Constants.POKEMON_PATH}$id${Constants.JSON}"
        return getSingleJsonList(context, path, pokemonType)
    }

    fun getPokemonSummaryById(id: Int): PokemonSummary? {
        val pokemonType = object : TypeToken<PokemonSummary>() {}.type
        val path = "${Constants.POKEMON_PATH}$id${Constants.JSON}"
        return getSingleJsonList(context, path, pokemonType)
    }

    fun getChainById(id: Int): EvolutionChain? {
        val chainType = object : TypeToken<EvolutionChain>() {}.type
        val path = "${Constants.CHAIN_PATH}$id${Constants.JSON}"
        return getSingleJsonList(context, path, chainType)
    }
}