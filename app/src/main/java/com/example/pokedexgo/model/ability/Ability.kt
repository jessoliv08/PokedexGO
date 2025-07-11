package com.example.pokedexgo.model.ability

data class Ability(
    val id: Int,
    val name: String,
    val isMainSeries: Boolean,
    val generation: String,
    val effectEntrySimple: EffectEntrySimple,
    val flavorEntrySimple: FlavorEntrySimple
)

data class EffectEntrySimple(
    val effect: String,
    val shortEffect: String? = null
)

data class FlavorEntrySimple(
    val flavorText: String,
    val versionGroup: String? = null
)
