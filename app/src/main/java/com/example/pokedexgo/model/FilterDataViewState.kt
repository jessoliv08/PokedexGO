package com.example.pokedexgo.model

data class FilterDataViewState(
    val shouldShowModal: Boolean = false,
    val slot1Selected: List<String>,
    val slot2Selected: List<String>,
    val allTypes: List<String>,
    val allTypesWithId: Map<Int, String>,
    val allGeneration: List<String>,
    val selectedGeneration: List<String>,
    val allHabitat: List<String>,
    val selectedHabitat: List<String>,
    val minHeight: Int,
    val maxHeight: Int,
    val minWeight: Int,
    val maxWeight: Int
)
