package com.example.pokedexgo.model

data class FilterDataViewState(
    val shouldShowModal: Boolean = false,
    val slot1Selected: List<String>,
    val slot2Selected: List<String>,
    val allTypes: List<String>,
    val allTypesWithId: Map<Int, String>
)
