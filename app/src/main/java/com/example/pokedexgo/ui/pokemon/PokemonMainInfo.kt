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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonDetails
import com.example.pokedexgo.model.PokemonType
import com.example.pokedexgo.model.generic.Name
import com.example.pokedexgo.model.generic.TypeStructure
import com.example.pokedexgo.ui.generic.MatchColor

@Composable
fun PokemonMainInfo(
    pokemon: PokemonDetails,
    getPokemonType: (Int) -> PokemonType?
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pokemon.image,
                modifier = Modifier.size(150.dp),
                contentDescription = "Pokemon Image",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.ditto),
                contentScale = ContentScale.FillBounds
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    pokemon.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    pokemon.idLabel,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )
                pokemon.types?.let {
                    TypeWithName(
                        types = it.toList(),
                        getPokemonType = getPokemonType
                    )
                }
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }

}

@Composable
private fun TypeWithName(
    types: List<TypeStructure>,
    getPokemonType: (Int) -> PokemonType?
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (type in types.sortedBy { it.slot }) {
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
                getPokemonType(type.id)?.let { pokemonType->
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
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonMainInfoPreview() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonMainInfo(
            pokemon = PokemonDetails(
                id = 1,
                evolution_chain = 1,
                gender_rate = 1,
                capture_rate = 1,
                base_happiness = 1,
                hatch_counter = 1,
                height = 1,
                weight = 1,
                flavor_text = "",
                generation = "1",
                habitat = "grass",
                base_experience = 1,
                names = arrayOf(Name("Bulbassaur", "en")),
                types = arrayOf(TypeStructure(1, 1), TypeStructure(2, 2)),
                stats = arrayOf(),
                egg_groups = arrayOf(),
                abilities = arrayOf(),
                moves = arrayOf(),
                image = ""
            )
        ) {
            return@PokemonMainInfo PokemonType(1, arrayOf(Name("grass", "en")))
        }
    }
}
