package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.state.ContentViewState
import com.example.pokedexgo.ui.generic.MatchColor

@Composable
fun PokemonMovesContent(
    content: ContentViewState.PokemonContentMoves,
    resourceName: String?,
    onMoveClick: (Int) -> Unit = {}
) {
    val color = MatchColor(resourceName ?: "")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(R.string.name),
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(R.string.learn_method),
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.level),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End,
                color = color
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = color
        )
        content.moves?.let { moves ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(moves) { move ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMoveClick(move.id) }
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.weight(2f),
                            text = move.name,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            modifier = Modifier.weight(2f),
                            text = move.move_learn_method,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(
                                R.string.level_number,
                                move.level_learned_at),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}