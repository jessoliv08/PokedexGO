package com.example.pokedexgo.usecase

import com.example.pokedexgo.repository.MoveRepository
import javax.inject.Inject

class MoveUseCase @Inject constructor(
    private val moveRepository: MoveRepository
) {
    fun getMoveById(id: Int) = moveRepository.getMoveById(id)
}