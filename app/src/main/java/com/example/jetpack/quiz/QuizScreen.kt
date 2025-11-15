package com.example.jetpack.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack.quiz.QuizViewModel

@Composable
fun QuizScreen(
  viewModel: QuizViewModel = viewModel(),
  onBackToMain: () -> Unit
) {
  if (viewModel.isQuizFinished) {
    QuizResultScreen(
      correctCount = viewModel.totalCorrect,
      totalCount = quizQuestions.size,
      onBackToMain = onBackToMain
    )
  } else {
    val currentQuestion = quizQuestions[viewModel.currentQuestionIndex]
    QuestionScreen(
      question = currentQuestion,
      questionNumber = viewModel.currentQuestionIndex + 1,
      totalQuestions = quizQuestions.size,
      onAnswerSubmit = { answer, selected ->
        viewModel.submitAnswer(answer, selected)
      },
      feedbackMessage = viewModel.feedbackMessage,
      isCorrect = viewModel.isCorrect
    )
  }
}

@Composable
fun QuestionScreen(
  question: Question,
  questionNumber: Int,
  totalQuestions: Int,
  onAnswerSubmit: (String?, Set<String>?) -> Unit,
  feedbackMessage: String,
  isCorrect: Boolean
) {
  var textAnswer by remember { mutableStateOf("") }
  var selectedOptions by remember { mutableStateOf(setOf<String>()) }

  LaunchedEffect(question.id) {
    textAnswer = ""
    selectedOptions = emptySet()
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Top
  ) {
    Text(
      text = "Вопрос $questionNumber из $totalQuestions",
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    Text(
      text = question.text,
      style = MaterialTheme.typography.bodyLarge,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    when (question) {
      is CheckboxQuestion -> {
        Column(modifier = Modifier.selectableGroup()) {
          question.options.forEach { option ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .fillMaxWidth()
                .selectable(
                  selected = selectedOptions.contains(option),
                  onClick = {
                    selectedOptions = if (selectedOptions.contains(option)) {
                      selectedOptions - option
                    } else {
                      selectedOptions + option
                    }
                  },
                  role = Role.Checkbox
                )
                .padding(vertical = 4.dp)
            ) {
              Checkbox(
                checked = selectedOptions.contains(option),
                onCheckedChange = null
              )
              Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
          }
        }
      }

      is TextQuestion -> {
        OutlinedTextField(
          value = textAnswer,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          onValueChange = { textAnswer = it },
          label = { Text("Ваш ответ") },
          modifier = Modifier.fillMaxWidth()
        )
      }

      is ImageTextQuestion -> {
        Image(
          painter = painterResource(id = question.imageRes),
          contentDescription = null,
          modifier = Modifier
            .size(200.dp)
            .align(Alignment.CenterHorizontally)
            .padding(bottom = 16.dp)
        )
        OutlinedTextField(
          value = textAnswer,
          onValueChange = { textAnswer = it },
          label = { Text("Ваш ответ") },
          modifier = Modifier.fillMaxWidth()
        )
      }
    }

    Spacer(modifier = Modifier.weight(1f))

    if (feedbackMessage.isNotEmpty()) {
      Text(
        text = feedbackMessage,
        color = if (isCorrect) Color.Green else Color.Red,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }

    Button(
      onClick = {
        when (question) {
          is CheckboxQuestion -> {
            onAnswerSubmit(null, selectedOptions)
          }
          else -> {
            onAnswerSubmit(textAnswer, null)
          }
        }
      },
      enabled = when (question) {
        is CheckboxQuestion -> selectedOptions.isNotEmpty()
        else -> textAnswer.isNotBlank()
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Далее")
    }
  }
}

@Composable
fun QuizResultScreen(
  correctCount: Int,
  totalCount: Int,
  onBackToMain: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = "Результаты квиза",
      style = MaterialTheme.typography.headlineMedium,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    Text(
      text = "Правильных ответов: $correctCount из $totalCount",
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    Button(
      onClick = onBackToMain,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("На главную")
    }
  }
}