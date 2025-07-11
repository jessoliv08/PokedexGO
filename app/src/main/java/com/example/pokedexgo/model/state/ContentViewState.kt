package com.example.pokedexgo.model.state

import com.example.pokedexgo.model.ability.AbilityStruct
import com.example.pokedexgo.model.move.MoveStruct
import com.example.pokedexgo.model.Stat
import com.example.pokedexgo.model.ability.Ability
import com.example.pokedexgo.model.chain.PokemonEvolution

sealed interface ContentViewState {
    data class PokemonContentMain(
        val chain: List<PokemonEvolution>?,
        val genderRate: String,
        val captureRate: Int,
        val habitat: String?,
        val eggGroups: List<String>?,
        val hatchCounter: Int,
        val baseExperience: Int
    ): ContentViewState

    data class PokemonContentStats(
        val stats: List<Stat>,
//        val damageFrom: List<PokemonType>,
//        val damageTo: List<PokemonType>
    ): ContentViewState

    data class PokemonContentAbilities(
        val abilities: List<AbilityStruct>?,
        val abilitiesCompleted: List<Ability>?
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