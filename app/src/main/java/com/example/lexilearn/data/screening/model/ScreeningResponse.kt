package com.example.lexilearn.data.screening.model

import com.google.gson.annotations.SerializedName

data class ScreeningResponse(
	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<ScreeningQuestionItems>,

	@field:SerializedName("message")
	val message: String
)

data class ScreeningQuestionItems(
	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
