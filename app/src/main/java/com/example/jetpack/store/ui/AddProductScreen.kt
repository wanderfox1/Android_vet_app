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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.model.Product
import com.example.jetpack.store.viewmodel.StoreViewModel
import com.example.jetpack.ui.theme.*
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
  vm: StoreViewModel,
  nav: NavHostController
) {
  val context = LocalContext.current

  var productName by remember { mutableStateOf("") }
  var productPrice by remember { mutableStateOf("") }
  var productDescription by remember { mutableStateOf("") }

  var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
  var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

  val categories by vm.allCategories.observeAsState(emptyList())
  var selectedCategory by remember { mutableStateOf<Category?>(null) }
  var expandedCategoryMenu by remember { mutableStateOf(false) }

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
      text = "Добавить товар",
      style = TitleLarge,
      color = BrandPrimary,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    Text(
      text = "Название товара",
      style = LabelLarge,
      color = Black,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
      value = productName,
      onValueChange = { productName = it },
      placeholder = { Text("Введите название", style = BodyLarge) },
      modifier = Modifier.fillMaxWidth(),
      colors = TextFieldDefaults.colors(
        focusedIndicatorColor = BrandPrimary,
        unfocusedIndicatorColor = BrandPrimary
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Цена",
      style = LabelLarge,
      color = Black,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
      value = productPrice,
      onValueChange = { productPrice = it },
      placeholder = { Text("$0.00", style = BodyLarge) },
      modifier = Modifier.fillMaxWidth(),
      colors = TextFieldDefaults.colors(
        focusedIndicatorColor = BrandPrimary,
        unfocusedIndicatorColor = BrandPrimary
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Категория",
      style = LabelLarge,
      color = Black,
      modifier = Modifier.padding(bottom = 8.dp)
    )

    ExposedDropdownMenuBox(
      expanded = expandedCategoryMenu,
      onExpandedChange = { expandedCategoryMenu = !expandedCategoryMenu }
    ) {
      TextField(
        value = selectedCategory?.name ?: "Выберите категорию",
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoryMenu) },
        modifier = Modifier
          .fillMaxWidth()
          .menuAnchor(),
        colors = TextFieldDefaults.colors(
          focusedIndicatorColor = BrandPrimary,
          unfocusedIndicatorColor = BrandPrimary
        )
      )

      ExposedDropdownMenu(
        expanded = expandedCategoryMenu,
        onDismissRequest = { expandedCategoryMenu = false }
      ) {
        categories.forEach { category ->
          DropdownMenuItem(
            text = { Text(category.name) },
            onClick = {
              selectedCategory = category
              expandedCategoryMenu = false
            }
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Описание",
      style = LabelLarge,
      color = Black,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
      value = productDescription,
      onValueChange = { productDescription = it },
      placeholder = { Text("Введите описание товара", style = BodyLarge) },
      modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
      colors = TextFieldDefaults.colors(
        focusedIndicatorColor = BrandPrimary,
        unfocusedIndicatorColor = BrandPrimary
      ),
      singleLine = false
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = { imagePickerLauncher.launch("image/*") },
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(containerColor = BrandSecondary500)
    ) {
      Text("Выбрать изображение", style = ButtonLarge, color = White)
    }

    Spacer(modifier = Modifier.height(16.dp))

    selectedImageBitmap?.let { bitmap ->
      Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Selected product image",
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
          .padding(vertical = 8.dp)
      )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Button(
      onClick = {
        if (productName.isNotBlank() &&
          productPrice.isNotBlank() &&
          selectedCategory != null) {

          val imageByteArray = selectedImageBitmap?.let { bitmap ->
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.toByteArray()
          }

          val newProduct = Product(
            name = productName,
            price = productPrice,
            description = productDescription,
            categoryId = selectedCategory!!.id,
            imageByteArray = imageByteArray
          )

          vm.insertProduct(newProduct)
          nav.popBackStack()
        }
      },
      modifier = Modifier.fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
      enabled = productName.isNotBlank() &&
              productPrice.isNotBlank() &&
              selectedCategory != null
    ) {
      Text("Сохранить товар", style = ButtonLarge, color = White)
    }

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedButton(
      onClick = { nav.popBackStack() },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Отмена", style = ButtonLarge, color = BrandPrimary)
    }
  }
}