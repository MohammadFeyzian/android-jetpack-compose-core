package com.faydenai.jetpack_compose_core.s07_ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faydenai.jetpack_compose_review.R

/**
 * This Kotlin file demonstrates the use of various Jetpack Compose UI components in a single cohesive example.
 * It includes the following components:
 *
 * - Text, BasicText: Display text with different styling options.
 * - Image, AsyncImage: Display images from resources and load images asynchronously from a URL.
 * - Icon, IconButton: Display material icons and handle click interactions.
 * - Card: A container with elevation and rounded corners for grouping content.
 * - Scaffold: A layout structure with slots for TopAppBar, BottomNavigation, FAB, and main content.
 * - TopAppBar: A toolbar at the top with a title and navigation/actions.
 * - NavigationBar, NavigationBarItem: A bottom navigation bar with multiple navigation items.
 * - LazyColumn: Efficient scrolling list for large sets of data.
 * - TabRow: A tab layout for switching between different sections of content.
 * - Surface, Box: Containers for structuring and aligning UI elements.
 * - FloatingActionButton: A button that floats above content for primary actions.
 * - Divider, Spacer: Simple components to create spacing and separation in the layout.
 *
 * This file is structured into multiple composable functions to ensure readability and modularity.
 * Each component is demonstrated with its basic usage and how it fits within a complex layout.
 */

@Composable
fun BaseComponentExample() {

    var currentTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = { FAB() },
        bottomBar = {
            BottomNavigationBar(
                currentTabIndex
            ) { tabIndex -> currentTabIndex = tabIndex }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ContentSection(currentTabIndex = currentTabIndex)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = { Text("Jetpack Compose Example") },
        navigationIcon = {
            IconButton(onClick = { /* Handle click */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle settings */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )
}

@Composable
private fun FAB() {
    FloatingActionButton(onClick = { /* Handle FAB click */ }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Composable
private fun BottomNavigationBar(
    currentTabIndex: Int,
    onTabChanged: (index: Int) -> Unit
) {

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = currentTabIndex == 0,
            onClick = { onTabChanged(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            selected = currentTabIndex == 1,
            onClick = { onTabChanged(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            selected = currentTabIndex == 2,
            onClick = { onTabChanged(2) }
        )
    }
}

@Composable
private fun ContentSection(
    currentTabIndex: Int
) {
    when (currentTabIndex){
        0 -> FirstTabContent()
        1 -> OtherTabsContent("Liked")
        2 -> OtherTabsContent( "Settings")
    }

}

@Composable
private fun FirstTabContent(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { TextComponent() }
        item { BasicTextComponent() }
        item { ImageComponent() }
        item { AsyncImageComponent() }
        item { CardComponent() }
        item { TabRowComponent() }
        item { HorizontalDivider() }
        item { BoxComponent() }
    }
}

@Composable
private fun OtherTabsContent(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
private fun TextComponent() {
    Text(
        text = "This is a Text Component",
        style = TextStyle(fontSize = 20.sp, color = Color.Black)
    )
}

@Composable
private fun BasicTextComponent() {
    BasicText(
        text = "This is a BasicText Component",
        style = TextStyle(fontSize = 18.sp, color = Color.Gray)
    )
}

@Composable
private fun ImageComponent() {
    Image(
        painter = painterResource(id = R.drawable.sample_image), // Replace with your image resource
        contentDescription = "Sample Image",
        modifier = Modifier.size(150.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun AsyncImageComponent() {
    AsyncImage(
        model = "https://picsum.photos/200/300", // Replace with your image URL
        contentDescription = "Async Loaded Image",
        modifier = Modifier.size(150.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun CardComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is a Card Component")
            Spacer(modifier = Modifier.height(8.dp))
            Icon(Icons.Default.ThumbUp, contentDescription = "Thumbs Up", tint = Color.Blue)
        }
    }
}

@Composable
private fun TabRowComponent() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column {
        // TabRow for switching between tabs
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text(text = "Tab 1", modifier = Modifier.padding(16.dp))
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text(text = "Tab 2", modifier = Modifier.padding(16.dp))
            }
        }

        // Content based on selected tab
        when (selectedTab) {
            0 -> Tab1Content() // Show content for Tab 1
            1 -> Tab2Content() // Show content for Tab 2
        }
    }
}

@Composable
private fun Tab1Content() {

    // Simple Surface component
    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 32.dp)
            .fillMaxWidth(),
        color = Color.LightGray,
    ) {
        Text(
            text = "This is content for Tab 1",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = TextStyle(fontSize = 18.sp, color = Color.Black)
        )
    }

}

@Composable
private fun Tab2Content() {
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        Text(
            text = "This is content for Tab 2",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = TextStyle(fontSize = 18.sp, color = Color.Blue)
        )
    }
}


@Composable
private fun HorizontalDivider() {
    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
}

@Composable
private fun BoxComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This is a Box Component")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBaseComponentExample() {
    BaseComponentExample()
}
