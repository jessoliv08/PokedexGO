package com.example.pokedexgo.model.chain

import android.content.res.Resources
import com.example.pokedexgo.R
import com.example.pokedexgo.model.generic.LinkType
import com.example.pokedexgo.model.generic.Trigger

class EvolutionDetails(
    val trigger: Trigger,
    val min_level: Int? = null,
    val min_beauty: Int? = null,
    val min_happiness: Int? = null,
    val min_affection: Int? = null,
    val time_of_day: String? = null,
    val held_item: LinkType? = null,
    val item: LinkType? = null,
    val known_move: LinkType? = null,
    val known_move_type: LinkType? = null,
    val trade_species: LinkType? = null,
    val needs_overworld_rain: Boolean,
    val turn_upside_down: Boolean,
    val location: LinkType? = null
) {
    fun getDescription(resources: Resources) : String {
        var descriptionStr = ""
        min_level?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.min_level,
                it.toString()))
            descriptionStr = descriptionStr.plus("\n")
        }
        min_beauty?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.min_beauty,
                it.toString()))
            descriptionStr = descriptionStr.plus("\n")
        }
        min_happiness?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.min_happiness,
                it.toString()))
            descriptionStr = descriptionStr.plus("\n")
        }
        min_affection?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.min_affection,
                it.toString()))
            descriptionStr = descriptionStr.plus("\n")
        }
        time_of_day?.let {
            if (time_of_day.isNotEmpty()) {
                descriptionStr = descriptionStr.plus(
                    resources.getString(
                        R.string.time_of_day,
                        it
                    )
                )
                descriptionStr = descriptionStr.plus("\n")
            }
        }
        item?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.item,
                it.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        held_item?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.held_item,
                it.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        known_move_type?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.known_move_type,
                it.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        known_move?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.known_move,
                it.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        trade_species?.let {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.trade_species,
                it.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        location?.let { loc ->
            descriptionStr = descriptionStr.plus(resources.getString(
                    R.string.location,
                    loc.name))
            descriptionStr = descriptionStr.plus("\n")
        }
        if (needs_overworld_rain) {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.needs_overworld_rain))
            descriptionStr = descriptionStr.plus("\n")
        }
        if (turn_upside_down) {
            descriptionStr = descriptionStr.plus(resources.getString(
                R.string.turn_upside_down))
            descriptionStr = descriptionStr.plus("\n")
        }
        return descriptionStr
    }
}