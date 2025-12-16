package com.example.jetpack.store.ui

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.viewmodel.StoreViewModel
import com.example.jetpack.ui.theme.*
import java.io.ByteArrayOutputStream

@Composable
fun AddCategoryScreen(
  vm: StoreViewModel,
  nav: NavHostController
) {
  val context = LocalContext.current

  var categoryName by remember { mutableStateOf("") }
  var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
  var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

  // Image picker launcher
  val imagePickerLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    selectedImageUri = uri
    uri?.let {
      selectedImageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(context.contentResolver, it)
        ImageDecoder.decodeBitmap(source)
      } else {
        @Suppress("DEPRECATION")
        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(16.dp)
  ) {
    Text(
      text = "Добавить категорию",
      style = TitleLarge,
      color = BrandPrimary,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    // Category Name
    Text(
      text = "Название категории",
      style = LabelLarge,
      color = Black,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
      value = categoryName,
      onValueChange = { categoryName = it },
      placeholder = { Text("Введите название", style = BodyLarge) },
      modifier = Modifier.fillMaxWidth(),
      colors = TextFieldDefaults.colors(
        focusedIndicatorColor = BrandPrimary,
        unfocusedIndicatorColor = BrandPrimary
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Image Picker
    Button(
      onClick = { imagePickerLauncher.launch("image/*") },
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(containerColor = BrandSecondary500)
    ) {
      Text("Выбрать изображение", style = ButtonLarge, color = White)
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Display selected image
    selectedImageBitmap?.let { bitmap ->
      Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Selected category image",
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
          .padding(vertical = 8.dp)
      )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Save Button
    Button(
      onClick = {
        if (categoryName.isNotBlank()) {
          val imageByteArray = selectedImageBitmap?.let { bitmap ->
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.toByteArray()
          }

          val newCategory = Category(
            name = categoryName,
            imageByteArray = imageByteArray
          )

          vm.insertCategory(newCategory)
          nav.popBackStack()
        }
      },
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
      enabled = categoryName.isNotBlank()
    ) {
      Text("Сохранить категорию", style = ButtonLarge, color = White)
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Cancel Button
    OutlinedButton(
      onClick = { nav.popBackStack() },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Отмена", style = ButtonLarge, color = BrandPrimary)
    }
  }
}