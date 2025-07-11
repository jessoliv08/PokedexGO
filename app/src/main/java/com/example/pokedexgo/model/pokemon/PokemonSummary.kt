package com.example.pokedexgo.model.pokemon

import com.example.pokedex.util.Constants
import com.example.pokedex.util.Constants.IMAGE_LINK
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.util.toFilledId
import java.util.Locale

open class PokemonSummary(
    val id: Int,
    var name: String? = null,
    val names: Array<Name>? = null,
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

    val nameLocal: String
        get() {
           return name
               ?: if (names != null) {
                   val currentLocale = Locale.getDefault().language
                   val namesFiltered = names.filter { nameInList ->
                       nameInList.language == currentLocale
                   }
                   val nameSelected = if (namesFiltered.isNotEmpty()) {
                       namesFiltered[0]
                   } else {
                       names.filter { nameInList ->
                           nameInList.language == Constants.DEFAULT_LANGUAGE
                       }[0]
                   }
                   nameSelected.name
               } else ""
        }
}