package com.example.pokedexgo.model.ability

import com.example.pokedex.util.Constants
import com.example.pokedexgo.model.generic.Name
import java.util.Locale

data class AbilityData(
    val id: Int,
    val names: Array<NameUnprocessed>,
    val effect_entries: Array<EffectEntry>,
    val flavor_text_entries: Array<FlavorEntry>,
    val generation: Language,
    val is_main_series: Boolean,
    // will not use:
    val name: String,
    val effect_changes: Array<EffectEntries>,
    val pokemon: Array<PokemonRelated>
) {
    val effectEntrySimple : EffectEntrySimple
        get() {
            val currentLocale = Locale.getDefault().language
            val namesFiltered = effect_entries.filter { nameInList ->
                nameInList.language.name == currentLocale
            }
            val nameSelected = if (namesFiltered.isNotEmpty()) {
                namesFiltered[0]
            } else {
                effect_entries.filter { nameInList ->
                    nameInList.language.name == Constants.DEFAULT_LANGUAGE
                }[0]
            }
            return EffectEntrySimple(
                effect = nameSelected.effect,
                shortEffect = nameSelected.short_effect
            )
        }

    val flavorEntrySimple : FlavorEntrySimple
        get() {
            val currentLocale = Locale.getDefault().language
            val namesFiltered = flavor_text_entries.filter { nameInList ->
                nameInList.language.name == currentLocale
            }
            val nameSelected = if (namesFiltered.isNotEmpty()) {
                namesFiltered[0]
            } else {
                flavor_text_entries.filter { nameInList ->
                    nameInList.language.name == Constants.DEFAULT_LANGUAGE
                }[0]
            }
            return FlavorEntrySimple(
                flavorText = nameSelected.flavor_text,
                versionGroup = nameSelected.version_group.name
            )
        }

    val nameSingle: String
        get() {
            val currentLocale = Locale.getDefault().language
            val namesFiltered = names.filter { nameInList ->
                nameInList.language.name == currentLocale
            }
            val nameSelected = if (namesFiltered.isNotEmpty()) {
                namesFiltered[0]
            } else {
                names.filter { nameInList ->
                    nameInList.language.name == Constants.DEFAULT_LANGUAGE
                }[0]
            }
            return nameSelected.name
        }
}

data class EffectEntries(
    val effect_entries: Array<EffectEntry>,
    val version_group: Language
)

data class EffectEntry(
    val effect: String,
    val language: Language,
    val short_effect: String? = null
)

data class FlavorEntry(
    val flavor_text: String,
    val language: Language,
    val version_group: Language
)

data class PokemonRelated(
    val slot: Int,
    val is_hidden: Boolean,
    val pokemon: Language
)

data class NameUnprocessed(
    val name: String,
    val language: Language
)

data class Language(
    val name: String,
    val url: String
)