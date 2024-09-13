package com.faydenai.jetpack_compose_core.s13_compose_viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Section: Jetpack Compose and ViewModel Integration
 *
 * In Jetpack Compose, you can easily integrate ViewModel to manage UI-related data and separate it
 * from UI logic. ViewModel works well with Jetpack Compose due to the reactive nature of Compose.
 *
 * Key Points:
 *
 * 1. **Composing with ViewModel**:
 *    ViewModel provides a way to store and manage UI-related data in a lifecycle-conscious way.
 *    Composables observe the state of the ViewModel, and any updates in the state trigger recomposition.
 *
 * 2. **Using viewModel() in Jetpack Compose**:
 *    To use a ViewModel in Compose, you can retrieve it using the `viewModel()` function.
 *    This function is lifecycle-aware and ensures you get the correct instance of your ViewModel.
 *
 * 3. **State and events between ViewModel and Composables**:
 *    - You use `LiveData`, `MutableLiveData`, `StateFlow`, or `MutableStateFlow` to maintain state in the ViewModel.
 *    - Composables observe these states and react to their changes.
 *    - Events like user interactions trigger updates in ViewModel state, and the UI is updated accordingly.
 */


/**
 * Complex Example:
 *
 * In this example, we will manage more complex state interactions between ViewModel and Composables.
 * The ViewModel will handle a list of items, allow adding new items, and display them dynamically in the UI.
 */

// Complex ViewModel for managing a list of items
class ItemListViewModel : ViewModel() {
    // Using MutableState to hold the state of the item list
    private val _items = MutableLiveData<List<String>>(emptyList())
    val items: LiveData<List<String>> = _items

    // Function to add a new item to the list
    fun addItem(item: String) {
        viewModelScope.launch {
            delay(300)
            _items.value = _items.value?.toMutableList()?.apply { add(item) }
        }
    }
}

// Composable that uses the ViewModel to display and manage a list of items
@Composable
fun ItemListScreen(viewModel: ItemListViewModel = viewModel()) {
    var newItem by remember { mutableStateOf("") }

    // Observe the item list from the ViewModel
    val items by viewModel.items.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = newItem,
            onValueChange = { newItem = it },
            label = { Text("New Item") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (newItem.isNotBlank()) {
                    viewModel.addItem(newItem)
                    newItem = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Item")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Items:",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display the list of items
        items.forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


// Preview for ItemListScreen
@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    ItemListScreen()
}
