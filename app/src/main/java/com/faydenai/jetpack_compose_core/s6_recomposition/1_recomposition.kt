package com.faydenai.jetpack_compose_core.s6_recomposition

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Topic: How recomposition works in Jetpack Compose
 *
 * Recomposition is the process in Jetpack Compose where the UI gets updated in response to changes in the state.
 * - Jetpack Compose uses a declarative approach where the UI is defined based on the state.
 * - When a state changes, Compose triggers a recomposition which recalculates the functions that depend on the changed state.
 * - Only the parts of the UI that are affected by the state change will be recomposed.
 *
 * Key Concepts:
 * 1. `remember`: Helps Compose retain the state across recompositions.
 * 2. `State`: Triggers recomposition when changed.
 *
 * Recomposition in Jetpack Compose allows for performance optimizations since only the UI components that depend on the updated state are recomposed,
 * instead of the entire UI. It is essential to understand how and when recomposition happens to avoid performance issues.
 *
 * Steps in recomposition:
 * - Jetpack Compose initially composes the UI.
 * - If a state that a composable function depends on changes, the function will be called again.
 * - This causes a recomposition of that composable function (and potentially its children).
 *
 * This behavior helps in creating efficient UIs, but it can also lead to performance issues if recomposition happens unnecessarily.
 * Understanding when and how recomposition works can help in writing optimized Compose code.
 **/

// Simple example: Understanding recomposition with a basic Counter
@Composable
fun SimpleCounter() {
    // State that triggers recomposition when changed
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Count: $count", style = MaterialTheme.typography.bodyLarge)

        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }
    }
}

/**
 * In this simple example:
 * - `count` is a state variable (`mutableStateOf`) that causes the `Text` composable to recompose whenever its value changes.
 * - When the button is clicked, `count` is updated and triggers a recomposition of the Text.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewSimpleCounter() {
    SimpleCounter()
}


// Complex example: Avoiding unnecessary recomposition by using keys and side effects
@Composable
fun ComplexRecomposition() {
    var outerState by remember { mutableIntStateOf(0) }
    var innerState by remember { mutableIntStateOf(0) }

    println("----> 1")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        println("----> 2")

        Text("Outer State: $outerState", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Outer Button affects only outerState
        Button(onClick = { outerState++ }) {
            Text("Outer Button")
        }

        println("----> 3")

        Spacer(modifier = Modifier.height(16.dp))

        // Complex part: innerState changes should only trigger recomposition for this specific Text composable
        RecompositionScope(innerState)

        println("----> 5")

        Spacer(modifier = Modifier.height(16.dp))

        // Inner Button affects innerState only
        Button(onClick = { innerState++ }) {
            Text("Inner Button")
        }

        println("----> 6")
    }
}

@Composable
fun RecompositionScope(innerState: Int) {
    println("----> 4")
    // Key ensures that only this part recomposes when innerState changes
    Text("Inner State: $innerState", style = MaterialTheme.typography.bodyLarge)
}

/**
 * In this complex example:
 * - We have two buttons, one for updating `outerState` and another for updating `innerState`.
 * - When the `outerState` is updated, the entire `Column` recomposes because it's part of the outer structure.
 * - When the `innerState` changes, only the `RecompositionScope` function recomposes due to state isolation.
 * - This helps prevent unnecessary recomposition of parts of the UI that haven't changed, making the app more efficient.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewComplexRecomposition() {
    ComplexRecomposition()
}
