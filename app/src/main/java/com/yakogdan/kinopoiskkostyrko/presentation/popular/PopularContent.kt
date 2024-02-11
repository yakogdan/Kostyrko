package com.yakogdan.kinopoiskkostyrko.presentation.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yakogdan.kinopoiskkostyrko.presentation.item.FilmCard

@Composable
fun PopularContent(component: PopularComponent) {

    val state by component.model.collectAsState()
    when (val filmState = state.filmsState) {
        PopularStore.State.FilmsState.Error -> {
            Text(text = "Error", color = Color.Red)
        }

        PopularStore.State.FilmsState.Initial -> {
            Text(text = "Initial", color = Color.Red)
        }

        is PopularStore.State.FilmsState.Loaded -> {
            Column {
                Text(text = "Популярное", color = Color.Red)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    itemsIndexed(
                        items = filmState.filmItems,
                        key = { _, item -> item.kinopoiskId }
                    ) { _, item ->
                        FilmCard(
                            filmItem = item,
                            onClick = {
                                component.onFilmItemClick(item)
                            },
                            onLongClick = {
                                component.addToFavourite(item)
                            }
                        )
                    }
                }
            }
        }

        PopularStore.State.FilmsState.Loading -> {
            Text(text = "Loading", color = Color.Red)
        }
    }
}