package com.example.pokedexgo.repository

import com.example.pokedexgo.dao.MoveDAO
import javax.inject.Inject

class MoveRepository @Inject constructor(
    private val moveDAO: MoveDAO
) {
    fun getMoveById(id: Int) = moveDAO.getMoveById(id)
}