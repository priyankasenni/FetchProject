package com.example.fetchproject.ui.theme.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fetchproject.viewmodel.ItemViewModel
import com.example.fetchproject.R
import com.example.fetchproject.utils.isInternetAvailable

@Composable
fun ItemListScreen(viewModel: ItemViewModel, context: Context) {
    // Observe the list of items from the ViewModel
    val items = viewModel.items.observeAsState(emptyList())

    // Trigger data fetching when the composable is first displayed
    LaunchedEffect(Unit) {
        if (isInternetAvailable(context)) {
            viewModel.fetchItems()
        } else {
            // Show a message if there is no internet
            Toast.makeText(context, context.getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (items.value.isEmpty()) {
                    // Display spinner inside a Box
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp)
                        )
                    }
                } else {
                    val groupedItems = items.value.groupBy { it.listId }

                    // Display the grouped list
                    groupedItems.forEach { (listId, listItems) ->
                        Text(
                            text = context.getString(R.string.list_id_label, listId),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(16.dp)
                        )
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(listItems) { item ->
                                Text(
                                    text = item.name ?: context.getString(R.string.unnamed_item),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
