package com.example.pokedexgo.model.ability

import com.example.pokedexgo.model.generic.NameIdUrl

data class AbilityStruct(
    val slot: Int,
    val ability: NameIdUrl,
    val is_hidden: Boolean,
)
