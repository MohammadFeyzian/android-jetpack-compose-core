package com.faydenai.jetpack_compose_core.s4_modifire

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

/**
 * Advanced Modifier Usage in Jetpack Compose
 *
 * This file covers advanced modifier aspects like graphicsLayer, rotate, scale, zIndex, and aspectRatio.
 *
 * Modifier Aspects:
 * 1. GraphicsLayer
 * 2. Rotate
 * 3. Scale
 * 4. ZIndex
 * 5. AspectRatio
 **/

// Simple example demonstrating aspectRatio and zIndex
@Composable
fun SimpleAspectRatioExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(1f) // Ensures a 1:1 aspect ratio
                .offset(x = (-50).dp, y = (-50).dp)
                .background(Color.Yellow)
                .align(Alignment.Center)
                .zIndex(1f) // Places this box on top
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .aspectRatio(1.5f) // Aspect ratio of 1.5:1
                .background(Color.Blue)
                .align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(x = 60.dp, y = 60.dp)
                .background(Color.Red)
                .align(Alignment.Center)
                .zIndex(2f) // Higher zIndex places this box on top of others
        )
    }
}

// Complex example demonstrating graphicsLayer, rotation, scaling, and zIndex
@Composable
fun ComplexGraphicsLayerExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // First Row with offset and clickable
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 20.dp, y = 10.dp)
                .clickable { /* Handle click */ }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Offset & Clickable Box",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Second Row with rotate, scale, and shadow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .rotate(45f)
                    .scale(1.2f)
                    .shadow(elevation = 8.dp, shape = RectangleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "Rotated & Scaled Box",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        // Third Row with graphicsLayer for more complex transformations
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .graphicsLayer(
                    rotationZ = 30f,
                    rotationX = 30f,
                    rotationY = 30f,
                    scaleX = 1.5f,
                    scaleY = 1.5f,
                    shadowElevation = 10f
                )

        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Magenta)
            )
            Spacer(modifier = Modifier.width(16.dp))
            BasicText(
                text = "GraphicsLayer Transformation",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Preview for simple and complex examples
@Preview(showBackground = true)
@Composable
fun PreviewSimpleAspectRatioExample() {
    SimpleAspectRatioExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexGraphicsLayerExample() {
    ComplexGraphicsLayerExample()
}
