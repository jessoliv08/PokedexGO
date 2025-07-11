package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.model.state.ResultPokemon
import com.example.pokedexgo.model.state.ResultPokemonDetail
import com.example.pokedexgo.ui.list.PokemonLoading
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailsScreen(
    pokemonId: Int,
    viewModel: PokemonDetailViewModel,
    onBackButton: () -> Unit = {},
    onNavigateToNextPokemon: (Int) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        viewModel.setupPokemon(pokemonId)
    }
    val pokemon by viewModel.viewState.collectAsState()
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        when (pokemon) {
            is ResultPokemonDetail.Success -> {
                PokemonDetailElement(
                    pokemon = (pokemon as ResultPokemonDetail.Success).pokemon,
                    viewModel = viewModel,
                    onBackButton = onBackButton,
                    onNavigateToNextPokemon = onNavigateToNextPokemon)
            }
            is ResultPokemonDetail.Loading -> {
                PokemonLoading(Modifier.align(Alignment.Center))
            }
            else -> {
                Text(
                    text = stringResource((pokemon as ResultPokemon.Error).message),
                    modifier = Modifier.align(Alignment.Center).padding(horizontal = 48.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}