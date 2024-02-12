package com.yakogdan.kinopoiskkostyrko.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
    Row(
        modifier = Modifier
            .padding(24.dp)
    ) {
        GlideImage(
            modifier = Modifier.height(63.dp),
            contentDescription = null,
            model = film.posterUrl
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(bottom = 24.dp),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                text = film.nameRu.toString()
            )

            Row {
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.background,
                    text = film.genres?.get(0)?.genre ?: ""
                )
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.background,
                    text = film.year.toString()
                )
            }
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