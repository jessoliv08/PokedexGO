package com.example.pokedexgo.ui.list.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RangeSliderFilter(
    title: String,
    initialRange: ClosedFloatingPointRange<Float> = 50f..200f,
    minValue: Float = 0f,
    maxValue: Float = 3000f,
    onRangeChanged: (ClosedFloatingPointRange<Float>) -> Unit
) {
    var range by remember { mutableStateOf(initialRange) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Min: ${range.start.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "Max: ${range.endInclusive.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        RangeSlider(
            value = range,
            onValueChange = {
                range = it
                onRangeChanged(range)
            },
            valueRange = minValue..maxValue,
            steps = 10, // Adjust for granularity
            modifier = Modifier.fillMaxWidth()
        )
    }
}
