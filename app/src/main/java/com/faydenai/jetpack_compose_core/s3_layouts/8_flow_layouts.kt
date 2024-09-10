package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Advanced Layouts: FlowRow
 *
 * FlowRow is a layout that allows items to flow into new rows when there isn't enough
 * space in a single line. This is useful for layouts like tag systems, chip layouts, or
 * any content that needs dynamic row wrapping.
 *
 * Key Concepts:
 * - `FlowRow`: Arranges items horizontally and wraps them to a new line when space runs out.
 * - `Modifier`: Use modifiers for customizing padding, spacing between items, background colors, etc.
 *
 * Use Cases:
 * - Tag systems, chip groups, dynamically sized items that need to wrap into multiple rows.
 *
 * FlowColumn has the same behaviour as the FlowRow
 *
 * Simple Example:
 * A basic `FlowRow` layout with simple items that wrap when necessary.
 **/

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimpleFlowRow() {
    val tags = listOf(
        "Compose",
        "Jetpack",
        "Android",
        "UI",
        "Layouts",
        "Material3",
        "FlowRow",
        "Accompanist"
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = tag, color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSimpleFlowRow() {
    SimpleFlowRow()
}

/**
 * Complex Example:
 * A more advanced `FlowRow` layout where items are clickable, and selected items are highlighted.
 * This example simulates a selectable tag or chip group layout.
 *
 * Features:
 * 1. Items flow into multiple rows when the screen width is exceeded.
 * 2. Items are clickable, and selected items are highlighted with a different background color.
 * 3. Each item has dynamic size and wraps neatly into the next row.
 **/

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComplexFlowRow() {
    val topics = listOf(
        "Technology", "Science", "Health", "Education", "Space", "Environment", "Politics", "Art",
        "Music", "Travel", "Food", "Culture", "Sports", "Economics", "Design", "History"
    )

    // Mutable state to track selected topics
    val selectedTopics = remember { mutableStateListOf<String>() }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        topics.forEach { topic ->
            val isSelected = topic in selectedTopics

            Box(
                modifier = Modifier
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        if (isSelected) selectedTopics.remove(topic) else selectedTopics.add(topic)
                    }
            ) {
                Text(
                    text = topic,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexFlowRow() {
    ComplexFlowRow()
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimpleFlowColumn() {

    val tags = List(50) { "Item ${(1..3000).random()}" }

    FlowColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        tags.forEach { tag ->
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = tag)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleFlowColumn() {
    SimpleFlowColumn()
}