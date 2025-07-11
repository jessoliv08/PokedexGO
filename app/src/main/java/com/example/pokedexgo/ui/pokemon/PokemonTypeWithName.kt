package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.model.pokemon.PokemonType
import com.example.pokedexgo.ui.generic.MatchColor

@Composable
fun PokemonTypeWithName(
    types: List<TypeStructure>,
    getPokemonType: (Int) -> PokemonType?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (type in types.sortedBy { it.slot }) {
            TypeWithName(
                getPokemonType(type.id)
            )
        }
    }
}

@Composable
fun TypeWithName(
    type: PokemonType?
) {
    type?.let { pokemonType ->
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
            Box(
                modifier = Modifier
                    .background(
                        color = MatchColor(
                            resourceName = pokemonType.name.toLowerCase(Locale.current)
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .width(100.dp)
            ) {
                Text(
                    text = pokemonType.name,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun TypeIcons(types: List<TypeStructure>) {
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
