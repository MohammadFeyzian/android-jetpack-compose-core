package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faydenai.jetpack_compose_review.R

/**
 * Jetpack Compose: Basic Layout Structures - Column
 *
 * Description:
 * A Column is one of the basic layout structures in Jetpack Compose. It arranges its children
 * vertically, one below the other. It can take various modifiers to control alignment, padding,
 * margins, and more. Columns are very useful when you want to create vertical lists of items
 * or UI components stacked on top of each other.
 *
 * Column Structure:
 * - Column(
 *      modifier: Modifier = Modifier,        // Optional, to modify the appearance or behavior
 *      verticalArrangement: Arrangement = Arrangement.Top,  // Controls spacing between items
 *      horizontalAlignment: Alignment = Alignment.Start     // Controls alignment of items inside the Column
 *   ) {
 *      // Children composables go here
 *   }
 *
 * Sample question:
 * Q: How does the Column layout work in Jetpack Compose, and how would you use it to arrange UI components vertically?
 **/


// Simple example of Column layout
@Composable
fun SimpleColumnExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // No space between items
//        verticalArrangement = Arrangement.SpaceBetween, // 2 Spaces in this example
//        verticalArrangement = Arrangement.SpaceEvenly,  // 4 Spaces in this example
//        verticalArrangement = Arrangement.SpaceAround,  // 6 Spaces in this example
//        verticalArrangement = Arrangement.spacedBy(100.dp) // Space between items
        horizontalAlignment = Alignment.CenterHorizontally,
//        horizontalAlignment = Alignment.Start,
//        horizontalAlignment = Alignment.End
    ) {
        Text(text = "Item 1", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Item 2", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Item 3", style = MaterialTheme.typography.bodyLarge)
    }
}

// Preview for simple example
@Preview(showBackground = true)
@Composable
fun PreviewSimpleColumnExample() {
    SimpleColumnExample()
}

/**
 * Complex Example:
 * In this example, we'll create a more complex Column layout that includes images, texts,
 * buttons, and applies padding, alignment, and arrangement to manage the layout more effectively.
 *
 * This example demonstrates how a Column can be used to build a screen layout in an Android app.
 **/

@Composable
fun ComplexColumnExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Jetpack Compose",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.sample_image),  // Replace with your drawable
            contentDescription = "Sample Image",
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "This is an example of a complex Column layout.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { /* TODO: Handle button click */ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Click Me")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Adding some space between elements

        Text(
            text = "Thank you for using Compose!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

// Preview for complex example
@Preview(showBackground = true)
@Composable
fun PreviewComplexColumnExample() {
    ComplexColumnExample()
}
