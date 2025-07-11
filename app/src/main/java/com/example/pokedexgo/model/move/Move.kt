package com.example.pokedexgo.model.move

data class Move(
    val id: Int,
    val name: String,
    val type: String,
    val damage_class: String?,
    val effect_chance: Int?,
    val effect: String?,
    val power: Int?,
    val pp: Int,
    val accuracy: Int?,
    val priority: Int?,
)
