package com.example.pokedex.util

public object Constants {
    const val DEFAULT_LANGUAGE = "en"
    const val IMAGE_LINK = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/detail/"
    const val JSON = ".json"
    const val ATTRIBUTE_TYPE_COLOR = "color"
    const val TYPE_LIST = "Types.json"
    const val STATS_LIST = "Stats.json"
    const val POKEMON_PATH = "pokemons/pokemon"
    const val ABILITIES_PATH = "abilities/ability"
    const val CHAIN_PATH = "chains/chain"
    const val POKEMON_LIST_WITH_FILTERS = "PokemonListWithFilter.json"
    const val MOVES_LIST = "MoveList.json"
    const val ITEMS_LIST = "ItemsList.json"
    const val HASH = "#"
    const val ZERO = "0"
    const val POKEMON_IMAGE_PREFIX = "ic_"
    const val TYPE_IMAGE_PREFIX = "type"
    const val ITEM_IMAGE_PREFIX = "item"
    const val TYPE_DRAWABLE = "drawable"
    const val CAPTURED_POKEMON_DATABASE = "captured_pokemon"
    const val DB = ".db"
    const val CAPTURED_POKEMON_UPDATE_NOTIFIER = "com.pokedex.action.update_captured"
    const val ID_EXTRA = "ID"
    const val QUANTITY_EXTRA = "QUANTITY"
    const val ACTION_EXTRA = "ACTION"
    const val UPDATE_EXTRA = "UPDATE"
    const val DELETE_EXTRA = "DELETE"
    enum class SortBy {
        ID,
        NAME,
        CAPTURED,
        COST
    }
}