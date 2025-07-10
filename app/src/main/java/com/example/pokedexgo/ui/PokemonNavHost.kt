package com.example.pokedexgo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedexgo.ui.list.PokemonList
import com.example.pokedexgo.ui.pokemon.PokemonDetailsScreen
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel
import com.example.pokedexgo.viewmodel.PokemonViewModel

@Composable
fun PokemonNavHost(
    navController: NavHostController,
    viewModel : PokemonViewModel,
    detailViewModel : PokemonDetailViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = PokemonListDestination.toString(),
    ) {
        composable(PokemonListDestination.toString()) {
            PokemonList(
                viewModel = viewModel,
                onPokemonSelected = { id ->
                    navController.navigate("pokemon_detail/$id")
                }
            )
        }
        composable(
            route = "pokemon_detail/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { stackEntry ->
            val pokemonId = stackEntry.arguments?.getInt("pokemonId") ?: -1
            PokemonDetailsScreen(
                pokemonId = pokemonId,
                viewModel = detailViewModel,
                onBackButton = {
                    navController.popBackStack()
                }
            )
        }
    }
}