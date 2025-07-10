package com.example.pokedexgo.ui.list.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.ui.generic.MatchColor
import com.example.pokedexgo.ui.theme.LightGray


@Composable
fun CustomFilterChipsExample(
    selectedFilters: SnapshotStateList<String>,
    filters: List<String>,
    numberToBreak: Int
) {
    Column(modifier = Modifier.padding(4.dp)) {
        filters.chunked(numberToBreak).forEach { rowFilters ->
            Row(modifier = Modifier.padding(top = 8.dp)) {
                rowFilters.forEach { filter ->
                    FilterChip(
                        selected = selectedFilters.contains(filter),
                        onClick = {
                            if (selectedFilters.contains(filter)) {
                                selectedFilters.remove(filter)
                            } else {
                                selectedFilters.add(filter)
                            }
                        },
                        label = { Text(filter) },
                        leadingIcon = {
                            if (selectedFilters.contains(filter)) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MatchColor(filter.toLowerCase(Locale.current)),
                            selectedLabelColor = Color.Black,
                            containerColor = LightGray,
                            labelColor = Color.Black
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

    }
}

