package com.yakogdan.kinopoiskkostyrko.presentation.popular

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmCard(
    filmItem: Film,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
//                .fillMaxSize()
//                .sizeIn(minHeight = 196.dp)
                .padding(24.dp)
        ) {
            GlideImage(
                modifier = Modifier.height(63.dp),
                contentDescription = null,
                model = filmItem.posterUrl
            )
            Column {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp),
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    text = filmItem.nameRu.toString()
                )

                Row {
                    Text(
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                        color = MaterialTheme.colorScheme.background,
                        text = filmItem.genres?.get(0)?.genre ?: ""
                    )
                    Text(
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                        color = MaterialTheme.colorScheme.background,
                        text = filmItem.year.toString()
                    )
                }

            }
        }
    }
}