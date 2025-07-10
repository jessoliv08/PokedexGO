package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonDetails
import com.example.pokedexgo.ui.generic.MatchColor
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailElement(
    pokemon: PokemonDetails,
    viewModel: PokemonDetailViewModel,
    onBackButton: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                viewModel = viewModel,
                pokemon = pokemon,
                resourceName = viewModel.getSlot1TypeName()?.toLowerCase(Locale.current),
                onBackButton = onBackButton
            )
        },
        bottomBar = {
            val bottomInset = WindowInsets.systemBars.asPaddingValues()
                .calculateBottomPadding()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp + bottomInset)
                    .background(
                        MatchColor(
                            viewModel.getSlot1TypeName()?.toLowerCase(Locale.current) ?: ""
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {}
                ) {
//                    Column {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBackIosNew,
//                            contentDescription = "Back"
//                        )
//                        Text(
//
//                        )
//                    }
                }
            }
        },
        content = { padding ->

        }
    )
}

@Composable
fun TopBar(
    viewModel: PokemonDetailViewModel,
    pokemon: PokemonDetails,
    resourceName: String?,
    onBackButton: () -> Unit
) {
    val topInset = WindowInsets.systemBars.asPaddingValues()
        .calculateTopPadding()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp + topInset)
                .background(
                    MatchColor(
                        resourceName ?: ""
                    )
                )
        ) {
            IconButton(
                onClick = onBackButton,
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = stringResource(R.string.pokemon_details_title),
                color = Color.Black,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
            )
        }
        PokemonMainInfo(
            pokemon = pokemon,
            getPokemonType = {
                viewModel.getPokemonType(it)
            }
        )
    }

}