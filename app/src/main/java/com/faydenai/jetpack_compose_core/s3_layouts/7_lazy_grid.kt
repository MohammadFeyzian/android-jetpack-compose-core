package com.faydenai.jetpack_compose_core.s3_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Advanced Layouts: Grids
 *
 * In Jetpack Compose, there isn't a direct `Grid` component like in the traditional View system.
 * However, with Jetpack Compose's `LazyVerticalGrid`, we can implement grid-like layouts.
 *
 * LazyVerticalGrid:
 * - A grid layout where the items are lazily composed, similar to LazyColumn or LazyRow.
 * - It allows items to be displayed in rows and columns.
 * - You can configure the number of columns via `GridCells.Fixed` or have responsive grids with `GridCells.Adaptive`.
 * - This is a powerful layout structure for creating grids like photo galleries, product displays, etc.
 *
 * Use Cases:
 * - Product catalogs, image grids, and photo galleries.
 * - Any content that requires multiple items to be displayed in a grid format.
 *
 * `LazyLazyHorizontalGrid` has the same behaviour as the `LazyVerticalGrid`
 *
 * Simple Example:
 * Demonstrates a basic `LazyVerticalGrid` with a fixed number of columns and static data.
 **/

@Composable
fun SimpleVerticalGridLayout() {

    val itemsList = List(20) { "Item $it" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsList) { item ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f), // Ensures a square item
                contentAlignment = Alignment.Center
            ) {
                Text(text = item, color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSimpleVerticalGridLayout() {
    SimpleVerticalGridLayout()
}

/**
 * Complex Example:
 * A more advanced grid layout example with dynamic content, selectable items, and styling.
 *
 * Features:
 * 1. Uses `LazyVerticalGrid` to display items in a 3-column grid.
 * 2. Items are clickable, and selected items are highlighted.
 * 3. Includes padding, margins, and conditional styling based on item state (selected/unselected).
 **/

@Composable
fun ComplexVerticalGridLayout() {

    data class Product(val name: String, val price: String)

    val productsList = listOf(
        Product("Laptop", "$1200"),
        Product("Smartphone", "$800"),
        Product("Headphones", "$150"),
        Product("Smartwatch", "$300"),
        Product("Tablet", "$400"),
        Product("Camera", "$900"),
        Product("Shoes", "$100"),
        Product("Backpack", "$60")
    )

    // State to track selected items
    val selectedProducts = remember { mutableStateListOf<Product>() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 columns for a more compact grid
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productsList) { product ->
            val isSelected = product in selectedProducts

            Box(
                modifier = Modifier
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.tertiaryContainer
                        else MaterialTheme.colorScheme.primaryContainer
                    )
                    .clickable {
                        if (isSelected) selectedProducts.remove(product)
                        else selectedProducts.add(product)
                    }
                    .padding(16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f), // Ensures a square item
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                    Text(
                        text = product.price,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComplexGridLayout() {
    ComplexVerticalGridLayout()
}


@Composable
fun SimpleHorizontalGridLayout() {

    val itemsList = List(50) { "Item $it" }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(6), // 2 rows
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsList) { item ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f), // Ensures a square item
                contentAlignment = Alignment.Center
            ) {
                Text(text = item, color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSimpleHorizontalGridLayout() {
    SimpleHorizontalGridLayout()
}