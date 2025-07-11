package com.example.pokedexgo.usecase

import com.example.pokedexgo.model.ability.Ability
import com.example.pokedexgo.repository.AbilityRepository
import javax.inject.Inject

class AbilityUseCase @Inject constructor(
    private val abilityRepository: AbilityRepository
) {
    fun getAbilityById(id: Int): Ability? {
        return abilityRepository.getAbilityById(id)
    }
}