package com.example.zipirzeka.level2

import com.example.zipirzeka.R

object Constants2 {
    fun getQuestions(): ArrayList<Question2> {
        val questionsList = ArrayList<Question2>()

        val question1 = Question2(
            1,
            R.drawable.dog,
            "Dog",
        )
        questionsList.add(question1)

        val question2 = Question2(
            1,
            R.drawable.cat,
        "Cat"
        )
        questionsList.add(question2)

        val question3 = Question2(
            1,
            R.drawable.horse,
            "Horse",
        )
        questionsList.add(question3)

        val question4 = Question2(
            1,
            R.drawable.elephant,
            ",Elephant",
        )
        questionsList.add(question4)

        val question5 = Question2(
            1,
            R.drawable.lion,
            "Lion",
        )
        questionsList.add(question5)

        val question6 = Question2(
            1,
            R.drawable.tiger,
            "Tiger",
        )
        questionsList.add(question6)

        val question7 = Question2(
            1,
            R.drawable.panda,
            "Panda",
        )
        questionsList.add(question7)

        val question8 = Question2(
            1,
            R.drawable.rabbit,
            "Rabbit"
        )
        questionsList.add(question8)

        val question9 = Question2(
            1,
            R.drawable.bird,
            "Bird",
        )
        questionsList.add(question9)

        val question10 = Question2(
            1,
            R.drawable.fish,
            "Fish",

        )
        questionsList.add(question10)
        return questionsList
    }
}