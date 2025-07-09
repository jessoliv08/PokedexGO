package com.example.pokedexgo.viewmodel

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonSummary
import com.example.pokedexgo.model.generic.ResultPokemon
import com.example.pokedexgo.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {
    private  lateinit var localPokemonList: List<PokemonSummary>
    private  lateinit var searchedSubList: List<PokemonSummary>
    private val _viewState = MutableStateFlow<ResultPokemon>(ResultPokemon.Loading)
    val viewState = _viewState.asStateFlow()
    private val _querySearch = MutableStateFlow("")
    val querySearch = _querySearch.asStateFlow()

    fun getAllPokemon() {
        val result = pokemonUseCase.getAllPokemon()
        if (result is ResultPokemon.Success) {
            localPokemonList = result.data
        }
        _viewState.update {
            result
        }
    }

    fun search(query: String) {
        _querySearch.value = query.toLowerCase(Locale.current)
        if (query.isEmpty() && this::localPokemonList.isInitialized) {
            _viewState.update {
                ResultPokemon.Success(localPokemonList)
            }
        } else {
            searchedSubList = localPokemonList.filter {
                it.name.toLowerCase(Locale.current).contains(query) ||
                        it.id.toString().contains(query) ||
                        it.idLabel.contains(query) ||
                        it.idLabel.substring(1, it.idLabel.length).contains(query)
            }
            if (searchedSubList.isEmpty()) {
                _viewState.update {
                    ResultPokemon.Empty(R.string.empty_message)
                }
            } else {
                _viewState.update {
                    ResultPokemon.Success(searchedSubList)
                }
            }
        }
    }
}