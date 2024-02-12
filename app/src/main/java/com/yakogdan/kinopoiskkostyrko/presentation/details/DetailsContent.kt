package com.yakogdan.kinopoiskkostyrko.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.yakogdan.kinopoiskkostyrko.R
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film

@Composable
fun DetailsContent(component: DetailsComponent) {
    val state by component.model.collectAsState()

    Box(modifier = Modifier) {
        when (val detailsState = state.detailsState) {
            DetailsStore.State.DetailsState.Error -> {
                Error()
            }

            DetailsStore.State.DetailsState.Initial -> {
                Initial()
            }

            is DetailsStore.State.DetailsState.Loaded -> {
                Details(film = detailsState.film)
            }

            DetailsStore.State.DetailsState.Loading -> {
                Loading()
            }
        }
    }

}

@Composable
private fun Error() {
    Text(text = "Error")
}

@Composable
private fun Initial() {
    Text(text = "Initial")
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Details(film: Film) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(R.string.film_poster),
            model = film.posterUrl
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                text = film.nameRu.toString()
            )
            Spacer(modifier = Modifier.height(14.dp))
            val genres = film.genres ?: listOf()
            val genresString = genres.joinToString(separator = ", ") { it.genre }
            Text(
                modifier = Modifier,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = "Жанры: $genresString"
            )
            Spacer(modifier = Modifier.height(10.dp))
            val countries = film.countries ?: listOf()
            val countriesString = countries.joinToString(separator = ", ") { it.country }
            Text(
                modifier = Modifier,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = "Жанры: $countriesString"
            )
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