package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.qeoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.qeoquiz.answer_is_true"
private const val TAG ="CheatActivity"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerBUtton: Button
    private var answerIsTrue = false

    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProviders.of(this).get(CheatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        cheatViewModel.answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerBUtton = findViewById(R.id.show_answer_button)

        if(cheatViewModel.isAnswerShown){
            answerTextView.setText(cheatViewModel.message)
            setAnswerShowResult(cheatViewModel.isAnswerShown)
        }

        showAnswerBUtton.setOnClickListener {
            val answerText = cheatViewModel.message
            answerTextView.setText(answerText)
            cheatViewModel.isAnswerShown = true
            setAnswerShowResult(cheatViewModel.isAnswerShown)
        }
    }

    private fun setAnswerShowResult(isAnswerShown: Boolean) {
        setResult(cheatViewModel.result, cheatViewModel.data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent (packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}