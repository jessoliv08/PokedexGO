package com.example.pokedexgo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokedexgo.model.PokemonType
import com.example.pokedexgo.model.generic.ResultPokemonDetail
import com.example.pokedexgo.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {
    private var cacheTypes: List<PokemonType> = emptyList()
    private val _viewState = MutableStateFlow<ResultPokemonDetail>(ResultPokemonDetail.Loading)
    val viewState = _viewState.asStateFlow()

    fun setupPokemon(id: Int) {
        _viewState.update {
            pokemonUseCase.getPokemonById(id)
        }
    }

    fun getSlot1TypeName(): String? {
        (_viewState.value as ResultPokemonDetail.Success).pokemon.types?.find {
            it.slot == 1
        }?.id?.let { typeId ->
            if (cacheTypes.isEmpty()) {
                cacheTypes = pokemonUseCase.getAllTypesList() ?: emptyList()

            }
            return cacheTypes.filter { it.id == typeId }.firstOrNull()?.name

        }
        return null
    }

    fun getPokemonType(typeId: Int): PokemonType? {
        if (cacheTypes.isEmpty()) {
            cacheTypes = pokemonUseCase.getAllTypesList() ?: emptyList()

        }
        return cacheTypes.firstOrNull { it.id == typeId }
    }
}