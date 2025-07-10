package com.example.pokedexgo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.pokedexgo.ui.list.PokemonList
import com.example.pokedexgo.ui.theme.PokedexGOTheme
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel
import com.example.pokedexgo.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : PokemonViewModel by viewModels()
    private val pokemonDetailViewModel : PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PokedexGOTheme {
                PokemonNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    detailViewModel = pokemonDetailViewModel
                )
            }
        }
    }
}