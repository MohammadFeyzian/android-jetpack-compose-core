package com.faydenai.jetpack_compose_core.s1_introduction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

/**
 * Introduction to Jetpack Compose
 *
 * Jetpack Compose is Android's modern toolkit for building native UI. It simplifies and accelerates UI
 * development on Android with less boilerplate code and more intuitive design, compared to the XML-based
 * layout system traditionally used in Android.
 *
 * It's declarative, meaning you describe the UI by calling composable functions and the UI automatically
 * updates itself when the data changes.
 *
 * Key features of Jetpack Compose:
 *
 * - Declarative UI: You define how UI should look for a given state, and Compose takes care of updating the UI when the state changes.
 * - Kotlin-based: Compose is fully written in Kotlin, using its powerful language features.
 * - Composable Functions: These functions, annotated with @Composable, build UI components.
 * - State Management: State is central in Jetpack Compose. You describe how UI should react to changes in data.
 * - Interoperability: Compose works well with existing XML views, allowing gradual migration.
 * - Modularity: Encourages reusable and testable UI components.
 *
 * Let's explore Jetpack Compose with some examples:
 **/

/**
 * Simple Example: A basic Composable function displaying a text message.
 *
 * This is a basic example where we define a composable function called `Greeting()`.
 * It accepts a name parameter and uses the `Text()` composable to display "Hello, <name>".
 **/

@Composable
fun Greeting(name: String) {
    // Displays a simple greeting message
    Text(text = "Hello, $name!")
}

// Preview the simple example in Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    Greeting("Android")
}

/**
 * Complex Example: A Composable function with state and a Button to change the displayed message dynamically.
 *
 * This example demonstrates a more advanced use case where we manage state inside a composable function
 * using `remember` and `mutableStateOf`. When the button is clicked, it updates the greeting message dynamically.
 *
 * Explanation:
 *
 * - State Management: We use `remember { mutableStateOf() }` to hold the state of the text. The state is mutable,
 *   meaning the UI will automatically recompose when the state changes.
 * - Layout Components: The `Column` composable is used to stack the `Text` and `Button` vertically. We also use
 *   `Spacer` to add space between components and `Modifier` to apply padding.
 * - Button Interaction: The `Button` composable triggers the change in the `name` state when clicked, which updates
 *   the greeting message to "Hello, Jetpack Compose!"
 **/

@Composable
fun GreetingWithButton() {
    // Managing the state of the text
    var name by remember { mutableStateOf("World") }

    // Composing the UI
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Displaying the text message based on the state of `name`
        Text(text = "Hello, $name!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Button to update the state
        Button(onClick = {
            name = "Jetpack Compose"
        }) {
            Text(text = "Change Greeting")
        }
    }
}

// Another way
@Composable
fun GreetingWithButton2() {

    var isFirst by remember { mutableStateOf(true) }

    val name = if (isFirst) "World!" else "Jetpack Compose"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello, $name!",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isFirst = !isFirst
            }
        ) {
            Text(text = "Change Greeting")
        }
    }
}

// Preview the complex example in Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewGreetingWithButton() {
    GreetingWithButton()
}

@Preview(showBackground = true)
@Composable
fun PreviewGreetingWithButton2() {
    GreetingWithButton2()
}
