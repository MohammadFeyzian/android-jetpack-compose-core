package com.faydenai.jetpack_compose_core.s08_side_effects

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/**
 * Side Effects in Jetpack Compose:
 * Side effects are operations that affect the outside world (e.g., logging, network requests, etc.) or depend on
 * external conditions that are not part of the composable's UI state. Jetpack Compose offers different composable
 * functions to handle side effects: `LaunchedEffect`, `SideEffect`, and `DisposableEffect`. These functions are designed
 * to integrate with the Compose lifecycle to ensure side effects are executed or cleaned up at the right time.
 *
 * Jetpack Compose uses a declarative programming model, so it rebuilds composables whenever
 * state changes. However, certain operations that have to occur outside of the composition (e.g.,
 * launching coroutines, triggering one-time events) must be handled carefully with the help of
 * side effects. Jetpack Compose provides several mechanisms to handle side effects:
 *
 * 1. `LaunchedEffect`: Used to launch suspendable functions such as coroutines. This effect is tied
 *    to the lifecycle of the composable and runs for the entire lifecycle of a composable. It can be used
 *    for actions like network calls, animations, or any suspendable side effects.
 *
 * 2. `SideEffect`: Runs synchronously after each successful recomposition. It is used to communicate between
 *    Compose and non-Compose code or handle some side effects that need to run on every recomposition. It's great
 *    for syncing external state with the UI.
 *
 * 3. `DisposableEffect`: Used when you need to set up and tear down resources based on the lifecycle of a composable.
 *    It provides `onDispose()` to clean up resources (e.g., close a connection, unregister a listener) when a
 *    composable leaves the composition.
 *
 * These tools help manage the lifecycles and the possible changes during the UI updates while
 * ensuring proper cleanup and efficient usage of resources.
 */

/**
 * Example using `LaunchedEffect`:
 * This example simulates a network call that updates data after a delay when a button is clicked.
 */
@Composable
fun LaunchedEffectExample() {
    var data by remember { mutableStateOf("Waiting for data...") }
    var fetchData by remember { mutableStateOf(false) }

    // LaunchedEffect will trigger data fetching when fetchData is set to true
    LaunchedEffect(fetchData) {
        if (fetchData) {
            delay(2000) // Simulating a network call delay
            data = "Data fetched successfully"
            fetchData = false // Reset to prevent continuous fetching
        }
    }

    Column {
        Text("Status: $data")
        Button(onClick = { fetchData = true }) {
            Text("Fetch Data")
        }
    }
}

// Preview for LaunchedEffect example
@Preview(showBackground = true)
@Composable
fun LaunchedEffectExamplePreview() {
    LaunchedEffectExample()
}


/**
 * Example using `SideEffect`:
 * This example demonstrates logging a message whenever the Composable recomposes.
 */
@Composable
fun SideEffectExample() {
    var counter by remember { mutableIntStateOf(0) }

    // SideEffect will log every time the Composable is recomposed
    SideEffect {
        println("Composable has been recomposed with counter value: $counter")
    }

    Column {
        Text("Counter: $counter")
        Button(onClick = { counter++ }) {
            Text("Increment Counter")
        }
    }
}

// Preview for SideEffect example
@Preview(showBackground = true)
@Composable
fun SideEffectExamplePreview() {
    SideEffectExample()
}

/**
 * Example using `DisposableEffect`:
 * This example demonstrates how to set up and clean up a listener when a composable enters or leaves the composition.
 */
@Composable
fun DisposableEffectExample() {
    var isActive by remember { mutableStateOf(true) }

    DisposableEffect(isActive) {
        if (isActive) {
            // Code to set up resources
            println("----> Listener registered")
        }

        // Cleanup logic when composable leaves the composition
        onDispose {
            println("----> Listener unregistered")
        }
    }

    Column {
        Button(onClick = { isActive = !isActive }) {
            Text(if (isActive) "Deactivate Listener" else "Activate Listener")
        }
    }
}

// Preview for DisposableEffect example
@Preview(showBackground = true)
@Composable
fun DisposableEffectExamplePreview() {
    DisposableEffectExample()
}

/**
 * Complex Example: Combining LaunchedEffect, SideEffect, and DisposableEffect
 * This example simulates a network call, logs recompositions, and registers/unregisters a listener during the lifecycle
 * of a Composable.
 */
@Composable
fun ComplexSideEffectsExample() {
    var count by remember { mutableIntStateOf(0) }
    var data by remember { mutableStateOf("Fetching data...") }

    // LaunchedEffect to simulate data fetching based on 'count' changes.
    LaunchedEffect(count) {
        data = "Fetching data for count: $count..."
        delay(2000L)  // Simulate a network call delay
        data = "Fetched data for count: $count"
    }

    // SideEffect to log count changes.
    SideEffect {
        println("Count has changed to: $count")
    }

    // DisposableEffect to handle resource cleanup.
    DisposableEffect(Unit) {
        println("Component entered composition with count: $count")
        onDispose {
            println("Cleaning up resources for count: $count")
        }
    }

    Column {
        Text(text = data)
        Button(onClick = { count++ }) {
            Text(text = "Fetch New Data")
        }

        // Additional control to clear data
        Button(onClick = { data = "Data cleared"; count = 0 }) {
            Text(text = "Clear Data")
        }
    }
}

// Preview for ComplexSideEffectsExample
@Preview(showBackground = true)
@Composable
fun ComplexSideEffectsExamplePreview() {
    ComplexSideEffectsExample()
}