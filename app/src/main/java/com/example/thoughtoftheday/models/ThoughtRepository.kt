package com.example.thoughtoftheday.models

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class ThoughtRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("thoughts")

    fun getThoughts(
        onSuccess: (List<Thought>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val thoughts = result.documents.mapNotNull { it.toObject(Thought::class.java) }
                onSuccess(thoughts)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun addThought(
        thought: Thought,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(thought.id)
            .set(thought)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
    }

    fun updateThought(
        thought: Thought,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(thought.id)
            .update(
                mapOf(
                    "content" to thought.content,
                    "createdBy" to thought.createdBy,
                    "createdAt" to thought.createdAt,
                    "hexColor" to thought.hexColor,
                    "emoji" to (thought.emoji ?: "😌")
                )
            )
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
    }

    fun deleteThought(
        thoughtId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(thoughtId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
    }

}
