package com.example.zipirzeka.quizapp

data class Question(
    val id: Int,
    val question: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctOption: Int

)

