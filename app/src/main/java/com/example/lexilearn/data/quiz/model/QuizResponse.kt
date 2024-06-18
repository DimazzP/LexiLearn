package com.example.lexilearn.data.quiz.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(
	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: QuizData,

	@field:SerializedName("message")
	val message: String
)

data class QuizData(
	@field:SerializedName("answerList")
	val answerList: List<String>,

	@field:SerializedName("answerKey")
	val answerKey: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("answerType")
	val answerType: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
