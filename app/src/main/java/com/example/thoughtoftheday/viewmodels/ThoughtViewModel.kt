package com.example.thoughtoftheday.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.thoughtoftheday.models.Thought
import com.example.thoughtoftheday.models.ThoughtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ThoughtViewModel : ViewModel() {

    private val repo = ThoughtRepository()
    private val _thoughts = MutableStateFlow<List<Thought>>(emptyList())
    val thoughts: StateFlow<List<Thought>> = _thoughts

    private val _dialogThought = MutableStateFlow<Thought?>(null)
    val dialogThought: StateFlow<Thought?> = _dialogThought

    init {
        fetchThoughts()
    }

    fun fetchThoughts(query: String = "") {
        repo.getThoughts(
            onSuccess = { allThoughts ->
                _thoughts.value = if (query.isBlank()) allThoughts
                else allThoughts.filter { it.content.contains(query, ignoreCase = true) }
            },
            onError = { error ->
                Log.e("ThoughtVM", "Fetch failed: ${error.message}", error)
            }
        )
    }

    fun addThought(thought: Thought) {
        val newThought = thought.copy(
            emoji = thought.emoji ?: Thought.emojiFromColor(thought.hexColor)
        )

        repo.addThought(
            newThought,
            onSuccess = { fetchThoughts() },
            onError = { error ->
                Log.e("ThoughtVM", "Add failed: ${error.message}", error)
            }
        )
    }

    fun updateThought(thought: Thought) {
        val updatedThought = thought.copy(
            emoji = thought.emoji ?: Thought.emojiFromColor(thought.hexColor)
        )

        repo.updateThought(
            updatedThought,
            onSuccess = { fetchThoughts() },
            onError = { error ->
                Log.e("ThoughtVM", "Update failed: ${error.message}", error)
            }
        )
    }


    fun deleteThought(id: String) {
        repo.deleteThought(
            id,
            onSuccess = { fetchThoughts() },
            onError = { error ->
                Log.e("ThoughtVM", "Delete failed: ${error.message}", error)
            }
        )
    }



    fun openAddDialog() {
        _dialogThought.value = Thought(id = "")
    }

    fun openEditDialog(thought: Thought) {
        _dialogThought.value = thought
    }

    fun closeDialog() {
        _dialogThought.value = null
    }
}
