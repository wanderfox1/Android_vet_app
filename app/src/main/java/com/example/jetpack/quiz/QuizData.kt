package com.example.jetpack.quiz

import androidx.annotation.DrawableRes
import com.example.jetpack.R

sealed class Question(
  val id: Int,
  val text: String,
  val correctAnswers: List<String>
)

class CheckboxQuestion(
  id: Int,
  text: String,
  val options: List<String>,
  correctAnswers: List<String>
) : Question(id, text, correctAnswers) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is CheckboxQuestion) return false
    return id == other.id &&
            text == other.text &&
            options == other.options &&
            correctAnswers == other.correctAnswers
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + text.hashCode()
    result = 31 * result + options.hashCode()
    result = 31 * result + correctAnswers.hashCode()
    return result
  }

  override fun toString(): String {
    return "CheckboxQuestion(id=$id, text='$text', options=$options, correctAnswers=$correctAnswers)"
  }
}

class TextQuestion(
  id: Int,
  text: String,
  correctAnswers: List<String>
) : Question(id, text, correctAnswers) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is TextQuestion) return false
    return id == other.id &&
            text == other.text &&
            correctAnswers == other.correctAnswers
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + text.hashCode()
    result = 31 * result + correctAnswers.hashCode()
    return result
  }

  override fun toString(): String {
    return "TextQuestion(id=$id, text='$text', correctAnswers=$correctAnswers)"
  }
}

class ImageTextQuestion(
  id: Int,
  text: String,
  @DrawableRes val imageRes: Int,
  correctAnswers: List<String>
) : Question(id, text, correctAnswers) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is ImageTextQuestion) return false
    return id == other.id &&
            text == other.text &&
            imageRes == other.imageRes &&
            correctAnswers == other.correctAnswers
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + text.hashCode()
    result = 31 * result + imageRes
    result = 31 * result + correctAnswers.hashCode()
    return result
  }

  override fun toString(): String {
    return "ImageTextQuestion(id=$id, text='$text', imageRes=$imageRes, correctAnswers=$correctAnswers)"
  }
}

val quizQuestions = listOf(
  CheckboxQuestion(
    id = 1,
    text = "Какие из этих животных являются млекопитающими?",
    options = listOf("Кошка", "Курица", "Собака", "Змея"),
    correctAnswers = listOf("Кошка", "Собака")
  ),
  TextQuestion(
    id = 2,
    text = "Какой самый большой орган у человека?",
    correctAnswers = listOf("Кожа")
  ),
  CheckboxQuestion(
    id = 3,
    text = "Какие из этих фруктов растут на деревьях?",
    options = listOf("Яблоко", "Клубника", "Банан", "Арбуз"),
    correctAnswers = listOf("Яблоко", "Банан")
  ),
  TextQuestion(
    id = 4,
    text = "Какой химический символ у золота?",
    correctAnswers = listOf("Au")
  ),
  ImageTextQuestion(
    id = 5,
    text = "Как называется этот фрукт?",
    imageRes = R.drawable.apple,
    correctAnswers = listOf("Яблоко", "Apple")
  ),
  ImageTextQuestion(
    id = 6,
    text = "Как называется эта собака?",
    imageRes = R.drawable.dog,
    correctAnswers = listOf("Собака", "Dog", "Пёс")
  )
)