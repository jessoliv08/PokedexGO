package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatSlider(statName: String, statValue: Int, maxStat: Int = 150) {
    val sliderPosition = remember { mutableStateOf(statValue.toFloat()) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Text(
            text = statName.replace("-", " ").replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyLarge,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Slider(
                value = sliderPosition.value,
                onValueChange = {}, // Slider is read-only here
                valueRange = 0f..maxStat.toFloat(),
                enabled = false, // Make it read-only
                colors = SliderDefaults.colors(
                    disabledThumbColor = when {
                        statValue >= 99 -> Color.Green
                        statValue >= 60 -> Color(0xFFFFA500) // Orange
                        else -> Color.Red
                    },
                    disabledActiveTrackColor = when {
                        statValue >= 99 -> Color.Green
                        statValue >= 60 -> Color(0xFFFFA500) // Orange
                        else -> Color.Red
                    }
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = statValue.toString())
        }
    }
}
