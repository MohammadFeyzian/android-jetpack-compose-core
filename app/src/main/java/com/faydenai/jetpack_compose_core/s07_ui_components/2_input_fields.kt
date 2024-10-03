package com.faydenai.jetpack_compose_core.s07_ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * This Kotlin file demonstrates handling input fields in Jetpack Compose,
 * covering components such as:
 *
 * - TextField: Standard text input, including password fields.
 * - Button: Clickable button to trigger actions.
 * - Checkbox: A checkable box that represents boolean states.
 * - Slider: A control to select a value from a continuous range.
 * - Switch: A toggle for on/off states.
 * - RadioButton: A circular button for single selection from a group.
 * - BasicTextField: A more customizable version of TextField.
 *
 *  DropdownMenuComponent demonstrates a basic dropdown menu in Jetpack Compose.
 *  It displays a button that, when clicked, shows a list of options inside a `DropdownMenu`.
 *  The user can select an option, and the selected option is displayed on the button.
 *
 *  - Components used: Button, DropdownMenu, DropdownMenuItem.
 *  - The state `expanded` controls the visibility of the menu.
 *  - The selected option is stored in `selectedOption`.
 *
 *  ExposedDropdownMenuComponent demonstrates a Material Design dropdown menu using `ExposedDropdownMenuBox`.
 *  It integrates a `TextField` with a dropdown for a more polished user experience.
 *  The dropdown appears when the user interacts with the `TextField`, and the selected option is shown in the `TextField`.
 *
 *  - Components used: ExposedDropdownMenuBox, ExposedDropdownMenu, TextField, DropdownMenuItem.
 *  - The `expanded` state controls the visibility of the dropdown.
 *  - The selected option is stored in `selectedOption`, and the dropdown follows Material design conventions.
 *
 * Each component is demonstrated with basic usage, state handling, and integration into a structured UI.
 * The file is organized into composable functions to keep the code modular and clean.
 */

@Composable
fun InputFieldsExample() {
    // Column to organize input components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // DropdownMenuComponent
        DropdownMenuComponent()

        // ExposedDropdownMenuComponent
        ExposedDropdownMenuComponent()

        // Text Input Section
        TextFieldComponent()

        // Password Input Section
        PasswordFieldComponent()

        // Button Section
        ButtonComponent()

        // Checkbox Section
        CheckboxComponent()

        // RadioButton Section
        RadioButtonComponent()

        // Switch Section
        SwitchComponent()

        // Slider Section
        SliderComponent()

        // BasicTextField Section (customizable text input)
        BasicTextFieldComponent()
    }
}

@Composable
fun TextFieldComponent() {
    var text by remember { mutableStateOf("") }
    Column {
        Text(text = "Enter your name:", fontSize = 16.sp)
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PasswordFieldComponent() {
    var password by remember { mutableStateOf("") }
    Column {
        Text(text = "Enter your password:", fontSize = 16.sp)
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ButtonComponent() {
    var clicked by remember { mutableStateOf(false) }

    Button(
        onClick = { clicked = !clicked },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = if (clicked) "Clicked!" else "Click Me")
    }
}

@Composable
fun CheckboxComponent() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (isChecked) "Checked" else "Unchecked")
    }
}

@Composable
fun RadioButtonComponent() {
    var selectedOption by remember { mutableStateOf("Option 1") }
    Column {
        Text(text = "Select an option:", fontSize = 16.sp)

        Row {
            RadioButton(
                selected = selectedOption == "Option 1",
                onClick = { selectedOption = "Option 1" }
            )
            Text(text = "Option 1")
        }

        Row {
            RadioButton(
                selected = selectedOption == "Option 2",
                onClick = { selectedOption = "Option 2" }
            )
            Text(text = "Option 2")
        }
    }
}

@Composable
fun SwitchComponent() {
    var isSwitchedOn by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isSwitchedOn,
            onCheckedChange = { isSwitchedOn = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (isSwitchedOn) "Switch is ON" else "Switch is OFF")
    }
}

@Composable
fun SliderComponent() {
    var sliderValue by remember { mutableFloatStateOf(0f) }

    Column {
        Text(text = "Adjust the value: ${sliderValue.toInt()}", fontSize = 16.sp)
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BasicTextFieldComponent() {
    var basicText by remember { mutableStateOf(TextFieldValue("Enter text here")) }

    Column {
        Text(text = "BasicTextField:", fontSize = 16.sp)
        BasicTextField(
            value = basicText,
            onValueChange = { basicText = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        )
    }
}

@Composable
fun DropdownMenuComponent() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select an option") }

    // Options to be displayed in the dropdown
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Button that triggers the dropdown menu
        Button(onClick = { expanded = !expanded }) {
            Text(text = selectedOption)
        }

        // DropdownMenu that displays the options
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuComponent() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    println("----> expanded: $expanded")

    // ExposedDropdownMenuBox provides the Material Design dropdown behavior
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        // TextField that shows the selected option and triggers the dropdown
        TextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text("Choose an option") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.SecondaryEditable),
            readOnly = true // Makes it behave like a dropdown
        )

        // The dropdown menu itself
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewInputFieldsExample() {
    InputFieldsExample()
}
