package com.faydenai.jetpack_compose_core.s03_layouts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

/**
 * Advanced layouts: LazyColumn
 *
 * LazyColumn is a scrollable list that only composes and lays out visible items,
 * making it efficient for rendering long lists. Unlike a regular Column,
 * LazyColumn renders items lazily to improve performance, especially with large datasets.
 *
 * Common features of LazyColumn:
 * - LazyColumn allows for scrolling, unlike regular columns.
 * - Lazy rendering ensures performance optimization for large lists.
 * - It uses the `items` function to display a list of elements.
 * - You can easily modify the content using `Modifier` for padding, margins, alignment, etc.
 *
 * When to use LazyColumn:
 * - Whenever you need to render a vertically scrolling list.
 * - It’s particularly useful for rendering lists of dynamic content, such as API data,
 *   or any other data set that could potentially grow large.
 *
 * Key Concepts:
 * - `items`: This is the core function used to iterate over a list of items.
 * - `LazyColumn`: The component that wraps the list and enables scrolling.
 * - `Modifier`: Used to add padding, margins, and alignment to the LazyColumn or its items.
 *
 * Simple Example:
 * Rendering a simple static list using LazyColumn.
 *
 **/

// Simple example
@Composable
fun SimpleLazyColumn() {

    val itemsList = (1..100).map { "Item $it" }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemsList) { item ->
            Text(
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/**
 * Preview Section:
 * These previews will help you see the layout without needing to run the entire app.
 **/

@Preview(showBackground = true)
@Composable
fun PreviewSimpleLazyColumn() {
    SimpleLazyColumn()
}

/**
 * Complex Example:
 * A more complex LazyColumn with dynamic content and modifiers, rendering a list
 * of objects. The items also include some visual customization and padding.
 *
 * In this example, we render a list of `Person` objects, which contain a name and age.
 * The items in the LazyColumn are customized using Modifier for padding and alignment.
 *
 **/

@Composable
fun ComplexLazyColumn1() {

    data class Person(val name: String, val age: Int)

    val itemsList = (1..100).map {
        Person(
            name = "Name $it",
            age = (18..60).random()
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemsList) { person ->
            Text(
                text = "${person.name}, Age: ${person.age}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComplexLazyColumn1() {
    ComplexLazyColumn1()
}

/**
 * Complex Example:
 *
 * This complex example demonstrates a more real-world usage of LazyColumn.
 *
 * Features:
 * 1. Render a list of `Person` objects.
 * 2. Include clickable items that react to user interaction.
 * 3. Alternate background colors for odd and even items.
 * 4. Display a separator between items.
 * 5. Highlight the clicked item with a special visual indicator.
 * 6. Apply padding, alignment, and styling modifiers.
 *
 * This scenario is typical in list views where users can interact with items.
 **/

@Composable
fun ComplexLazyColumn2() {

    data class Person(val name: String, val age: Int)

    val peopleList = listOf(
        Person("John", 30),
        Person("Emily", 25),
        Person("Michael", 35),
        Person("Sarah", 28),
        Person("David", 40)
    )

    // Mutable state to track which item was clicked
    var selectedPerson by remember { mutableStateOf<Person?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(peopleList) { person ->
            val isSelected = selectedPerson == person

            // Alternate background colors for odd and even items
            val backgroundColor = if (peopleList.indexOf(person) % 2 == 0) {
                Color.LightGray
            } else {
                Color.White
            }

            // Item layout with clickable interaction
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .clickable { selectedPerson = person } // Update selected person
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
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
                // Visual indicator for selected item
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            // Separator between items
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComplexLazyColumn2() {
    ComplexLazyColumn2()
}
