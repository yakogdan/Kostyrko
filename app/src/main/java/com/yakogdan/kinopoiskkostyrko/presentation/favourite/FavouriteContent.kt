package com.yakogdan.kinopoiskkostyrko.presentation.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film

@Composable
fun FavouriteContent(component: FavouriteComponent) {

    val state by component.model.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = state.filmItems,
            key = { _, item -> item.kinopoiskId }
        ) { _, item ->
            FilmCard(
                filmItem = item,
                onClick = {
                    component.onFilmItemClick(item)
                }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun FilmCard(
    filmItem: Film,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.Blue),
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
//                .fillMaxSize()
//                .sizeIn(minHeight = 196.dp)
                .padding(24.dp)
        ) {
            GlideImage(
                modifier = Modifier,
                contentDescription = null,
                model = filmItem.posterUrl
            )
            Column {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp),
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 48.sp),
                    text = filmItem.nameRu.toString()
                )

                Row {
                    Text(
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.background,
                        text = filmItem.genres?.get(0)?.genre ?: ""
                    )
                    Text(
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.background,
                        text = filmItem.year.toString()
                    )
                }

            }
        }
    }
}