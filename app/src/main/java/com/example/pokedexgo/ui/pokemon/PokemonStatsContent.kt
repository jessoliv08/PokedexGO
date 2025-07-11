package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.Stat
import com.example.pokedexgo.model.state.ContentViewState

@Composable
fun PokemonStatsContent(
    content: ContentViewState.PokemonContentStats
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.stats),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        StatsScreen(content.stats)
    }
}

@Composable
fun StatsScreen(stats: List<Stat>) {
    Column(modifier = Modifier.padding(vertical = 16.dp).padding(end = 16.dp, start = 24.dp)) {
        stats.forEach { stat ->
            StatSlider(stat.name, stat.base_stat)
        }
    }
}