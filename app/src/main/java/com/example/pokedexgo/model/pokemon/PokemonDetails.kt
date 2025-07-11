package com.example.pokedexgo.model.pokemon

import com.example.pokedexgo.model.ability.AbilityStruct
import com.example.pokedexgo.model.MoveStruct
import com.example.pokedexgo.model.StatStruct
import com.example.pokedexgo.model.generic.MultipleNames
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.util.toFilledId

class PokemonDetails(
    val id: Int,
    val evolution_chain: Int,
    val gender_rate: Int,
    val capture_rate: Int,
    val base_happiness: Int,
    val hatch_counter: Int,
    val height: Int,
    val weight: Int,
    val flavor_text: String,
    val generation: String,
    val habitat: String?,
    val base_experience: Int,
    names: Array<Name>,
    val types: Array<TypeStructure>?,
    val stats: Array<StatStruct>?,
    val egg_groups: Array<String>?,
    val abilities: Array<AbilityStruct>?,
    val moves: Array<MoveStruct>?,
    val image: String
): MultipleNames(names) {
    val idLabel: String
        get() {
            return "#${id.toFilledId()}"
        }
}
