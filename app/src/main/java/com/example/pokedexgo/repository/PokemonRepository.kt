package com.example.pokedexgo.repository

import com.example.pokedexgo.dao.PokemonDAO
import com.example.pokedexgo.model.chain.Chain
import com.example.pokedexgo.model.chain.PokemonEvolution
import com.example.pokedexgo.model.pokemon.PokemonSummary
import javax.inject.Inject

class PokemonRepository  @Inject constructor(
    private val pokemonDAO: PokemonDAO
) {
    fun getAllPokemonWithFilter() = pokemonDAO.getAllPokemonWithFilterList()
    fun getAllTypesList() = pokemonDAO.getAllTypesList()
    fun getAllTypesDetailedList() = pokemonDAO.getAllTypesDetailedList()
    fun getPokemonById(id: Int) = pokemonDAO.getPokemonById(id)

    fun getChainById(id: Int): List<PokemonEvolution>? {
        val chain = pokemonDAO.getChainById(id)
        return chain?.chain?.let { firstChain ->
            pokemonDAO.getPokemonSummaryById(firstChain.pokemonId)?.let { pokemon ->
                setupEvolutionChain(firstChain.evolves_to, pokemon, firstChain.is_baby)
            }
        }
    }

    private fun setupEvolutionChain(
        evolutionChain: Array<Chain>,
        pokemonOrigin: PokemonSummary,
        isBaby: Boolean
    ): ArrayList<PokemonEvolution> {
        val chainList = ArrayList<PokemonEvolution>()
        if (evolutionChain.isNotEmpty()) {
            for (evoltInd in evolutionChain) {
                pokemonDAO.getPokemonSummaryById(evoltInd.pokemonId)?.let { pokemonEvolv ->
                    pokemonOrigin.name = getName(isBaby, pokemonOrigin)
                    pokemonEvolv.name = getName(evoltInd.is_baby, pokemonEvolv)
                    val pokemonEvolution = PokemonEvolution(
                        fromPokemon = pokemonOrigin,
                        toPokemon = pokemonEvolv,
                        descriptionDetails = evoltInd.evolution_details
                    )
                    setupTypes(pokemonEvolution, pokemonOrigin, 0)
                    setupTypes(pokemonEvolution, pokemonEvolv, 1)

                    evoltInd.evolution_details?.let {
                        pokemonEvolution.trigger = it.trigger.name
                    }

                    chainList.add(pokemonEvolution)

                    chainList.addAll(
                        setupEvolutionChain(
                            evolutionChain = evoltInd.evolves_to,
                            pokemonOrigin = pokemonEvolv,
                            isBaby = evoltInd.is_baby
                        )
                    )
                }
            }
        }
        return chainList
    }

    private fun setupTypes(pokemonEvolution: PokemonEvolution, pokemonSummary: PokemonSummary, index: Int) {
        pokemonSummary.types?.let {
            if (it.isNotEmpty()) pokemonEvolution.typeOneId[index] = it[0].id
            if (it.size > 1) pokemonEvolution.typeTwoId[index] = it[1].id
        }
    }

    private fun getName(isBaby: Boolean, pokemon: PokemonSummary) : String {
        return if (isBaby) {
            "${pokemon.nameLocal} (baby)"
        } else {
            pokemon.nameLocal
        }
    }

}