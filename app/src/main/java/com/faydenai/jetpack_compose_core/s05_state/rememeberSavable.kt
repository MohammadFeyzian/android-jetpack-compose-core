package com.faydenai.jetpack_compose_core.s05_state

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment

/**
 * # `rememberSaveable` and Surviving Configuration Changes in Jetpack Compose
 *
 * ## What is `rememberSaveable`?
 * In Jetpack Compose, `remember` allows us to retain state across recompositions, but it does not retain state across
 * configuration changes like screen rotations or when the app is sent to the background and brought back.
 *
 * `rememberSaveable` is an enhanced version of `remember` that saves the state across such configuration changes.
 * It uses the Android framework’s saved instance state mechanism to persist data across configuration changes.
 *
 * ## Key Concepts:
 * - **`remember`**: Retains state across recompositions, but not across configuration changes.
 * - **`rememberSaveable`**: Retains state across recompositions and configuration changes (e.g., screen rotations, process death).
 *
 * Use `rememberSaveable` when you need to persist simple types like `Int`, `String`, `Boolean`, or objects implementing the `Parcelable` interface.
 *
 * ### Simple Example:
 * A counter example where the count state is saved across configuration changes (e.g., screen rotation).
 **/

// Simple example using rememberSaveable to retain state across configuration changes
@Composable
fun CounterWithRememberSaveable() {
    // Using rememberSaveable to save count across configuration changes
    var count by rememberSaveable { mutableIntStateOf(0) }

    // UI: Displaying the count and a button to increment the count
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}

// Preview for the simple example
@Preview(showBackground = true)
@Composable
fun PreviewCounterWithRememberSaveable() {
    CounterWithRememberSaveable()
}

/**
 * ## Complex Example:
 * A more complex example where multiple states are preserved using `rememberSaveable`. This could be a form with
 * multiple inputs (such as name and age), where the state of each input is preserved across configuration changes.
 *
 * In this case, the state for each field is managed using `rememberSaveable` to ensure that any changes made by
 * the user are retained even when the screen is rotated or the app is paused/resumed.
 **/

// Complex example using rememberSaveable for multiple states (e.g., a form)
@Composable
fun UserForm() {
    // Using rememberSaveable to save form inputs across configuration changes
    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }

    // UI: Form with inputs for name and age, and a submit button
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name Input Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Age Input Field
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(onClick = { /* Handle form submission */ }) {
            Text("Submit")
        }
    }
}

// Preview for the complex example
@Preview(showBackground = true)
@Composable
fun PreviewUserForm() {
    UserForm()
}
