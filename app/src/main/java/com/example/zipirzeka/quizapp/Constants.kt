package com.example.zipirzeka.quizapp

import com.example.zipirzeka.R

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            R.drawable.dog,
            "Pig",
            "Horse",
            "Dog",
            "Donkey",
            3
        )
        questionsList.add(question1)

        val question2 = Question(
            1,
            R.drawable.cat,
            "Cow",
            "Cat",
            "Donkey",
            "Elephant",
            2
        )
        questionsList.add(question2)

        val question3 = Question(
            1,
            R.drawable.horse,
            "Horse",
            "Zebra",
            "Donkey",
            "Dog",
            1
        )
        questionsList.add(question3)

        val question4 = Question(
            1,
            R.drawable.elephant,
            "Wolf",
            "Pig",
            "Bear",
            ",Elephant",
            4
        )
        questionsList.add(question4)

        val question5 = Question(
            1,
            R.drawable.lion,
            "Lion",
            "Kangroo",
            "Crocodile",
            "Dragon",
            1
        )
        questionsList.add(question5)

        val question6 = Question(
            1,
            R.drawable.tiger,
            "Ant",
            "Bee",
            "Donkey",
            "Tiger",
            4
        )
        questionsList.add(question6)

        val question7 = Question(
            1,
            R.drawable.panda,
            "Bear",
            "Dog",
            "Panda",
            "ButterFly",
            3
        )
        questionsList.add(question7)

        val question8 = Question(
            1,
            R.drawable.rabbit,
            "Rabbit",
            "Horse",
            "Mouse",
            "Bird",
            1
        )
        questionsList.add(question8)

        val question9 = Question(
            1,
            R.drawable.bird,
            "Fish",
            "Donkey",
            "Cat",
            "Bird",
            4
        )
        questionsList.add(question9)

        val question10 = Question(
            1,
            R.drawable.fish,
            "Turtle",
            "Fish",
            "Frog",
            "Horse",
            2
        )
        questionsList.add(question10)
        return questionsList
    }
}