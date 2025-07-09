package com.example.pokedexgo.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun FilterSelectionView(
    filterData: FilterDataViewState,
    onApplyFilter: (FilterDataViewState) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val selectedFiltersSlot1 =
            remember { mutableStateListOf<String>().also { it.addAll(filterData.slot1Selected) } }
        TitleAndSelectAllOrNone(
            title = stringResource(R.string.filter_type_slot_1),
            selectAll = { selectedFiltersSlot1.addAll(filterData.allTypes) },
            removeAll = { selectedFiltersSlot1.removeAll(filterData.allTypes) }
        )
        CustomFilterChipsExample(
            selectedFilters = selectedFiltersSlot1,
            filters = filterData.allTypes
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
            filters = filterData.allTypes
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = {
                onApplyFilter(
                    filterData.copy(
                        slot1Selected = selectedFiltersSlot1,
                        slot2Selected = selectedFiltersSlot2,
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

@Composable
private fun TitleAndSelectAllOrNone(
    title: String,
    selectAll: () -> Unit,
    removeAll: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge,
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
) {
    Column(modifier = Modifier.padding(4.dp)) {
        filters.chunked(4).forEach { rowFilters ->
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
    return colorResource(resId)
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
                allTypesWithId = emptyMap()
            )
        )
    }
}