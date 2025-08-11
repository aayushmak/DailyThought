@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.thoughtoftheday.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.thoughtoftheday.models.Thought
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.thoughtoftheday.utils.ColorUtil
import java.util.UUID

@Composable
fun ThoughtFormDialog(
    initialThought: Thought? = null,
    onDismiss: () -> Unit,
    onSave: (Thought) -> Unit
) {
    var content by remember { mutableStateOf(initialThought?.content ?: "") }
    var createdBy by remember { mutableStateOf(initialThought?.createdBy ?: "") }
    var hexColor by remember { mutableStateOf(initialThought?.hexColor ?: "#FFFFFF") }
    var emoji by remember { mutableStateOf(initialThought?.emoji ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                val finalEmoji = emoji.ifBlank { Thought.emojiFromColor(hexColor) }
                val newThought = Thought(
                    id = initialThought?.id ?: UUID.randomUUID().toString(),
                    content = content.trim(),
                    createdBy = createdBy.trim(),
                    createdAt = initialThought?.createdAt ?: System.currentTimeMillis(),
                    hexColor = hexColor,
                    emoji = finalEmoji
                )
                onSave(newThought)
                // ❌ Do NOT call onDismiss() here – let the parent handle it after save
            }) {
                Text(if (initialThought == null) "Add" else "Update")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text(if (initialThought == null) "New Thought" else "Edit Thought") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Thought") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )

                OutlinedTextField(
                    value = createdBy,
                    onValueChange = { createdBy = it },
                    label = { Text("Created By") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = hexColor,
                    onValueChange = { hexColor = it.uppercase() },
                    label = { Text("Hex Color (#RRGGBB)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(ColorUtil.fromHexOrDefault(hexColor))
                )

                OutlinedTextField(
                    value = emoji,
                    onValueChange = { emoji = it },
                    label = { Text("Emoji (optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
