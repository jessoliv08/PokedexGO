package com.example.pokedexgo.model

import com.example.pokedex.util.Constants.IMAGE_LINK
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.util.toFilledId

open class PokemonSummary(
    val id: Int,
    val name: String,
    val types: Array<TypeStructure>?,
    val habitat: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val generation: String? = null
) {
    val image :String
        get() = "$IMAGE_LINK${id.toFilledId()}.png"
    val idLabel: String
        get() {
            return "#${id.toFilledId()}"
        }
}