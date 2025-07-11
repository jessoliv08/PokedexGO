package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedexgo.R
import com.example.pokedexgo.model.pokemon.PokemonSummary

@Composable
fun PokemonSummaryMainInfo(
    pokemon: PokemonSummary,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = pokemon.image,
            modifier = Modifier.size(150.dp),
            contentDescription = "Pokemon Image",
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.ditto),
            contentScale = ContentScale.FillBounds
        )
        Text(
            pokemon.nameLocal,
            style = MaterialTheme.typography.bodyLarge
        )
        pokemon.types?.let {
            TypeIcons(
                types = it.toList()
            )
        }

    }

}
