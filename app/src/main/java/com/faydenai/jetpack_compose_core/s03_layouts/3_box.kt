package com.faydenai.jetpack_compose_core.s03_layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faydenai.jetpack_compose_review.R

/**
 * Jetpack Compose: Basic Layout Structures - Box
 *
 * Description:
 * A Box is a layout structure in Jetpack Compose that allows you to place its children on top of each other.
 * It is useful for scenarios where you want to overlap UI elements, or align content in all directions
 * within a certain area. You can also use modifiers to control positioning, padding, and alignment of
 * the elements inside the Box.
 *
 * Box Structure:
 * - Box(
 *      modifier: Modifier = Modifier,        // Optional, to modify the appearance or behavior
 *      contentAlignment: Alignment = Alignment.TopStart  // Aligns children inside the Box
 *   ) {
 *      // Children composables go here
 *   }
 *
 * Sample question:
 * Q: How does the Box layout work in Jetpack Compose, and how would you use it to layer UI components?
 **/

// Simple example of Box layout
@Composable
fun SimpleBoxExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Centered Text", style = MaterialTheme.typography.bodyLarge)
    }
}

// Preview for simple example
@Preview(showBackground = true)
@Composable
fun PreviewSimpleBoxExample() {
    SimpleBoxExample()
}

/**
 * Complex Example:
 * In this example, we'll create a more complex Box layout that includes multiple overlapping layers.
 * We'll add an image as the background, overlay it with text, and a button at the bottom-right corner.
 *
 * This demonstrates the ability of Box to layer and position components relative to each other.
 **/

@Composable
fun ComplexBoxExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.sample_image),  // Replace with your drawable
            contentDescription = "Background Image",
            modifier = Modifier
//                .fillMaxSize()
                .matchParentSize(), // Related to the BoxScope
            contentScale = ContentScale.Fit
        )

        // Centered text on top of the image
        Text(
            text = "Overlay Text",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center) // Related to the BoxScope
                .background(brush = SolidColor(Color.Gray), alpha = .8f)
        )

        // Button at the bottom-right corner
        Button(
            onClick = { /* TODO: Handle button click */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Action")
        }
    }
}

// Preview for complex example
@Preview(showBackground = true)
@Composable
fun PreviewComplexBoxExample() {
    ComplexBoxExample()
}
