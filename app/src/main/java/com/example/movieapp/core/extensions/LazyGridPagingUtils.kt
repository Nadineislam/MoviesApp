package com.example.movieapp.core.extensions

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.filter

@Composable
fun LazyGridState.onBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) = apply {
    val shouldLoadMoreBuffer = remember(layoutInfo.totalItemsCount) {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false

            val bufferIndex = layoutInfo.totalItemsCount - 1 - buffer
            lastVisibleItem.index >= bufferIndex
        }
    }

    LaunchedEffect(shouldLoadMoreBuffer) {
        snapshotFlow { shouldLoadMoreBuffer.value }
            .filter { it }
            .collect { loadMore() }
    }
}