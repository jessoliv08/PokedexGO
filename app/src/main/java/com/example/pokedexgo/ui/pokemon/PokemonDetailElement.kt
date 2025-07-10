package com.example.pokedexgo.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.R
import com.example.pokedexgo.model.PokemonDetails
import com.example.pokedexgo.ui.generic.MatchColor
import com.example.pokedexgo.ui.theme.DarkerGray
import com.example.pokedexgo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailElement(
    pokemon: PokemonDetails,
    viewModel: PokemonDetailViewModel,
    onBackButton: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                viewModel = viewModel,
                pokemon = pokemon,
                resourceName = viewModel.getSlot1TypeName()?.toLowerCase(Locale.current),
                onBackButton = onBackButton
            )
        },
        bottomBar = {
            BottomBar(viewModel = viewModel)
        },
        content = { padding ->

        }
    )
}

@Composable
fun TopBar(
    viewModel: PokemonDetailViewModel,
    pokemon: PokemonDetails,
    resourceName: String?,
    onBackButton: () -> Unit
) {
    val topInset = WindowInsets.systemBars.asPaddingValues()
        .calculateTopPadding()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp + topInset)
                .background(
                    MatchColor(
                        resourceName ?: ""
                    )
                )
        ) {
            IconButton(
                onClick = onBackButton,
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = DarkerGray
                )
            }
            Text(
                text = stringResource(R.string.pokemon_details_title),
                color = DarkerGray,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
            )
        }
        PokemonMainInfo(
            pokemon = pokemon,
            getPokemonType = {
                viewModel.getPokemonType(it)
            }
        )
    }
}


@Composable
fun BottomBar(viewModel: PokemonDetailViewModel) {
    val tabButton by viewModel.bottomButtonViewState.collectAsState()
    val bottomInset = WindowInsets.systemBars.asPaddingValues()
        .calculateBottomPadding()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp + bottomInset)
            .background(
                MatchColor(
                    viewModel.getSlot1TypeName()?.toLowerCase(Locale.current) ?: ""
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabButton.forEach { data ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(
                        color = if (data.isSelected) {
                            Color.Gray.copy(alpha = 0.4f)
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable { viewModel.onContentSelect(data.id) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (data.isSelected) {
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier.padding(bottom = bottomInset, top = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(data.icon),
                        contentDescription = "Tab ${data.label}",
                        tint = if (data.isSelected) {
                            Color.White
                        } else {
                            DarkerGray
                        },
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = stringResource(data.label),
                        color = if (data.isSelected) {
                            Color.White
                        } else {
                            DarkerGray
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.height(20.dp)
                    )
                }
            }
        }
    }
}
