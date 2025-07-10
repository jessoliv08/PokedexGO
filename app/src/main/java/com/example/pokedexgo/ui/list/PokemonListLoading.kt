package com.example.pokedexgo.ui.list

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PokemonLoading(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().heightIn(max = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Loading",
        )
        MovingPokeball()
    }
}

@Composable
fun MovingPokeball() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(160.dp)
                .graphicsLayer {
                    rotationZ = rotation
                    transformOrigin = TransformOrigin.Center
                }
        ) {
            val pokeballRadius = size.minDimension / 2
            val center = Offset(size.width / 2, size.height / 2)

            // Top red half
            drawArc(
                color = Color.Red,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(center.x - pokeballRadius, center.y - pokeballRadius),
                size = Size(pokeballRadius * 2, pokeballRadius * 2)
            )

            // Bottom white half
            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(center.x - pokeballRadius, center.y - pokeballRadius),
                size = Size(pokeballRadius * 2, pokeballRadius * 2)
            )

            // Middle black line
            drawLine(
                color = Color.Black,
                start = Offset(center.x - pokeballRadius, center.y),
                end = Offset(center.x + pokeballRadius, center.y),
                strokeWidth = 8f
            )

            // Outer button (black circle)
            drawCircle(
                color = Color.Black,
                radius = pokeballRadius * 0.25f,
                center = center
            )

            // Inner button (white circle)
            drawCircle(
                color = Color.White,
                radius = pokeballRadius * 0.125f,
                center = center
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPokemonListLoading() {
    PokemonLoading(Modifier)
}
