package com.faydenai.jetpack_compose_core.s06_recomposition

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Topic: Key Best Practices to Ensure Efficient Recomposition
 *
 * Efficient recomposition in Jetpack Compose is critical to avoid unnecessary UI updates and to ensure performance is not degraded.
 * Best practices can help manage and optimize recompositions, making your app more responsive and reducing redundant processing.
 *
 * Best Practices:
 * 1. **Use remember wisely**: Cache state values or objects that should persist across recompositions to avoid recalculating them unnecessarily.
 * 2. **State hoisting**: Keep state outside of composables whenever possible. Pass state down as parameters to reduce unintentional recompositions.
 * 3. **Break down composables**: Split large composables into smaller ones to minimize the scope of recomposition. Smaller composables allow more granular control over recomposition.
 * 4. **Avoid excessive recomposition**: Make use of `derivedStateOf`, `rememberUpdatedState`, or `snapshotFlow` to avoid unnecessary recompositions from changing states.
 * 5. **Keys for list items**: Ensure that lists and repeated elements use stable keys to control how they recompose (e.g., `LazyColumn`).
 * 6. **Use immutable data structures**: Immutable objects help in preventing unexpected state changes that can trigger recomposition.
 * 7. **Side-effects**: Use `LaunchedEffect`, `DisposableEffect`, or `rememberCoroutineScope` only when absolutely necessary to prevent recomposition from restarting side-effects unintentionally.
 *
 * By following these practices, you can ensure that recompositions occur only when necessary and that your UI remains efficient and responsive.
 **/

// Simple example: Using `remember` and breaking down composables to minimize recomposition
@Composable
fun SimpleEfficientRecomposition() {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Counter Demo", style = MaterialTheme.typography.bodyLarge)

        // Break this into a smaller composable to avoid full recomposition of the Column
        CounterDisplay(count)

        Button(onClick = { count++ }) {
            Text("Increment Count")
        }
    }
}

@Composable
fun CounterDisplay(count: Int) {
    // This part of the UI will only recompose when `count` changes, not the whole column
    Text(text = "Count: $count", style = MaterialTheme.typography.bodyLarge)
}

/**
 * In this simple example:
 * - `remember` is used to cache the state variable `count` so that it doesn’t reset during recompositions.
 * - The `CounterDisplay` function is separated from the main `Column` to ensure only that specific part of the UI recomposes when the `count` changes.
 * - This is an example of breaking composables into smaller components to optimize recomposition.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewSimpleEfficientRecomposition() {
    SimpleEfficientRecomposition()
}


// Complex example: Using `derivedStateOf`, `snapshotFlow`, and avoiding unnecessary recompositions in a list
@Composable
fun ComplexEfficientRecomposition() {
    val items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3", "Item 4")) }
    var selectedItem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Only recomposes when the selected item changes
        SelectedItemDisplay(selectedItem)

        LazyColumn {
            itemsIndexed(items) { index, item ->
                ListItem(
                    item = item,
                    onClick = { selectedItem = item }
                )
            }
        }
    }
}

@Composable
fun SelectedItemDisplay(selectedItem: String) {
    // Using derivedStateOf to control recomposition based on derived state
    val displayText by remember {
        derivedStateOf { "Selected: $selectedItem" }
    }

    Text(text = displayText, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun ListItem(item: String, onClick: () -> Unit) {
    // This composable will recompose only when clicked, avoiding unnecessary updates to the entire list
    Button(onClick = onClick, modifier = Modifier.padding(8.dp)) {
        Text(item)
    }
}

/**
 * In this complex example:
 * - `derivedStateOf`: `SelectedItemDisplay` uses this to avoid recomposition unless `selectedItem` changes.
 * - `LazyColumn`: This list structure uses keys implicitly to prevent unnecessary recompositions of the entire list.
 * - Breaking down into smaller composables (`ListItem`) ensures that only the relevant items recompose when selected.
 *
 * These techniques ensure that recomposition happens in a targeted and efficient manner, optimizing UI performance.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewComplexEfficientRecomposition() {
    ComplexEfficientRecomposition()
}


/**
 * Another example
 */
@Composable
fun UseOfDerivedStateOf() {

    var counter1 by remember { mutableIntStateOf(0) }
    var counter2 by remember { mutableIntStateOf(0) }

    val totalCount by remember { derivedStateOf { counter1 + counter2 } }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total count: $totalCount",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Count1: $counter1",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { counter1++ }) {
                    Text(text = "Increment")
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "count2: $counter2",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { counter2++ }) {
                    Text(text = "Increment")
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewUseOfDerivedStateOf() {
    UseOfDerivedStateOf()
}