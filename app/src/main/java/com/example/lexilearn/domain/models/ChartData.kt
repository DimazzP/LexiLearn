package com.example.lexilearn.domain.models


data class ChartData(val x: Float, val y: Float)

val chartData = listOf(
    ChartData(0f, 10f),
    ChartData(1f, 20f),
    ChartData(2f, 15f),
    ChartData(3f, 25f),
    ChartData(4f, 10f),
    ChartData(5f, 30f),
    ChartData(6f, 20f)
)

val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")