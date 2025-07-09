package com.example.pokedexgo.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.FilterDataViewState
import com.example.pokedexgo.ui.theme.LightGray
import com.example.pokedexgo.ui.theme.PokemonSectionColor

@Composable
fun FilterSelectionView(
    filterData: FilterDataViewState,
    onApplyFilter: (FilterDataViewState) -> Unit = {}
) {
    val maxHeight = LocalConfiguration.current.screenHeightDp * 0.7

    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .heightIn(max = maxHeight.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val selectedFiltersSlot1 =
                remember { mutableStateListOf<String>().also { it.addAll(filterData.slot1Selected) } }
            TitleAndSelectAllOrNone(
                title = stringResource(R.string.filter_type_slot_1),
                modifier = Modifier.padding(top = 30.dp),
                selectAll = { selectedFiltersSlot1.addAll(filterData.allTypes) },
                removeAll = { selectedFiltersSlot1.removeAll(filterData.allTypes) }
            )
            CustomFilterChipsExample(
                selectedFilters = selectedFiltersSlot1,
                filters = filterData.allTypes,
                numberToBreak = 4
            )

            val selectedFiltersSlot2 =
                remember { mutableStateListOf<String>().also { it.addAll(filterData.slot2Selected) } }
            TitleAndSelectAllOrNone(
                title = stringResource(R.string.filter_type_slot_2),
                selectAll = { selectedFiltersSlot2.addAll(filterData.allTypes) },
                removeAll = { selectedFiltersSlot2.removeAll(filterData.allTypes) }
            )
            CustomFilterChipsExample(
                selectedFilters = selectedFiltersSlot2,
                filters = filterData.allTypes,
                numberToBreak = 4
            )

            val selectedGeneration =
                remember { mutableStateListOf<String>().also { it.addAll(filterData.selectedGeneration) } }
            TitleAndSelectAllOrNone(
                title = stringResource(R.string.filter_generation),
                selectAll = { selectedGeneration.addAll(filterData.allGeneration) },
                removeAll = { selectedGeneration.removeAll(filterData.allGeneration) }
            )
            CustomFilterChipsExample(
                selectedFilters = selectedGeneration,
                filters = filterData.allGeneration,
                numberToBreak = 6
            )

            val selectedHabitat =
                remember { mutableStateListOf<String>().also { it.addAll(filterData.selectedHabitat) } }
            TitleAndSelectAllOrNone(
                title = stringResource(R.string.filter_habitat),
                selectAll = { selectedHabitat.addAll(filterData.allHabitat) },
                removeAll = { selectedHabitat.removeAll(filterData.allHabitat) }
            )
            CustomFilterChipsExample(
                selectedFilters = selectedHabitat,
                filters = filterData.allHabitat,
                numberToBreak = 3
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 16.dp),
                onClick = {
                    onApplyFilter(
                        filterData.copy(
                            slot1Selected = selectedFiltersSlot1,
                            slot2Selected = selectedFiltersSlot2,
                            selectedGeneration = selectedGeneration,
                            selectedHabitat = selectedHabitat,
                            shouldShowModal = false
                        )
                    )
                },
            ) {
                Text(
                    stringResource(R.string.filter_apply),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun TitleAndSelectAllOrNone(
    title: String,
    selectAll: () -> Unit,
    removeAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TextButton(
                onClick = selectAll,
            ) {
                Text(stringResource(R.string.filter_type_select_all))
            }

            TextButton(
                onClick = removeAll,
            ) {
                Text(stringResource(R.string.filter_type_select_none))
            }
        }
    }
}

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
                            selectedContainerColor = matchColor(filter.toLowerCase(Locale.current)),
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

@Composable
private fun matchColor(resourceName: String): Color {
    val context = LocalContext.current
    val resId = remember(resourceName) {
        context.resources.getIdentifier(resourceName, "color", context.packageName)
    }
    return if (resId != 0) {
        colorResource(resId)
    } else {
        PokemonSectionColor
    }
}

@Preview(showBackground = true)
@Composable
fun CustomFilterChipsExamplePreview() {
    Column {
        FilterSelectionView(
            FilterDataViewState(
                slot1Selected = listOf("Grass", "Fire", "Water"),
                slot2Selected = listOf("Grass"),
                allTypes = listOf("Grass", "Fire", "Water", "Normal", "Poison"),
                shouldShowModal = false,
                allTypesWithId = emptyMap(),
                allGeneration = emptyList(),
                selectedGeneration = emptyList(),
                allHabitat = emptyList(),
                selectedHabitat = emptyList()
            )
        )
    }
}