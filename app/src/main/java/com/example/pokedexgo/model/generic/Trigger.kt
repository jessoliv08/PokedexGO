package com.example.pokedexgo.model.generic

import com.example.pokedex.util.Constants
import java.util.*

open class Trigger(
    val id: Int,
    val names: Array<Name>? = null
) {
    val name: String?
        get() {
            val currentLocale = Locale.getDefault().language
            val namesFiltered = names?.filter { nameInList ->
                nameInList.language == currentLocale
            } ?: emptyList()
            val nameSelected = if (namesFiltered.isNotEmpty()) {
                namesFiltered[0]
            } else {
                names?.find { nameInList ->
                    nameInList.language == Constants.DEFAULT_LANGUAGE
                }
            }
            return nameSelected?.name
        }
}