package com.example.jetpack

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack.quiz.QuizScreen
import com.example.jetpack.quiz.QuizViewModel
import com.example.jetpack.store.StoreActivity
import com.example.jetpack.ui.theme.Black
import com.example.jetpack.ui.theme.BodyLarge
import com.example.jetpack.ui.theme.BrandLight
import com.example.jetpack.ui.theme.BrandPrimary
import com.example.jetpack.ui.theme.BrandSecondary500
import com.example.jetpack.ui.theme.ButtonLarge
import com.example.jetpack.ui.theme.LabelLarge
import com.example.jetpack.ui.theme.TitleLarge
import com.example.jetpack.ui.theme.White

@Composable
fun PetProfileContent(modifier: Modifier = Modifier) {
  var name by remember { mutableStateOf("") }
  var age by remember { mutableStateOf("") }
  var breed by remember { mutableStateOf("") }
  var weight by remember { mutableStateOf("") }
  var vaccinations by remember { mutableStateOf("") }
  var food by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }

  val context = LocalContext.current

  var showQuiz by remember { mutableStateOf(false) }

  if (showQuiz) {
    Text(
      text = "Quiz Screen - Coming Soon",
      style = TitleLarge,
      color = BrandPrimary,
      modifier = Modifier.fillMaxSize().wrapContentSize()
    )
    return
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Image(
      painter = painterResource(id = R.drawable.bg),
      contentDescription = "Фоновое изображение",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )

    Column(
      modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
    ) {
      Text(
        text = "Создание профиля питомца",
        style = TitleLarge,
        color = BrandPrimary,
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .padding(bottom = 24.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
      )

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        Image(
          painter = painterResource(id = R.drawable.pet_secondary),
          contentDescription = "Основное фото питомца",
          modifier = Modifier.size(80.dp)
        )

        Image(
          painter = painterResource(id = R.drawable.pet_secondary),
          contentDescription = "Дополнительное фото питомца",
          modifier = Modifier.size(80.dp)
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Имя питомца",
        style = LabelLarge,
        color = Black,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      TextField(
        value = name,
        onValueChange = { name = it },
        placeholder = { Text("Томас", style = BodyLarge) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        )
      )

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Возраст",
        style = LabelLarge,
        color = Black,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      TextField(
        value = age,
        onValueChange = { age = it },
        placeholder = { Text("3", style = BodyLarge) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        )
      )

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Порода",
        style = LabelLarge,
        color = Black,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      TextField(
        value = breed,
        onValueChange = { breed = it },
        placeholder = { Text("Вислоухий", style = BodyLarge) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        )
      )

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Вес",
        style = LabelLarge,
        color = Black,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      TextField(
        value = weight,
        onValueChange = { weight = it },
        placeholder = { Text("7 (kilos)", style = BodyLarge) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        )
      )

      Spacer(modifier = Modifier.height(24.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Прививки",
            style = LabelLarge,
            color = Black,
            modifier = Modifier.padding(bottom = 8.dp)
          )
          TextField(
            value = vaccinations,
            onValueChange = { vaccinations = it },
            placeholder = { Text("Статус прививок", style = BodyLarge) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
              focusedIndicatorColor = BrandPrimary,
              unfocusedIndicatorColor = BrandPrimary
            )
          )
        }

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Любимая еда",
            style = LabelLarge,
            color = Black,
            modifier = Modifier.padding(bottom = 8.dp)
          )
          TextField(
            value = food,
            onValueChange = { food = it },
            placeholder = { Text("Что любит есть", style = BodyLarge) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
              focusedIndicatorColor = BrandPrimary,
              unfocusedIndicatorColor = BrandPrimary
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Описание",
        style = LabelLarge,
        color = Black,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      TextField(
        value = description,
        onValueChange = { description = it },
        placeholder = { Text("Опишите характер и привычки питомца", style = BodyLarge) },
        modifier = Modifier
          .fillMaxWidth()
          .height(120.dp),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        ),
        singleLine = false
      )

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = { showQuiz = true },
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
      ) {
        Text("Пройти квиз", style = ButtonLarge, color = White)
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 70.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {

        Button(
          onClick = {
            val sendIntent = Intent().apply {
              action = Intent.ACTION_SEND
              putExtra(Intent.EXTRA_TEXT, "Попробуйте моё приложение для создания профиля питомца! 🐾")
              type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, null))
          },
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(
            containerColor = BrandSecondary500,
            contentColor = White
          )
        ) {
          Text("Поделиться", style = ButtonLarge)
        }

        Button(
          onClick = {
            val intent = Intent(context, StoreActivity::class.java)
            context.startActivity(intent)
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = BrandSecondary500,
            contentColor = White
          )
        ) {
          Text("Store", style = ButtonLarge)
        }
      }
    }
  }
}