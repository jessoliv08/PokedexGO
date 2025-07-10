package com.example.pokedexgo.model

import android.content.res.Resources
import com.example.pokedexgo.R
import com.example.pokedexgo.model.generic.LinkType
import com.example.pokedexgo.model.generic.Trigger

class EvolutionDetails(
    val trigger: Trigger,
    val min_level: Int?,
    val min_beauty: Int?,
    val min_happiness: Int?,
    val min_affection: Int?,
    val time_of_day: String?,
    val held_item: LinkType?,
    val item: LinkType?,
    val known_move: LinkType?,
    val known_move_type: LinkType?,
    val trade_species: LinkType?,
    val needs_overworld_rain: Boolean,
    val turn_upside_down: Boolean,
    val location: Array<LinkType>?
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
        location?.let {
            for (loc in it) {
                descriptionStr = descriptionStr.plus(resources.getString(
                    R.string.location,
                    loc.name))
                descriptionStr = descriptionStr.plus("\n")
            }
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