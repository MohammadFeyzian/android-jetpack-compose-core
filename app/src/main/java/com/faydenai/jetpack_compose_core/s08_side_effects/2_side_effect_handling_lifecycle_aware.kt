package com.faydenai.jetpack_compose_core.s08_side_effects

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Side-effect Handling and Lifecycle-aware Composables in Jetpack Compose:
 *
 * In Jetpack Compose, the composition lifecycle is critical to managing side effects correctly.
 * Compose rebuilds the UI when state changes, but certain side effects should be managed
 * in a way that considers lifecycle events, such as the creation, recomposition, and
 * disposal of composables. Lifecycle-aware composables ensure that resources are acquired and
 * released at the right time, preventing memory leaks or unintended behavior.
 *
 * Jetpack Compose provides several key tools for handling side effects with lifecycle awareness:
 *
 * 1. `remember`: This is used to preserve state across recompositions. It ensures that certain
 *    values or resources are remembered and maintained without being recalculated unnecessarily.
 *
 * 2. `rememberUpdatedState`: This allows you to maintain access to a value across recompositions
 *    even if the value itself changes. It is commonly used when launching side effects.
 *
 * 3. `DisposableEffect`: This effect allows you to perform actions when a composable enters or
 *    leaves the composition. It handles the setup and cleanup of resources, such as registering
 *    and unregistering listeners, in a lifecycle-aware manner.
 *
 * 4. `LaunchedEffect`: Launches coroutines in a way that ties their lifecycle to the composable.
 *    It runs suspendable functions that are lifecycle-aware and ensures cleanup when the
 *    composable leaves the composition.
 *
 * Proper handling of side effects in lifecycle-aware composables ensures that state updates,
 * network calls, and other actions occur at the right moments and don't persist longer than needed.
 */

/**
 * Example: Using `remember` to Preserve State Across Recomposition
 * This example demonstrates how to use `remember` to keep state values across recompositions,
 * preventing them from being reset when the composable function is recomposed.
 */
@Composable
fun RememberExample() {
    var count by remember { mutableIntStateOf(0) }

    Column {
        Text("Count: $count")
        Button(onClick = { count++ }) {
            Text("Increment Count")
        }
    }
}

// Preview for RememberExample
@Preview(showBackground = true)
@Composable
fun RememberExamplePreview() {
    RememberExample()
}

/**
 * Example: Using `rememberUpdatedState` to Maintain Access to Updated State in Effects
 * This example demonstrates how `rememberUpdatedState` can be used to ensure that the latest
 * value of a variable is used even if an effect, such as `LaunchedEffect`, has already been launched.
 */
@Composable
fun RememberUpdatedStateExample() {
    var isActive by remember { mutableStateOf(true) }
    val currentState = rememberUpdatedState(newValue = isActive)

    LaunchedEffect(Unit) {
        while (currentState.value) {
            delay(1000)
            println("Running task while active")
        }
        println("Task stopped")
    }

    Column {
        Text("Is Active: $isActive")
        Button(onClick = { isActive = !isActive }) {
            Text(if (isActive) "Deactivate" else "Activate")
        }
    }
}

// Preview for RememberUpdatedStateExample
@Preview(showBackground = true)
@Composable
fun RememberUpdatedStateExamplePreview() {
    RememberUpdatedStateExample()
}

/**
 * Example: Using `DisposableEffect` for Lifecycle-aware Resource Management
 * This example shows how to set up and clean up a resource, such as registering and unregistering
 * a listener when a composable enters or leaves the composition.
 */
@Composable
fun LifecycleAwareDisposableEffectExample() {
    var isListening by remember { mutableStateOf(true) }

    DisposableEffect(isListening) {
        // Set up resources
        println("Listener registered")

        // Cleanup resources when composable leaves the composition
        onDispose {
            println("Listener unregistered")
        }
    }

    Column {
        Button(onClick = { isListening = !isListening }) {
            Text(if (isListening) "Stop Listening" else "Start Listening")
        }
    }
}

// Preview for LifecycleAwareDisposableEffectExample
@Preview(showBackground = true)
@Composable
fun LifecycleAwareDisposableEffectExamplePreview() {
    LifecycleAwareDisposableEffectExample()
}

/**
 * Example: Combining Lifecycle-aware Side Effects in a Real-world Scenario
 * This example demonstrates how to handle a long-running background task using `rememberUpdatedState`
 * and how to ensure proper resource cleanup using `DisposableEffect`.
 */
@Composable
fun ComplexLifecycleAwareExample() {
    var isActive by remember { mutableStateOf(true) }
    var taskStatus by remember { mutableStateOf("Waiting for task...") }
    val currentIsActive = rememberUpdatedState(isActive)
    val coroutineScope = rememberCoroutineScope()

    // Launch a coroutine task that updates the status every 2 seconds while isActive is true
    LaunchedEffect(Unit) {
        while (currentIsActive.value) {
            delay(2000)
            taskStatus = "Task running..."
            println("Task running")
        }
        taskStatus = "Task stopped"
        println("Task stopped")
    }

    // Clean up when composable leaves the composition
    DisposableEffect(isActive) {
        println("Component active with status: $taskStatus")
        onDispose {
            println("Cleaning up resources")
        }
    }

    Column {
        Text("Task Status: $taskStatus")

        Button(onClick = {
            coroutineScope.launch {
                // Perform a task
            }
            isActive = !isActive
        }) {
            Text(if (isActive) "Stop Task" else "Start Task")
        }
    }
}

// Preview for ComplexLifecycleAwareExample
@Preview(showBackground = true)
@Composable
fun ComplexLifecycleAwareExamplePreview() {
    ComplexLifecycleAwareExample()
}
