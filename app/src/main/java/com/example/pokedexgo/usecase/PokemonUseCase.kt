package com.example.pokedexgo.usecase

import com.example.pokedexgo.R
import com.example.pokedexgo.model.generic.ResultPokemon
import com.example.pokedexgo.repository.PokemonRepository
import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import com.example.pokedexgo.model.FilterDataViewState
import com.example.pokedexgo.model.PokemonDetails
import com.example.pokedexgo.model.PokemonSummary
import com.example.pokedexgo.model.PokemonType
import com.example.pokedexgo.model.generic.ResultPokemonDetail
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

    fun getAllTypesList(): List<PokemonType>? {
        try {
            return pokemonRepository.getAllTypesList()?.toList()
        } catch (ex: Exception) {
            Log.e(TAG, "Error parsing types from json", ex)
            return emptyList()
        }
    }

    fun getPokemonById(id: Int): ResultPokemonDetail {
        try {
            pokemonRepository.getPokemonById(id)?.let {
                return ResultPokemonDetail.Success(it)
            } ?: {
                Log.w(TAG, "Pokemon is NULL!!")
            }

        } catch (ex: Exception) {
            Log.e(TAG, "Error parsing types from json", ex)
        }
        return ResultPokemonDetail.Error(R.string.pokemon_details_error)
    }

    fun createFilterDataDefault(
        localPokemonList: List<PokemonSummary>
    ) : FilterDataViewState? {
        getAllTypesList()?.let { allTypes ->
            val allTypesStr = allTypes.map {
                it.name
            }
            val allTypesMap = allTypes.associate {
                it.id to it.name
            }
            val allGeneration = localPokemonList.mapNotNull {
                it.generation?.toUpperCase(Locale.current)?.split("-")?.last()
            }.distinct()
            val allHabitat = localPokemonList.map {
                it.habitat ?: "NONE"
            }.distinct()
            val height = localPokemonList.mapNotNull {
                it.height
            }
            val weight = localPokemonList.mapNotNull {
                it.weight
            }
            return FilterDataViewState(
                shouldShowModal = false,
                slot1Selected = allTypesStr,
                slot2Selected = allTypesStr,
                allTypes = allTypesStr,
                allTypesWithId = allTypesMap,
                allGeneration = allGeneration,
                selectedGeneration = allGeneration,
                allHabitat = allHabitat,
                selectedHabitat = allHabitat,
                maxHeight = height.max(),
                minHeight = height.min(),
                minWeight = weight.min(),
                maxWeight = weight.max()
            )
        }
        return null
    }

    fun filterList(
        filterData: FilterDataViewState,
        localPokemonList: List<PokemonSummary>
    ): List<PokemonSummary> {
        val filterByGeneration = filterByGeneration(
            localPokemonList,
            filterData.selectedGeneration
        )
        val filterByHabitat = filterByHabitat(
            localPokemonList = filterByGeneration,
            selectedHabitat = filterData.selectedHabitat
        )
        val filterHeight = filterByHeight(
            localPokemonList = filterByHabitat,
            minHeight = filterData.minHeight,
            maxHeight = filterData.maxHeight
        )
        val filterWeight = filterByWeight(
            localPokemonList = filterHeight,
            minWeight = filterData.minWeight,
            maxWeight = filterData.maxWeight
        )
        return filterByType(
            localPokemonList = filterWeight,
            slot1Selected = filterData.slot1Selected,
            slot2Selected = filterData.slot2Selected,
            allTypesWithId = filterData.allTypesWithId
        )
    }

    private fun filterByType(
        localPokemonList: List<PokemonSummary>,
        slot1Selected: List<String>,
        slot2Selected: List<String>,
        allTypesWithId: Map<Int, String>
    ): List<PokemonSummary> {
        val selectIdsSlot1 = slot1Selected.map { typeString ->
            allTypesWithId.filter {
                it.value == typeString
            }.map { it.key }.first()
        }
        val selectIdsSlot2 = slot2Selected.map { typeString ->
            allTypesWithId.filter {
                it.value == typeString
            }.map { it.key }.first()
        }
        val slot1Filter = localPokemonList
            .filter { pokemon ->
                selectIdsSlot1.contains(
                    pokemon.types?.find { type ->
                        type.slot == 1
                    }?.id
                )
            }
        return  slot1Filter.filter { pokemon ->
            pokemon.types?.find { type ->
                type.slot == 2
            }?.id?.let { toFilter ->
                selectIdsSlot2.contains(
                    toFilter
                )
            } ?: true
        }
    }

    private fun filterByWeight(
        localPokemonList: List<PokemonSummary>,
        minWeight: Int,
        maxWeight: Int
    ): List<PokemonSummary> {
        return localPokemonList.filter {
            it.weight?.let { weight ->
                weight in minWeight..<maxWeight+1
            } ?: true
        }
    }

    private fun filterByHeight(
        localPokemonList: List<PokemonSummary>,
        minHeight: Int,
        maxHeight: Int
    ): List<PokemonSummary> {
        return localPokemonList.filter {
            it.height?.let { height ->
                height in (minHeight)..<maxHeight+1
            } ?: true
        }
    }

    private fun filterByHabitat(
        localPokemonList: List<PokemonSummary>,
        selectedHabitat: List<String>
    ): List<PokemonSummary> {
        return localPokemonList.filter { pokemon ->
            selectedHabitat.contains(
                pokemon.habitat ?: "NONE"
            )
        }
    }

    private fun filterByGeneration(
        localPokemonList: List<PokemonSummary>,
        selectedGenerationSent: List<String>
    ): List<PokemonSummary> {
        val selectedGeneration = selectedGenerationSent.map {
            "GENERATION-$it".toLowerCase(Locale.current)
        }
        return localPokemonList.filter { pokemon ->
            selectedGeneration.contains(
                pokemon.generation
            )
        }
    }

    companion object {
        private val TAG = PokemonUseCase::class.java.name
    }
}