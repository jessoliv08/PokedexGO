package com.example.pokedexgo.model.move

data class MoveStruct(
    val id: Int,
    val name: String,
    val move_learn_method: String,
    val level_learned_at: Int
)