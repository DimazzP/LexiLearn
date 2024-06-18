package com.example.lexilearn.data.quiz.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(
	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<QuizData>,

	@field:SerializedName("message")
	val message: String
)

data class QuizData(
	@field:SerializedName("choiceType")
	val choiceType: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("choices")
	val choices: List<String>
)
