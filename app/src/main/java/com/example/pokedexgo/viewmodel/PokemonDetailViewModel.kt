package com.example.pokedexgo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonType
import com.example.pokedexgo.model.state.ContentViewState
import com.example.pokedexgo.model.state.ResultPokemonDetail
import com.example.pokedexgo.model.state.TabButtonState
import com.example.pokedexgo.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {
    private var cacheTypes: List<PokemonType> = emptyList()
    private val _viewState = MutableStateFlow<ResultPokemonDetail>(ResultPokemonDetail.Loading)
    val viewState = _viewState.asStateFlow()

    private val _contentViewState = MutableStateFlow<ContentViewState>(ContentViewState.Loading)
    val contentViewState = _contentViewState.asStateFlow()

    private val _bottomButtonViewState = MutableStateFlow(
        listOf(
            TabButtonState(
                id = 0,
                icon = R.drawable.pokeball,
                label = R.string.pokemon_details_section_main,
                isSelected = true
            ),
            TabButtonState(
                id = 1,
                icon = R.drawable.stats,
                label = R.string.pokemon_details_status,
                isSelected = false
            ),
            TabButtonState(
                id = 2,
                icon = R.drawable.ability,
                label = R.string.pokemon_details_abilities,
                isSelected = false
            ),
            TabButtonState(
                id = 3,
                icon = R.drawable.moves,
                label = R.string.pokemon_details_moves,
                isSelected = false
            ),
            TabButtonState(
                id = 4,
                icon = R.drawable.info,
                label = R.string.pokemon_details_info,
                isSelected = false
            )
        )
    )
    val bottomButtonViewState = _bottomButtonViewState.asStateFlow()

    fun setupPokemon(id: Int) {
        _viewState.update {
            pokemonUseCase.getPokemonById(id)
        }
        onContentSelect(0)
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

    fun onContentSelect(contentId: Int) {
        if (_viewState.value !is ResultPokemonDetail.Success) return

        val newList = _bottomButtonViewState.value.map {
            it.copy(isSelected = it.id == contentId )
        }
        _bottomButtonViewState.update {
            newList
        }
        val pokemon = (_viewState.value as ResultPokemonDetail.Success).pokemon
        val content = when (contentId) {
            0 -> {
                ContentViewState.PokemonContentMain(
//                    chain = ,
                    genderRate = if (pokemon.gender_rate != -1) {
                        val femaleRate = ((pokemon.gender_rate / 8f) * 100).toInt()
                        "${femaleRate}% F / ${abs(femaleRate - 100)}% M"
                    } else {
                        "genderless"
                    },
                    captureRate = pokemon.capture_rate,
                    habitat = pokemon.habitat,
                    eggGroups = pokemon.egg_groups?.toList(),
                    hatchCounter = pokemon.hatch_counter,
                    baseExperience = pokemon.base_experience
                )
            }
            1 -> {
                ContentViewState.PokemonContentStats(
                    stats = pokemon.stats?.toList(),
//                    damageFrom = ,
//                    damageTo =
                )
            }
            2 -> {
                ContentViewState.PokemonContentAbilities(
                    abilities = pokemon.abilities?.toList()
                )
            }
            3 -> {
                ContentViewState.PokemonContentMoves(
                    moves = pokemon.moves?.toList()
                )
            }
            4 -> {
                ContentViewState.PokemonContentInfo(
                    description = pokemon.flavor_text,
                    baseHappiness = pokemon.base_happiness,
                    generation = pokemon.generation,
                    height = pokemon.height,
                    weight = pokemon.weight
                )
            }
            else -> ContentViewState.Loading
        }
        _contentViewState.update {
            content
        }
    }
}