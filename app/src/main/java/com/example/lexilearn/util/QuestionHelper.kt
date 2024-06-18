package com.example.lexilearn.util

fun String.parseToQuizQuestion(): List<String> {
    val delimiter = "{{answer}}"

    val parts = this.split(delimiter)
    val result = mutableListOf<String>()

    for (i in parts.indices) {
        result.add(parts[i])
        if (i < parts.size - 1) {
            result.add("?")
        }
    }

    if (result.last().isEmpty()) {
        result.remove(result.last())
    }

    return result
}