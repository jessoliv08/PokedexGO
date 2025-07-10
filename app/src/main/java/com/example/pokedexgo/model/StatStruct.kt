package com.example.pokedexgo.model

data class StatStruct(
    val id: Int,
    val base_stat: Int
) {
    var nameStatus: String? = null

    override fun toString(): String {
        return "id: $id, baseStat: $base_stat, nameStatus: $nameStatus"
    }
}