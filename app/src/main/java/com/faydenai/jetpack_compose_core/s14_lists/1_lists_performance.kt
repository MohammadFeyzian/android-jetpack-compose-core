package com.faydenai.jetpack_compose_core.s14_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Handling Lists and Performance
 *
 * In Jetpack Compose, LazyColumn is used to efficiently display large sets of data. It works similarly
 * to RecyclerView but with better support for Compose's declarative UI model. It only renders the items
 * currently visible on the screen, which improves performance.
 *
 * For large datasets, especially when dealing with pagination or infinite scrolling, it is important to
 * handle data loading efficiently. This example demonstrates:
 *
 * - Using LazyColumn to display a list of items.
 * - Implementing pagination where the UI requests more data when the user scrolls to the bottom.
 * - Integration with ViewModel to manage state and data.
 * - Optimizing performance by loading data in batches of 20 items.
 *
 * The ViewModel holds a list of 100 items, but initially, only 20 are displayed. When the user reaches
 * the end of the displayed list, the next batch of 20 items is loaded.
 */

// Example ViewModel handling the data and pagination logic
class ListViewModel : ViewModel() {
    // Simulated list of 100 items
    private val fullItemList = List(100) { "Item #$it" }

    // MutableStateList holding the currently displayed items
    var itemList by mutableStateOf<List<String>>(emptyList())
        private set

    // Flag to indicate loading state
    var isLoading by mutableStateOf(false)
        private set

    // Current page
    private var currentPage = 0

    init {
        // Initially load the first page
        loadMoreData()
    }

    // Function to load the next page of items (20 items per page)
    fun loadMoreData() {
        if (isLoading || currentPage >= fullItemList.size / 20) return
        viewModelScope.launch {
            isLoading = true
            println("----> load page: $currentPage")
            delay(1000) // Simulate network delay
            val nextPageItems = fullItemList.chunked(20).getOrNull(currentPage) ?: emptyList()
            itemList = itemList + nextPageItems
            currentPage++
            isLoading = false
        }
    }
}

// Example composable that uses LazyColumn and pagination
@Composable
fun PaginatedListScreen(viewModel: ListViewModel = viewModel()) {
    val itemList by remember { derivedStateOf { viewModel.itemList } }
    val isLoading by remember { derivedStateOf { viewModel.isLoading } }
    val listState = rememberLazyListState()

    // Trigger loading more data when reaching the end of the list, with a buffer to avoid early triggers
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleItemIndex ->
            val totalItems = itemList.size
            // Only load more data when the user is near the end of the list (e.g., within 5 items)
            if (lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItems - 5 && !isLoading) {
                println("----> Requesting more data")
                viewModel.loadMoreData()
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(itemList) { item ->
                ListItem(item)
            }

            // Show a loading indicator when new data is being loaded
            if (isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ListItem(item: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = item,
            modifier = Modifier.padding(16.dp)
        )
    }
}

// Preview of the composable in Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewPaginatedList() {
    PaginatedListScreen(viewModel = ListViewModel())
}
