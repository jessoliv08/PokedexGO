package com.example.pokedexgo.model

import com.example.pokedexgo.model.pokemon.PokemonType

data class Damages(
    val values: List<TypeDamage>
)

data class TypeDamage(
    val type: PokemonType,
    val damage: Double
)
