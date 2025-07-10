package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetails(
    pokemonId: Int,
    viewModel: PokemonDetailViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.setupPokemon(pokemonId)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = viewModel.pokemonId.id.toString())
    }
}