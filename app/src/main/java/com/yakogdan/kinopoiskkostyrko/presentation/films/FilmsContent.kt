package com.yakogdan.kinopoiskkostyrko.presentation.films

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.kinopoiskkostyrko.R
import com.yakogdan.kinopoiskkostyrko.presentation.item.FilmCard

@Composable
fun FilmsContent(component: FilmsComponent) {

    val state by component.model.collectAsState()
    when (val filmState = state.filmsState) {
        PopularStore.State.FilmsState.Error -> {
            Error()
        }

        PopularStore.State.FilmsState.Initial -> {
            Initial()
        }

        is PopularStore.State.FilmsState.Loaded -> {
            Loaded(filmState, component)
        }

        PopularStore.State.FilmsState.Loading -> {
            Loading()
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Composable
private fun Loaded(
    filmState: PopularStore.State.FilmsState.Loaded,
    component: FilmsComponent
) {
    Column {
        Text(modifier = Modifier.padding(10.dp), text = "Популярные", fontSize = 25.sp)
        Box(modifier = Modifier.fillMaxSize()) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Button(modifier = Modifier.weight(1f), onClick = { }) {
                    Text(text = "Популярные")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(modifier = Modifier.weight(1f), onClick = { }) {
                    Text(text = "Избранное")
                }
            }
        }
    }
}

@Composable
private fun Initial() {
    Text(text = "Initial", color = Color.Red)
}

@Composable
private fun Error() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = stringResource(
                R.string.error_icon
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.error_check_connection),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}