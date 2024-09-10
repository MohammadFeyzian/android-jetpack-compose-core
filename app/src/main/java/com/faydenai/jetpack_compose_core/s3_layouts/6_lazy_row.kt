package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Advanced Layouts: LazyRow
 *
 * LazyRow:
 * - LazyRow is used to display a horizontal list of items. It behaves similarly to LazyColumn but
 *   arranges items horizontally instead of vertically.
 * - Useful for implementing horizontal carousels, image sliders, or scrolling tabs.
 * - Like LazyColumn, LazyRow renders only the visible items, optimizing performance for large lists.
 *
 * Key Concepts:
 * - `LazyRow`: A horizontally scrolling list that only renders visible items lazily.
 * - `items`: Used to iterate over a list of data and display individual items in the row.
 * - `Modifier`: Used for customizing padding, alignment, size, and other properties of items in the row.
 *
 * Use Cases:
 * - Horizontal carousels (e.g., a movie list, product sliders, etc.).
 * - Horizontal scrolling tabs.
 * - Image galleries.
 *
 * Simple Example:
 * Rendering a list of strings horizontally using LazyRow.
 **/

@Composable
fun SimpleLazyRow() {

    val itemsList = (1..10).map { "Item $it" }

    // LazyRow (Horizontal List)
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(itemsList) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSimpleLazyRow() {
    SimpleLazyRow()
}

/**
 * Complex Example:
 * A more complex example demonstrating dynamic content, interaction, and customization in LazyRow.
 * This example includes clickable items, conditional background colors, and horizontal scrolling.
 *
 * Features:
 * 1. Display a list of `Person` objects.
 * 2. Alternate background colors for even and odd items.
 * 3. Clickable items that show a selected state.
 * 4. Include custom padding, margins, and alignment.
 * 5. Horizontal scrolling.
 **/

@Composable
fun ComplexLazyRow() {

    data class Person(val name: String, val age: Int)

    val peopleList = listOf(
        Person("John", 30),
        Person("Emily", 25),
        Person("Michael", 35),
        Person("Sarah", 28),
        Person("David", 40)
    )

    // Mutable state to track the selected person
    var selectedPerson by remember { mutableStateOf<Person?>(null) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(peopleList) { person ->
            val isSelected = selectedPerson == person

            // Alternate background colors for even and odd items
            val backgroundColor = if (peopleList.indexOf(person) % 2 == 0) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.tertiaryContainer
            }

            // Clickable item layout
            Column(
                modifier = Modifier
                    .clickable { selectedPerson = person }
                    .padding(8.dp)
                    .background(backgroundColor)
                    .padding(16.dp)
            ) {

                Text(
                    text = person.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    ),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                )

                Text(
                    text = "Age: ${person.age}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComplexLazyRow() {
    ComplexLazyRow()
}
