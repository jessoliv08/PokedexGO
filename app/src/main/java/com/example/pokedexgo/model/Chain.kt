package com.example.pokedex.model

import com.example.pokedexgo.model.EvolutionDetails
import com.example.pokedexgo.model.generic.LinkType

data class Chain(
    val evolves_to: Array<Chain>,
    val evolution_details: EvolutionDetails?,
    val species: LinkType,
    val is_baby: Boolean
) {
    val pokemonId: Int
    get() {
        val specieLinkArray = species.url.split("/")
        return specieLinkArray[specieLinkArray.size - 2].toInt()
    }
}
