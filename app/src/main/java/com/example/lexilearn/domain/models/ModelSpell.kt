package com.example.lexilearn.domain.models

data class ModelSpell(
    val id: Int,
    val type: Boolean,
    var data: String,
    var emp: Int? = null,
    var hasContent: Boolean = false,
    var showCard: Boolean = false
)
