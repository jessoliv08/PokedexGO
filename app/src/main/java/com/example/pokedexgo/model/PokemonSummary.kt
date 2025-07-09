package com.example.pokedexgo.model

import com.example.pokedex.util.Constants.IMAGE_LINK
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.util.toFilledId

open class PokemonSummary(
    id: Int,
    names: Array<Name>,
    val types: Array<TypeStructure>?
): Trigger(id, names) {
    val image :String
        get() = "$IMAGE_LINK${id.toFilledId()}.png"
}