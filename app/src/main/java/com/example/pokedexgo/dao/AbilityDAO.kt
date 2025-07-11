package com.example.pokedexgo.dao

import android.content.Context
import com.example.pokedex.DAO.getSingleJsonList
import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.ability.AbilityData
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AbilityDAO @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getAbilityById(id: Int): AbilityData? {
        val pokemonType = object : TypeToken<AbilityData>() {}.type
        val path = "${Constants.ABILITIES_PATH}$id${Constants.JSON}"
        return getSingleJsonList(context, path, pokemonType)
    }
}