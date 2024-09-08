package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faydenai.jetpack_compose_review.R

/**
 * Jetpack Compose: Basic Layout Structures - Row
 *
 * Description:
 * A Row is one of the basic layout structures in Jetpack Compose. It arranges its children
 * horizontally, side by side. It can take various modifiers to control alignment, padding,
 * spacing, and more. Rows are ideal when you need to align components next to each other,
 * such as buttons, text, or images.
 *
 * Row Structure:
 * - Row(
 *      modifier: Modifier = Modifier,        // Optional, to modify the appearance or behavior
 *      horizontalArrangement: Arrangement = Arrangement.Start,  // Controls spacing between items
 *      verticalAlignment: Alignment = Alignment.Top            // Controls alignment of items inside the Row
 *   ) {
 *      // Children composables go here
 *   }
 *
 * Sample question:
 * Q: How does the Row layout work in Jetpack Compose, and how would you use it to arrange UI components horizontally?
 **/

// Simple example of Row layout
@Composable
fun SimpleRowExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
//        horizontalArrangement = Arrangement.SpaceBetween, // Check the Column for more description
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        horizontalArrangement = Arrangement.spacedBy(20.dp),
//        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Item 1", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Item 2", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Item 3", style = MaterialTheme.typography.bodyLarge)
    }
}

// Preview for simple example
@Preview(showBackground = true)
@Composable
fun PreviewSimpleRowExample() {
    SimpleRowExample()
}

/**
 * Complex Example:
 * In this example, we'll create a more complex Row layout that includes images, texts,
 * buttons, and applies padding, alignment, and arrangement to manage the layout more effectively.
 *
 * This example demonstrates how a Row can be used to build a horizontal UI in an Android app.
 **/

@Composable
fun ComplexRowExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_image),
            contentDescription = "Sample Image",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .weight(1f) // Related to the RowScope
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Title Text",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Subtitle Text",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Button(onClick = { /* TODO: Handle button click */ }) {
            Text(text = "Action")
        }
    }
}

// Preview for complex example
@Preview(showBackground = true)
@Composable
fun PreviewComplexRowExample() {
    ComplexRowExample()
}
