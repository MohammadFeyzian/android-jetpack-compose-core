package com.faydenai.jetpack_compose_core.s5_state

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * # Unidirectional Data Flow in Jetpack Compose
 *
 * ## What is Unidirectional Data Flow?
 * Unidirectional Data Flow (UDF) is a design principle in Jetpack Compose where data flows in one direction:
 * from a single source of truth (often a ViewModel or parent composable) down to the UI.
 * When user actions occur, events are propagated upwards, but the state itself is modified
 * and controlled by the single source of truth. This makes the flow of data predictable and easy to follow.
 *
 * In Jetpack Compose, UDF ensures that:
 * 1. The state is controlled and updated by a single source.
 * 2. Composables observe the state and react to changes in the state.
 * 3. User interactions are propagated upwards via events to change the state at the source.
 *
 * ## Simple Example:
 * In this example, we have a parent composable that passes the state and an event handler down to the child
 * composable. The child composable is stateless and relies on the parent to manage the state.
 *
 * - **Parent**: Holds the state (`count`) and event handler (`onIncrement`).
 * - **Child**: Displays the state and invokes the event handler when an action is triggered (button click).
 *
 * This ensures a unidirectional flow of data: State flows from parent to child, and user events flow back up
 * from child to parent.
 **/

// Simple example: Parent managing state, child composable displaying and interacting
@Composable
fun CounterParent() {
    // Parent holds the state
    var count by remember { mutableIntStateOf(0) }

    // Parent passes state and event handler down to child
    CounterChild(count = count, onIncrement = { count++ })
}

@Composable
fun CounterChild(count: Int, onIncrement: () -> Unit) {
    // Child composable receives state and event handler from the parent
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onIncrement) {
            Text("Increment")
        }
    }
}

// Preview for the simple example
@Preview(showBackground = true)
@Composable
fun PreviewCounterParent() {
    CounterParent()
}

/**
 * ## Complex Example:
 * A more complex example with a scenario where there are multiple child composables, each displaying
 * and interacting with the state, but the state is still managed by the parent. This demonstrates
 * how unidirectional data flow scales to more complex UI structures.
 *
 * In this case, the parent manages two pieces of state (e.g., counters for two players in a game) and
 * passes state and event handlers to each child. Each child is responsible for displaying and
 * interacting with its respective piece of state, but the parent is the single source of truth.
 **/

// Complex example: Parent managing multiple states, children interacting with individual state
@Composable
fun MultiCounterParent() {
    // Parent holds multiple state variables
    var playerOneScore by remember { mutableIntStateOf(0) }
    var playerTwoScore by remember { mutableIntStateOf(0) }

    // UI: Parent passes state and event handlers down to multiple child composables
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Player Scores",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Player One Counter
        CounterChild(count = playerOneScore, onIncrement = { playerOneScore++ })

        Spacer(modifier = Modifier.height(16.dp))

        // Player Two Counter
        CounterChild(count = playerTwoScore, onIncrement = { playerTwoScore++ })
    }
}

// Preview for the complex example
@Preview(showBackground = true)
@Composable
fun PreviewMultiCounterParent() {
    MultiCounterParent()
}
