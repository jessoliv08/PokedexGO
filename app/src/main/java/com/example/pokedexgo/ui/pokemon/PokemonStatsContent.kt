package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.Damages
import com.example.pokedexgo.model.Stat
import com.example.pokedexgo.model.state.ContentViewState
import com.example.pokedexgo.ui.theme.DarkerGray

@Composable
fun PokemonStatsContent(
    content: ContentViewState.PokemonContentStats
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.stats),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        StatsScreen(content.stats)
        HorizontalDivider()
        Text(
            text = stringResource(R.string.weakness),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp).padding(bottom = 16.dp)
        )
        DamageList(damages = content.weakness)
        Text(
            text = stringResource(R.string.strengths),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp).padding(bottom = 16.dp)
        )
        DamageList(damages = content.strengths)
    }
}

@Composable
private fun StatsScreen(stats: List<Stat>) {
    Column(modifier = Modifier.padding(vertical = 16.dp).padding(end = 16.dp, start = 24.dp)) {
        stats.forEach { stat ->
            StatSlider(stat.name, stat.base_stat)
        }
    }
}

@Composable
private fun DamageList(damages: Damages) {
    if (damages.values.isEmpty()) return
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        damages.values.chunked(2).forEach { twiceDamage ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                twiceDamage.forEach { damage ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TypeWithName(damage.type)
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .background(
                                    color = DarkerGray,
                                    shape = CircleShape
                                )
                                .padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = damage.damage.toString(),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }


    }

}