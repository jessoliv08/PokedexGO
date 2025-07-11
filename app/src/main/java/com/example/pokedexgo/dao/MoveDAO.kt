package com.example.pokedexgo.dao

import android.content.Context
import com.example.pokedex.DAO.getSingleJsonList
import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.move.Move
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoveDAO @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getMoveById(id: Int): Move? {
        val pokemonType = object : TypeToken<Move>() {}.type
        val path = "${Constants.MOVE_PATH}$id${Constants.JSON}"
        return getSingleJsonList(context, path, pokemonType)
    }
}