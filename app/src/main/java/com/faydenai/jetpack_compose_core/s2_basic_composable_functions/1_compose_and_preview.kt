package com.faydenai.jetpack_compose_core.s2_basic_composable_functions

// File: BasicComposableFunctions.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Full details and description:
 *
 * @Composable functions are the core building blocks in Jetpack Compose.
 * They are used to define UI elements, and unlike XML layouts in traditional Android development,
 * Composable functions allow you to create UI using pure Kotlin code.
 * The @Composable annotation is what tells the Compose framework that this function is responsible
 * for generating UI elements.
 *
 * Key Points:
 * - The @Composable annotation is used to mark a function that contributes to the UI.
 * - The function doesn't return anything; instead, it describes the UI in a declarative way.
 * - A Composable function can call other Composable functions to build complex UIs.
 * - You can pass parameters to a Composable function, similar to a regular function, to make the UI dynamic.
 *
 * In essence, Composable functions represent the UI declaratively, making it easier to update
 * and handle the state in a reactive way.
 *
 * Sample Question:
 * Q: What is the purpose of the @Composable annotation in Jetpack Compose, and how does it differ
 *    from traditional XML-based layouts in Android?
 * A: The @Composable annotation marks a function as a Composable that builds UI elements.
 *    It allows you to declare your UI in Kotlin code, eliminating the need for XML layouts.
 *    In XML-based layouts, UI is declared separately from logic, whereas Compose unifies them
 *    in Kotlin functions. The UI reacts to changes in state automatically.
 **/

// Simple Example: Basic Composable Function
@Composable
fun Greeting(name: String) {
    // This function creates a Text UI element displaying "Hello, <name>"
    Text(text = "Hello, $name!", style = MaterialTheme.typography.bodyLarge)
}

/**
 * Explanation:
 * - In the simple example, the `Greeting` Composable function takes a `name` parameter
 *   and displays it inside a `Text` composable.
 * - `Text` is a pre-defined Composable function that renders text on the screen.
 * - `@Preview` is used to preview the UI within Android Studio without running the app on a device/emulator.
 **/

// Preview of the simple example
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting(name = "World")
}


// Complex Example: A More Complex Composable with Dynamic UI
@Composable
fun UserProfileCard(name: String, age: Int, isOnline: Boolean) {
    // Column is used to stack elements vertically
    Column(
        modifier = Modifier
            .padding(16.dp) // Works as margin: Before sizing and background
            .fillMaxWidth()
            .background(if (isOnline) Color.Green else Color.Gray)
            .padding(16.dp) // Works as padding: After sizing and background
    ) {

        Text(
            text = "Name: $name",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Age: $age",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Status: ${if (isOnline) "Online" else "Offline"}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Explanation:
 * - In the complex example, `UserProfileCard` creates a UI to display a user's profile
 *   with their name, age, and online status.
 * - The `Column` composable is used to arrange UI elements vertically.
 * - The `Modifier` is used to define layout and styling properties like padding, background color, etc.
 * - The background color changes dynamically based on the `isOnline` boolean.
 * - The `Text` composables display the user's name, age, and status.
 *
 * This example demonstrates a more advanced usage of Composable functions,
 * including dynamic styling based on parameters.
 **/

// Preview of the complex example
@Preview(showBackground = true)
@Composable
fun UserProfileCardPreviewTrue() {
    UserProfileCard(name = "Mohi Fayden", age = 28, isOnline = true)
}

@Preview(showBackground = true)
@Composable
fun UserProfileCardPreviewFalse() {
    UserProfileCard(name = "Mohi Fayden", age = 28, isOnline = false)
}

// Another Complex Example: A composable function displaying a card with text and a button
@Composable
fun ComplexCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Welcome to Jetpack Compose",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Learn Jetpack Compose with practical examples.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* TODO: Add action here */ }) {
                Text("Get Started")
            }
        }
    }
}

/**
 * Complex Explanation:
 *
 * The ComplexCard function creates a Card component that contains two text elements and a button.
 * - The Card is styled with padding, shape, and elevation using the Material3 theming system.
 * - Inside the Card, a Column is used to vertically stack the elements.
 * - The first Text displays a headline, the second Text shows a brief description, and a Button
 *   is provided at the bottom to handle an action.
 *
 * Usage:
 * - The ComplexCard composable can be used in any layout to display a more structured and styled
 *   UI component, demonstrating the power of Jetpack Compose.
 * - You can expand this by adding actions to the button or customizing the styling further.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewComplexCard() {
    ComplexCard()
}

