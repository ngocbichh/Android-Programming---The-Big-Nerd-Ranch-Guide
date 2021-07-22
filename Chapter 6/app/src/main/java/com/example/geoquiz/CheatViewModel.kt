package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel

class CheatViewModel : ViewModel() {

    var answerIsTrue = false
    var isAnswerShow = false

    val result by lazy {
        when {
            isAnswerShow -> Activity.RESULT_OK
            else -> Activity.RESULT_CANCELED
        }
    }

    val data by lazy {
        Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShow)
        }
    }

    val message = when {
        answerIsTrue -> R.string.true_button
        else -> R.string.false_button
    }
}