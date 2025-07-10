package com.example.pokedexgo.model.state

import com.example.pokedexgo.model.AbilityStruct
import com.example.pokedexgo.model.MoveStruct
import com.example.pokedexgo.model.StatStruct

sealed interface ContentViewState {
    data class PokemonContentMain(
//        val chain: Chain,
        val genderRate: Int,
        val captureRate: Int,
        val habitat: String,
        val eggGroups: List<String>?,
        val hatchCounter: Int,
        val baseExperience: Int
    ): ContentViewState

    data class PokemonContentStats(
        val stats: List<StatStruct>?,
//        val damageFrom: List<PokemonType>,
//        val damageTo: List<PokemonType>
    ): ContentViewState

    data class PokemonContentAbilities(
        val abilities: List<AbilityStruct>?
    ): ContentViewState

    data class PokemonContentMoves(
        val moves: List<MoveStruct>?
    ): ContentViewState

    data class PokemonContentInfo(
        val description: String,
        val baseHappiness: Int,
        val generation: String,
        val height: Int,
        val weight: Int,
    ): ContentViewState

    object Loading: ContentViewState
}