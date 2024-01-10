package com.example.movieapp.core.utils

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun <T> GetResourceList(
    resourceState: Resource<T>,
    emptyListMessage: String,
    successBlock: @Composable (T?) -> Unit
) {
    when (resourceState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val items = resourceState.data
            successBlock(items)
        }

        is Resource.Error -> {
            val message = resourceState.message ?: emptyListMessage
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
                .show()
        }
    }
}