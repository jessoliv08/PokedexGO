package com.example.pokedexgo.usecase

import com.example.pokedexgo.R
import com.example.pokedexgo.model.generic.ResultPokemon
import com.example.pokedexgo.repository.PokemonRepository
import android.util.Log
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    fun getAllPokemonWithFilter(): ResultPokemon {
        try {
            pokemonRepository.getAllPokemonWithFilter()?.let { allPokemon ->
                if (allPokemon.isNotEmpty()) {
                    return ResultPokemon.Success(
                        allPokemon.toList()
                    )
                }
            }
            return ResultPokemon.Empty(R.string.empty_message)
        } catch (ex: Exception) {
            Log.e(TAG, "Error parsing pokemon list", ex)
            return ResultPokemon.Error(R.string.error_message)
        }
    }

    companion object {
        private val TAG = PokemonUseCase::class.java.name
    }
}