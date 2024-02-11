package com.yakogdan.kinopoiskkostyrko.presentation.favourite

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
fun FavouriteContent(component: FavouriteComponent) {

    val state by component.model.collectAsState()
    Column {
        Text(text = "Избранное", color = Color.Red)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(
                items = state.filmItems,
                key = { _, item -> item.kinopoiskId }
            ) { _, item ->
                FilmCard(
                    filmItem = item,
                    onClick = {
                        component.onFilmItemClick(item)
                    },
                    onLongClick = {}
                )
            }
        }
    }
}