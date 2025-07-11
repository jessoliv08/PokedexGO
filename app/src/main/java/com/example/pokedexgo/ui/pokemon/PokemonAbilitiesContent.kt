package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.pokedexgo.model.ability.Ability
import com.example.pokedexgo.model.state.ContentViewState

@Composable
fun PokemonAbilitiesContent(
    content: ContentViewState.PokemonContentAbilities
) {
    content.abilities?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (pokemonAbility in content.abilities.sortedBy { it.slot }) {
                val abilityMoreInfo = content.abilitiesCompleted?.find {
                    it.id == pokemonAbility.ability.id
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(
                            R.string.pokemon_slot,
                            pokemonAbility.slot.toString()
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    if (pokemonAbility.is_hidden) {
                        Text(
                            text = stringResource(R.string.is_hidden),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    abilityMoreInfo?.name?.let { abilityName ->
                        Text(
                            text = abilityName,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Text(
                        text = pokemonAbility.ability.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                abilityMoreInfo?.let { ability ->
                    AbilityDetails(ability)
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun AbilityDetails(ability: Ability) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.description_label),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = ability.flavorEntrySimple.flavorText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        ability.flavorEntrySimple.versionGroup?.let {
            Text(
                text = stringResource(R.string.version_group),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Text(
            text = stringResource(R.string.effect_label),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = ability.effectEntrySimple.effect,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        ability.effectEntrySimple.shortEffect?.let {
            Text(
                text = stringResource(R.string.short_effect_label),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ability.effectEntrySimple.shortEffect,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}