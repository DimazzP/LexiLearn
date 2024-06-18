package com.example.lexilearn.data.screening.model

data class ScreeningAnswerResponse(
	val code: Int,
	val data: ScreeningAnswerResult,
	val message: String
)

data class ScreeningAnswerResult(
	val answerId: Int,
	val score: Int
)

