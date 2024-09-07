package com.faydenai.jetpack_compose_core.s1_introduction

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import android.widget.TextView
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Differences between Jetpack Compose and XML-based UI
 *
 * 1. Declarative vs Imperative UI:
 *
 *      - Jetpack Compose: Uses a declarative approach. You describe what the UI should look like for a given state,
 *        and Compose automatically updates the UI when the underlying data changes. This approach leads to more concise and readable code.
 *      - XML-based UI: Follows an imperative approach, where you define the UI structure in XML, and then manually update it through
 *        Java or Kotlin code when the data changes (e.g., calling findViewById() and then updating properties).
 *
 * 2. Single Language:
 *
 *      - Jetpack Compose: UI is written entirely in Kotlin, which allows you to avoid switching between XML and Kotlin/Java code.
 *      - XML-based UI: UI layout is defined in XML, and the behavior is handled in Java/Kotlin. You have to bridge the gap
 *        between XML and Kotlin by using findViewById or View Binding.
 *
 * 3. UI Reusability:
 *
 *      - Jetpack Compose: Encourages modular and reusable UI components through Composable functions,
 *        making code more maintainable and testable.
 *      - XML-based UI: Reusability in XML is more limited. You can create custom views or fragments,
 *        but it requires more effort and boilerplate code.
 *
 * 4. Performance:
 *
 *      - Jetpack Compose: Optimized for performance with fewer overdraws and better control over how components are recomposed.
 *      - XML-based UI: Can result in overdraws and inefficiencies, especially with deep view hierarchies and complex layouts.
 *
 * 5. Interoperability:
 *
 *      - Jetpack Compose: Easily integrates with existing XML views using AndroidView. You can mix Compose with traditional views,
 *        making it suitable for incremental migration.
 *      - XML-based UI: You cannot directly embed Jetpack Compose components into an XML layout. However, you can use
 *        ComposeView in XML to embed Compose into a traditional layout.
 *
 *
 * Summary:
 *
 * 1. Jetpack Compose uses a declarative UI, while XML-based UI is imperative.
 * 2. Jetpack Compose is fully written in Kotlin, while XML-based UI splits UI (XML) and logic (Kotlin/Java).
 * 3. Jetpack Compose supports better UI reusability with composable functions, whereas XML-based UI requires custom views.
 * 4. Jetpack Compose offers better performance optimizations with fewer overdraws.
 * 5. Jetpack Compose can interoperate with existing XML views using AndroidView.
 *
 * Below are simple and complex examples to demonstrate both approaches.
 **/

/**
 * Simple Example: Jetpack Compose UI compared to XML-based UI
 *
 * Jetpack Compose: A simple composable function that displays a greeting text.
 **/

@Composable
fun SimpleComposeGreeting() {
    Text(text = "Hello from Jetpack Compose!")
}

// Preview of the simple Jetpack Compose example
@Preview(showBackground = true)
@Composable
fun PreviewSimpleComposeGreeting() {
    SimpleComposeGreeting()
}

/**
 * XML-based equivalent (for context, not in Kotlin)
 *
 * XML (UI):
 * <TextView
 *   android:id="@+id/greetingTextView"
 *   android:layout_width="wrap_content"
 *   android:layout_height="wrap_content"
 *   android:text="Hello from XML!" />
 *
 * Kotlin (Code):
 * val textView = findViewById<TextView>(R.id.greetingTextView)
 * textView.text = "Hello from XML!"
 **/

/**
 * Complex Example: Interoperability between Jetpack Compose and XML-based UI
 *
 * This example shows how Jetpack Compose can integrate with an XML-based view using AndroidView.
 **/

@SuppressLint("SetTextI18n")
@Composable
fun ComposeWithXMLIntegration() {
    // Using AndroidView to include a traditional XML-based TextView in Jetpack Compose
    @Suppress("UNUSED_VARIABLE") val context = LocalContext.current
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // A composable Text in Jetpack Compose
        Text(text = "This is Jetpack Compose")

        Spacer(modifier = Modifier.height(16.dp))

        // Integrating XML TextView into Compose using AndroidView
        AndroidView(
            factory = { ctx ->
                TextView(ctx).apply {
                    text = "This is an XML TextView inside Compose!"
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Adding a Compose Button for interaction
        Button(onClick = {
            // Button logic can go here
        }) {
            Text(text = "Button in Compose")
        }
    }
}

// Preview the complex example with interoperability in Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewComposeWithXMLIntegration() {
    ComposeWithXMLIntegration()
}
