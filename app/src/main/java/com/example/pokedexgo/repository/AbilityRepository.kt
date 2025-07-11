package com.example.pokedexgo.repository

import com.example.pokedexgo.dao.AbilityDAO
import com.example.pokedexgo.model.ability.Ability
import javax.inject.Inject

class AbilityRepository  @Inject constructor(
    private val abilityDAO: AbilityDAO
) {
    fun getAbilityById(id: Int): Ability? {
        abilityDAO.getAbilityById(id)?.let { abilityCompleted ->
            return Ability(
                id = abilityCompleted.id,
                name = abilityCompleted.nameSingle,
                isMainSeries = abilityCompleted.is_main_series,
                generation = abilityCompleted.generation.name,
                effectEntrySimple = abilityCompleted.effectEntrySimple,
                flavorEntrySimple = abilityCompleted.flavorEntrySimple
            )
        }
        return null
    }
}