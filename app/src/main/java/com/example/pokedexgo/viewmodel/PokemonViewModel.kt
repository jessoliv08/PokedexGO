package com.example.pokedexgo.viewmodel

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexgo.R
import com.example.pokedexgo.model.state.FilterDataViewState
import com.example.pokedexgo.model.pokemon.PokemonSummary
import com.example.pokedexgo.model.state.ResultPokemon
import com.example.pokedexgo.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {
    private  lateinit var localPokemonList: List<PokemonSummary>
    private  lateinit var filteredSubList: List<PokemonSummary>
    private  lateinit var searchedSubList: List<PokemonSummary>
    private val _viewState = MutableStateFlow<ResultPokemon>(ResultPokemon.Loading)
    val viewState = _viewState.asStateFlow()
    private val _querySearch = MutableStateFlow("")
    val querySearch = _querySearch.asStateFlow()
    private val _filterDataViewState = MutableStateFlow<FilterDataViewState?>(null)
    val filterDataViewState = _filterDataViewState.asStateFlow()

    fun setupInitialInfo() {
        viewModelScope.launch {
            // setup Pokemon
            val result = pokemonUseCase.getAllPokemonWithFilter()
            if (result is ResultPokemon.Success) {
                localPokemonList = result.dataWithFilter
            }
            _viewState.update {
                result
            }
            filteredSubList = localPokemonList
            // setup Filters
            pokemonUseCase.createFilterDataDefault(
                localPokemonList = localPokemonList
            )?.let { filter->
                _filterDataViewState.update {
                    filter
                }
            }
        }
    }

    fun shouldShowFilterModal(shouldShow: Boolean) {
        _filterDataViewState.update {
            it?.copy(
                shouldShowModal = shouldShow
            )
        }
    }

    fun onApplyFilter(filterData: FilterDataViewState) {
        _filterDataViewState.update {
            filterData
        }
        _querySearch.value = ""
        filteredSubList = pokemonUseCase.filterList(
            localPokemonList = localPokemonList,
            filterData = filterData
        )
        _viewState.update {
            ResultPokemon.Success(filteredSubList)
        }
    }

    fun search(query: String) {
        _querySearch.value = query.toLowerCase(Locale.current)
        if (query.isEmpty() && this::filteredSubList.isInitialized) {
            _viewState.update {
                ResultPokemon.Success(filteredSubList)
            }
        } else {
            searchedSubList = filteredSubList.filter {
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