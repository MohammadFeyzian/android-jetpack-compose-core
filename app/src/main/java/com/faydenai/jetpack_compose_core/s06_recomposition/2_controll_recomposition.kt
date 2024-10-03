package com.faydenai.jetpack_compose_core.s06_recomposition

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Topic: Controlling recomposition and avoiding unnecessary UI updates
 *
 * Jetpack Compose allows you to control recomposition to prevent unnecessary UI updates, which can improve performance.
 * Unnecessary recompositions occur when parts of the UI are re-executed without any meaningful changes, which can lead
 * to wasted processing and a less responsive app.
 *
 * Key Concepts:
 * 1. **remember**: Stores values across recompositions. Use it to prevent recomposing values that haven't changed.
 * 2. **rememberUpdatedState**: Keeps track of state that can change over time, but doesn't trigger recompositions.
 * 3. **derivedStateOf**: Creates derived states that are recomposed only when specific state changes.
 * 4. **keys**: By using keys in lists or recompositions, you ensure that only relevant items get recomposed.
 * 5. **LaunchedEffect/DisposableEffect**: These are used to manage side effects, but careful use of them can also avoid recompositions.
 *
 * Best practices include isolating state in smaller composable functions, using `remember` to cache results, and splitting
 * composables to control recomposition in a granular way.
 *
 **/

// Simple example: Controlling recomposition with `remember`
@Composable
fun SimpleControlledRecomposition() {
    // State that causes recomposition when changed
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Count: $count", style = MaterialTheme.typography.bodyLarge)

        // Button that triggers state change
        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }

        // This block will not be recomposed because it's remembered
        val rememberedValue = remember {
            "This is remembered -> initial count = $count"
        }
        Text(text = rememberedValue, style = MaterialTheme.typography.bodySmall)
    }
}

/**
 * In this example:
 * - `count` will trigger recomposition, but `rememberedValue` is stored across recompositions and won't be recomposed.
 * - The `remember` function ensures that `rememberedValue` doesn't get recalculated unnecessarily.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewSimpleControlledRecomposition() {
    SimpleControlledRecomposition()
}


// Complex example: Using `derivedStateOf` and `rememberUpdatedState` to control recomposition
@Composable
fun ComplexControlledRecomposition() {
    var inputText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter Text") }
        )

        // This button controls a different state and should recompose only when `isActive` changes
        Button(onClick = { isActive = !isActive }) {
            Text(if (isActive) "Deactivate" else "Activate")
        }

        // Using `derivedStateOf` to calculate length only when `inputText` changes
        val textLength by remember { derivedStateOf { inputText.length } }

        Text("Text Length: $textLength")

        // Using `rememberUpdatedState` to manage a value that shouldn't cause recomposition
        val updatedText by rememberUpdatedState(newValue = inputText)

        Text("Current Text: $updatedText")
    }
}

/**
 * In this complex example:
 * - `derivedStateOf`: The text length is derived from `inputText`, and this value will only be recomposed when `inputText` changes.
 * - `rememberUpdatedState`: Even though `inputText` changes, the updated value is stored and does not trigger unnecessary recomposition.
 *
 * This approach allows efficient control of recomposition by isolating changes and recalculations to the minimal necessary parts of the UI.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewComplexControlledRecomposition() {
    ComplexControlledRecomposition()
}
