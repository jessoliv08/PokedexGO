package com.example.pokedexgo.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonSummary
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.model.generic.TypeStructure

@Composable
fun PokemonElement(pokemon: PokemonSummary) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PokemonInfo(pokemon)
            pokemon.types?.let {
                TypeIcons(types = it.toList())
            }
        }
        HorizontalDivider()
    }
}

@Composable
private fun PokemonInfo(pokemon: PokemonSummary) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = pokemon.image,
            contentDescription = "Pokemon Image",
            modifier = Modifier.size(100.dp),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.ditto),
        )
        Column {
            Text(
                pokemon.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                pokemon.idLabel,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun TypeIcons(types: List<TypeStructure>) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (type in types.sortedBy { it.slot }) {
            val resourceName = "type${type.id}"
            val resId = remember(type.id) {
                context.resources.getIdentifier(resourceName, "drawable", context.packageName)
            }
            if (resId != 0) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = "Type",
                    modifier = Modifier.height(34.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPokemonElement() {
    PokemonElement(
        pokemon = PokemonSummary(
            id = 1,
            names = arrayOf(
                Name("Bulbasaur", "en")
            ),
            types = arrayOf(
                TypeStructure(1, 1),
                TypeStructure(5, 2)
            )
        )
    )
}