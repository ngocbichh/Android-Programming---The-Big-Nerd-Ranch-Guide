package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.quest_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var numberOfCorrectAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            disableAnswerButton()
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            disableAnswerButton()
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            if(currentIndex != 0){
                enableAnswerButton()
                updateQuestion()

                if(currentIndex == questionBank.size -1){
                    nextButton.setText("Result")
                }
            } else {
                nextButton.isEnabled = false
                displayScore()
            }
        }

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer (userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.corect_toast
        } else {
            R.string.incorrect_toast
        }
        if (userAnswer == correctAnswer) numberOfCorrectAnswer++
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun disableAnswerButton() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun enableAnswerButton(){
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    private fun displayScore() {
        var score: Int = 0
        score = 100 * numberOfCorrectAnswer / questionBank.size

        var messenger: String = "Your score is " + score
        Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show()
    }
}