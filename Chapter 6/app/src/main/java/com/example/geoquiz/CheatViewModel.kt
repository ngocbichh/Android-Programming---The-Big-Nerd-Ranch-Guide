package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"

class CheatViewModel : ViewModel() {

    var answerIsTrue = false
    var isAnswerShown = false

    val result by lazy {
        when {
            isAnswerShown -> Activity.RESULT_OK
            else -> Activity.RESULT_CANCELED
        }
    }

    val data by lazy {
        Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
    }

    val message = when {
        answerIsTrue -> R.string.true_button
        else -> R.string.false_button
    }

}