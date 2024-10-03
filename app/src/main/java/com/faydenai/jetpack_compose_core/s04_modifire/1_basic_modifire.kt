package com.faydenai.jetpack_compose_core.s04_modifire

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Basic Modifier Usage in Jetpack Compose
 *
 * This file includes basic modifier aspects like padding, alignment, background, size, etc.
 *
 * Modifier Aspects:
 * 1. Padding
 * 2. Alignment
 * 3. Size
 * 4. Background
 * 5. FillMaxWidth/FillMaxHeight
 * 6. Spacer
 **/

// Simple example demonstrating padding, alignment, and background color
@Composable
fun SimpleModifierExample() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        BasicText(
            text = "Simple Example",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// Complex example with padding, alignment, weight, size, and background
@Composable
fun ComplexModifierExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .9f),
                                MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .9f)
                            )
                        )
                    )
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Padding, Weight, and Alignment",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .8f),
                                MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .9f)
                            )
                        )
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Basic Size and Background",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Preview for simple and complex examples
@Preview(showBackground = true)
@Composable
fun PreviewSimpleModifierExample() {
    SimpleModifierExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexModifierExample() {
    ComplexModifierExample()
}
