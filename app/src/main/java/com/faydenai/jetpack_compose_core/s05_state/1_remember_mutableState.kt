package com.faydenai.jetpack_compose_core.s05_state

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * # State Management in Jetpack Compose with `remember` and `mutableStateOf`
 *
 * In Jetpack Compose, state management is an important concept because it allows the UI to respond to
 * data changes. The most common way to manage state in Jetpack Compose is using `remember` and `mutableStateOf`.
 *
 * ## `remember`:
 * The `remember` function is used to store a value in the composition. This means that the value will survive
 * recompositions, but it will be cleared when the composition is disposed (such as when the screen rotates).
 *
 * ## `mutableStateOf`:
 * The `mutableStateOf` function is used to hold a value that can change over time. When the value changes,
 * Jetpack Compose will automatically recompose the necessary UI parts to reflect the change.
 *
 * Together, `remember` and `mutableStateOf` are commonly used to manage local state within a composable function.
 *
 * ### Key Concepts:
 * - `remember`: Used to retain a value across recompositions.
 * - `mutableStateOf`: Used to store and track state changes. The value it holds can change over time, triggering recompositions.
 *
 * ## Simple Example:
 * A simple button that toggles between "ON" and "OFF" state when clicked.
 **/

// Simple example
@Composable
fun ToggleButtonExample() {
    // Remember the state (ON or OFF)
    var isOn by remember { mutableStateOf(false) }

    // UI: A Button that toggles between "ON" and "OFF" when clicked
    Button(
        onClick = { isOn = !isOn },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = if (isOn) "ON" else "OFF")
    }
}

// Preview for the simple example
@Preview(showBackground = true)
@Composable
fun PreviewToggleButtonExample() {
    ToggleButtonExample()
}

/**
 * ## Complex Example:
 * A more complex example showing a counter that increments with each button press and also demonstrates
 * how multiple states can be managed using `remember` and `mutableStateOf`.
 *
 * In this example, we have two buttons: one that increases the count, and another that resets it.
 * Additionally, the counter’s state is displayed and updated dynamically.
 **/

// Complex example
@Composable
fun CounterExample() {
    // State management for counter
    var count by remember { mutableIntStateOf(0) }

    // UI: Display count and two buttons (Increment and Reset)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the current count
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Increment Button
        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Reset Button
        Button(onClick = { count = 0 }) {
            Text(text = "Reset")
        }
    }
}

// Preview for the complex example
@Preview(showBackground = true)
@Composable
fun PreviewCounterExample() {
    CounterExample()
}
