package com.example.pokedexgo.ui.pokemon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pokedexgo.model.state.MoveViewState

private const val DELAY_ANIMATION = 200

@Composable
fun MoveBottomModalWithAnimation(
    modal: MoveViewState,
    onHideModal: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = modal.shouldShowModal,
            enter = fadeIn(),
            exit = fadeOut(animationSpec = tween(delayMillis = DELAY_ANIMATION)),
            modifier = Modifier,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onHideModal() },
                    )
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.4f)),
            )
        }
        AnimatedVisibility(
            visible = modal.shouldShowModal,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(delayMillis = DELAY_ANIMATION)
            ),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier.clickable(
                enabled = false,
                onClick = {}), // Intercept click from scrim to do nothing
        ) {
            MoveModal(
                modal = modal
            )
        }
    }
}