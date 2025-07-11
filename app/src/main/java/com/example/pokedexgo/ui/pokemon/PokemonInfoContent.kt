package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.state.ContentViewState

@Composable
fun PokemonGeneralContent(
    content: ContentViewState.PokemonContentInfo
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
            text = content.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp).padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        Text(
            text = stringResource(R.string.extra_info),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LineInformation(
            icon = R.drawable.generation,
            label = R.string.generation,
            value = content.generation
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        LineInformation(
            icon = R.drawable.base_happiness,
            label = R.string.base_happiness,
            value = content.baseHappiness.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        LineInformation(
            icon = R.drawable.height,
            label = R.string.height,
            value = content.height.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))
        LineInformation(
            icon = R.drawable.weight,
            label = R.string.weight,
            value = content.weight.toString()
        )
        HorizontalDivider(modifier = Modifier.padding(start = 12.dp))

    }
}