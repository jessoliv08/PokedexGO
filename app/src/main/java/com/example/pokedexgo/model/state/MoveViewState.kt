package com.example.pokedexgo.model.state

import com.example.pokedexgo.model.move.Move
import com.example.pokedexgo.model.pokemon.PokemonType

data class MoveViewState(
    val shouldShowModal: Boolean = false,
    val type: PokemonType? = null,
    val move: Move? = null
)
