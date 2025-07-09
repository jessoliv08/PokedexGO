package com.example.pokedexgo.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexgo.model.generic.ResultPokemon
import com.example.pokedexgo.ui.theme.PokemonSectionColor
import com.example.pokedexgo.viewmodel.PokemonViewModel

@Composable
fun PokemonList(viewModel : PokemonViewModel) {
    LaunchedEffect(Unit) {
        viewModel.setupInitialInfo()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val querySearch by viewModel.querySearch.collectAsState()
            TopBar(viewModel, querySearch)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(innerPadding),
        ) {
            val viewState by viewModel.viewState.collectAsState()
            when (viewState) {
                is ResultPokemon.Empty -> {
                    Text(
                        text = stringResource((viewState as ResultPokemon.Empty).message),
                        modifier = Modifier.align(Alignment.Center).padding(horizontal = 48.dp),
                        textAlign = TextAlign.Center
                    )
                }
                is ResultPokemon.Error -> {
                    Text(
                        text = stringResource((viewState as ResultPokemon.Error).message),
                        modifier = Modifier.align(Alignment.Center).padding(horizontal = 48.dp),
                        textAlign = TextAlign.Center
                    )
                }
                ResultPokemon.Loading -> {
                    PokemonListLoading(Modifier.align(Alignment.Center))
                }
                is ResultPokemon.Success -> {
                    val result = (viewState as ResultPokemon.Success).dataWithFilter
                    LazyColumn {
                        items(result) { data ->
                            PokemonElement(pokemon = data)
                        }
                    }
                }
            }
        }
    }
    val filterData by viewModel.filterDataViewState.collectAsState()
    filterData?.let {
        FilterBottomModalWithAnimation(
            shouldShow = it.shouldShowModal,
            filterData = it,
            onApplyFilter = { viewModel.onApplyFilter(it) },
            onHideModal = { viewModel.shouldShowFilterModal(false) }
        )
    }
}

@Composable
private fun TopBar(
    viewModel : PokemonViewModel,
    querySearch: String
) {
    val topInset = WindowInsets.systemBars.asPaddingValues()
        .calculateTopPadding()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp + topInset)
            .background(PokemonSectionColor)
    ) {
        SearchBarWithFilter(
            query = querySearch,
            onQueryChange = {
                viewModel.search(it)
            },
            onFilterClick = {
                viewModel.shouldShowFilterModal(true)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}