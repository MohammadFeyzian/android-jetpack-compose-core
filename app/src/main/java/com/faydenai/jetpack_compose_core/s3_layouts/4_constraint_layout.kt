package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.faydenai.jetpack_compose_review.R

/**
 * Jetpack Compose: Basic Layout Structures - ConstraintLayout
 *
 * Description:
 * ConstraintLayout is a layout system in Jetpack Compose that allows you to create complex layouts
 * by defining constraints between composables or between a composable and its parent.
 * It provides a powerful way to arrange components in a flexible manner, similar to the traditional
 * ConstraintLayout in the XML-based Android View system.
 *
 * ConstraintLayout Structure:
 * - ConstraintLayout {
 *      val references = createRefs()     // Define references for composables inside the layout
 *      Component1(modifier = Modifier.constrainAs(reference1) {  })  // Define constraints for each component
 *      Component2(modifier = Modifier.constrainAs(reference2) {  })  // Define constraints for each component
 *   }
 *
 * Sample question:
 * Q: How does the ConstraintLayout work in Jetpack Compose, and how would you position components relative to each other?
 **/

// Simple example of ConstraintLayout
@Composable
fun SimpleConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for composables
        val (button1, button2, text) = createRefs() // Part of the ConstraintLayoutScope

        // Button 1 (aligned to top-start of the layout)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) { // Part of the ConstraintLayoutScope
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        ) {
            Text("Button 1")
        }

        // Button 2 (aligned to top-end of the layout)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        ) {
            Text("Button 2")
        }

        // Text (centered between the two buttons horizontally)
        Text(
            text = "Centered Text, long text, long text, long text, long text",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button1.bottom, margin = 16.dp)
                start.linkTo(button1.end, margin = 16.dp)
                end.linkTo(button2.start, margin = 16.dp)
                width = Dimension.fillToConstraints // Force to fill the constraints only
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Preview for simple example
@Preview(showBackground = true)
@Composable
fun PreviewSimpleConstraintLayoutExample() {
    SimpleConstraintLayoutExample()
}

/**
 * Complex Example:
 * In this example, we'll create a more advanced layout with multiple elements positioned relative to
 * each other, using constraints for flexible and responsive layout design.
 **/

@Composable
fun ComplexConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references for composables
        val (image, text1, text2, button) = createRefs()

        // Image (aligned to top center of the screen)
        Image(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "Image",
            modifier = Modifier
                .size(150.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                }
        )

        // Text 1 (below the image, horizontally centered)
        Text(
            text = "Title Text",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(image.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            }
        )

        // Text 2 (below Text 1, with padding)
        Text(
            text = "Subtitle Text",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.constrainAs(text2) {
                top.linkTo(text1.bottom, margin = 8.dp)
                centerHorizontallyTo(parent)
            }
        )

        // Button (aligned to the bottom of the screen, horizontally centered)
        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            }
        ) {
            Text("Click Me")
        }
    }
}

// Preview for complex example
@Preview(showBackground = true)
@Composable
fun PreviewComplexConstraintLayoutExample() {
    ComplexConstraintLayoutExample()
}
