package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.state.ContentViewState

@Composable
fun PokemonGeneralContent(
    content: ContentViewState.PokemonContentMain
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.capturing_data),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        content.habitat?.let { habitat ->
            LineInformation(
                icon = R.drawable.nature_svgrepo_com,
                label = R.string.habitat,
                value = habitat
            )
            HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        }
        LineInformation(
            icon = R.drawable.pokeball,
            label = R.string.capture,
            value = content.captureRate.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        LineInformation(
            icon = R.drawable.xp,
            label = R.string.base_experience,
            value = content.baseExperience.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp).padding(bottom = 16.dp))
        Text(
            text = stringResource(R.string.breeding_data),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LineInformation(
            icon = R.drawable.gender,
            label = R.string.gender,
            value = content.genderRate
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        content.eggGroups?.let { eggGroups ->
            LineInformation(
                icon = R.drawable.evolve,
                label = R.string.egg_groups,
                value = eggGroups.toString()
            )
        }

        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        LineInformation(
            icon = R.drawable.steps,
            label = R.string.hatch_counter,
            value = content.hatchCounter.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp).padding(bottom = 16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonGeneralContentPreview() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonGeneralContent(
            content = ContentViewState.PokemonContentMain(
                genderRate = "50% female",
                captureRate = 1,
                hatchCounter = 1,
                habitat = "grass",
                baseExperience = 1,
                eggGroups = listOf("bug"),
            )
        )
    }
}

