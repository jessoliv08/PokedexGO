package com.example.pokedexgo.model

import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.util.toFilledId
import java.util.*

open class Trigger(
    val id: Int,
    val names: Array<Name>
) {
    val name: String
        get() {
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
            return nameSelected.name
        }

    val idLabel: String
        get() {
            return "#${id.toFilledId()}"
        }
}