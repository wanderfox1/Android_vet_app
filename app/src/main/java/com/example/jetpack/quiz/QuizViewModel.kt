package com.example.jetpack.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
  var currentQuestionIndex by mutableStateOf(0)
  val userAnswers = mutableStateListOf<String>()
  val selectedOptions = mutableStateListOf<Set<String>>()
  var isAnswerChecked by mutableStateOf(false)
  var feedbackMessage by mutableStateOf("")
  var isCorrect by mutableStateOf(false)

  fun submitAnswer(answer: String? = null, selected: Set<String>? = null) {
    val current = quizQuestions[currentQuestionIndex]
    var correct = false

    when (current) {
      is TextQuestion, is ImageTextQuestion -> {
        val userAnswer = answer?.trim() ?: ""
        correct = current.correctAnswers.any { it.equals(userAnswer, ignoreCase = true) }
        userAnswers.add(userAnswer)
      }
      is CheckboxQuestion -> {
        val userSelection = selected ?: emptySet()
        val normalizedUser = userSelection.map { it.trim().lowercase() }.toSet()
        val normalizedCorrect = current.correctAnswers.map { it.trim().lowercase() }.toSet()
        correct = normalizedUser == normalizedCorrect
        selectedOptions.add(userSelection)
      }
    }

    isCorrect = correct
    feedbackMessage = if (correct) "✅ Правильно!" else "❌ Неправильно"
    currentQuestionIndex++
  }

  fun resetQuiz() {
    currentQuestionIndex = 0
    userAnswers.clear()
    selectedOptions.clear()
    feedbackMessage = ""
    isCorrect = false
  }

  val totalCorrect: Int
    get() {
      var count = 0
      for (i in quizQuestions.indices) {
        val q = quizQuestions[i]
        val correct = when (q) {
          is TextQuestion, is ImageTextQuestion -> {
            val ans = if (i < userAnswers.size) userAnswers[i] else ""
            q.correctAnswers.any { it.equals(ans, ignoreCase = true) }
          }
          is CheckboxQuestion -> {
            val sel = if (i < selectedOptions.size) selectedOptions[i] else emptySet()
            val normUser = sel.map { it.trim() }.toSet()
            val normCorr = q.correctAnswers.map { it.trim() }.toSet()
            normUser == normCorr
          }
        }
        if (correct) count++
      }
      return count
    }

  val isQuizFinished: Boolean
    get() = currentQuestionIndex >= quizQuestions.size
}