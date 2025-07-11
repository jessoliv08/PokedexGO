package com.example.pokedexgo.model.pokemon

import com.example.pokedexgo.model.generic.MultipleNames
import com.example.pokedexgo.model.generic.Name

open class PokemonType(
    val id: Int,
    names: Array<Name>
): MultipleNames(names = names)