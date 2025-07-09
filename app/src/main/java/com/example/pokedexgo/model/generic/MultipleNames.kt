package com.example.pokedexgo.model.generic

import com.example.pokedex.util.Constants
import java.util.*

open class MultipleNames(
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
}