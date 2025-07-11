package com.example.pokedexgo.model

import com.example.pokedexgo.model.generic.Id
import com.example.pokedexgo.model.generic.MultipleNames
import com.example.pokedexgo.model.generic.Name

class TypeDetailed(
    val id: Int,
    names: Array<Name>,
    val no_damage_from: Array<Id>,
    val half_damage_from: Array<Id>,
    val double_damage_from: Array<Id>,
    val no_damage_to: Array<Id>,
    val half_damage_to: Array<Id>,
    val double_damage_to: Array<Id>,
): MultipleNames(names = names)