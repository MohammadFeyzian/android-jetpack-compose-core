package com.faydenai.jetpack_compose_core.s4_modifire

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Intermediate Modifier Usage in Jetpack Compose
 *
 * This file covers intermediate modifier aspects like clickable, border, clip, offset, shadow.
 *
 * Modifier Aspects:
 * 1. Clickable
 * 2. Border
 * 3. Clip
 * 4. Offset
 * 5. Shadow
 **/

// Simple example with clickable, border, and clip
@Composable
fun SimpleClickableModifierExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(color = Color.Blue)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = CircleShape
            )
            .clickable { /* Handle click */ }
            .padding(16.dp)
    ) {
        BasicText(
            text = "Clickable Box",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// Complex example showcasing clickable, offset, and shadow
@Composable
fun ComplexOffsetModifierExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 20.dp, y = 10.dp)
                .clickable { /* Handle click */ }
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Offset & Clickable",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = (-20).dp, y = 10.dp)
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Offset",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .background(Color.Green)
                    .shadow(elevation = 8.dp, shape = RectangleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Box with Shadow",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

// Preview for simple and complex examples
@Preview(showBackground = true)
@Composable
fun PreviewSimpleClickableModifierExample() {
    SimpleClickableModifierExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexOffsetModifierExample() {
    ComplexOffsetModifierExample()
}
