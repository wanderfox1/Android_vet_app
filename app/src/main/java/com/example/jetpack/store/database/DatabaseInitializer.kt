package com.example.jetpack.store.database

import android.content.Context
import com.example.jetpack.store.model.Category
import com.example.jetpack.store.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {

  fun initialize(context: Context, database: StoreDatabase) {
    CoroutineScope(Dispatchers.IO).launch {
      val categoryDao = database.categoryDao()
      val productDao = database.productDao()

      val existingCategories = categoryDao.getAllCategoriesForInit()
      if (existingCategories.isNotEmpty()) {
        return@launch // Already initialized
      }

      val categories = listOf(
        Category(name = "Для женщин", imageByteArray = null),      // For Women
        Category(name = "Для мужчин", imageByteArray = null),      // For Men
        Category(name = "Для детей", imageByteArray = null),       // For Children
        Category(name = "Для питомцев", imageByteArray = null),    // For Pets
        Category(name = "Аксессуары", imageByteArray = null),      // Accessories
        Category(name = "Электроника", imageByteArray = null)      // Electronics
      )

      val categoryIds = mutableListOf<Long>()
      categories.forEach { category ->
        val id = categoryDao.insert(category)
        categoryIds.add(id)
      }

      val products = mutableListOf<Product>()

      if (categoryIds.isNotEmpty()) {
        products.addAll(
          listOf(
            Product(
              name = "Платье летнее",
              price = "3500 ₽",
              description = "Легкое летнее платье из натуральных тканей",
              categoryId = categoryIds[0].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Сумка кожаная",
              price = "5200 ₽",
              description = "Стильная сумка из натуральной кожи",
              categoryId = categoryIds[0].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Туфли на каблуке",
              price = "4800 ₽",
              description = "Элегантные туфли для особых случаев",
              categoryId = categoryIds[0].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      if (categoryIds.size > 1) {
        products.addAll(
          listOf(
            Product(
              name = "Рубашка классическая",
              price = "2800 ₽",
              description = "Деловая рубашка из хлопка",
              categoryId = categoryIds[1].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Ремень кожаный",
              price = "1500 ₽",
              description = "Классический кожаный ремень",
              categoryId = categoryIds[1].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Кроссовки спортивные",
              price = "6500 ₽",
              description = "Удобные кроссовки для спорта и отдыха",
              categoryId = categoryIds[1].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      if (categoryIds.size > 2) {
        products.addAll(
          listOf(
            Product(
              name = "Футболка детская",
              price = "800 ₽",
              description = "Яркая футболка с принтом",
              categoryId = categoryIds[2].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Рюкзак школьный",
              price = "2200 ₽",
              description = "Удобный рюкзак для школы",
              categoryId = categoryIds[2].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Игрушка мягкая",
              price = "1200 ₽",
              description = "Плюшевая игрушка медведь",
              categoryId = categoryIds[2].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      if (categoryIds.size > 3) {
        products.addAll(
          listOf(
            Product(
              name = "Корм для собак",
              price = "1800 ₽",
              description = "Сухой корм премиум класса",
              categoryId = categoryIds[3].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Игрушка для кошек",
              price = "350 ₽",
              description = "Интерактивная игрушка-мышка",
              categoryId = categoryIds[3].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Ошейник",
              price = "650 ₽",
              description = "Регулируемый ошейник со светоотражателем",
              categoryId = categoryIds[3].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      if (categoryIds.size > 4) {
        products.addAll(
          listOf(
            Product(
              name = "Часы наручные",
              price = "8900 ₽",
              description = "Стильные часы с кожаным ремешком",
              categoryId = categoryIds[4].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Солнцезащитные очки",
              price = "3200 ₽",
              description = "Очки с UV защитой",
              categoryId = categoryIds[4].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Кошелек",
              price = "1800 ₽",
              description = "Компактный кошелек из кожи",
              categoryId = categoryIds[4].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      if (categoryIds.size > 5) {
        products.addAll(
          listOf(
            Product(
              name = "Наушники беспроводные",
              price = "4500 ₽",
              description = "Bluetooth наушники с шумоподавлением",
              categoryId = categoryIds[5].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Powerbank",
              price = "2200 ₽",
              description = "Внешний аккумулятор 10000 mAh",
              categoryId = categoryIds[5].toInt(),
              imageByteArray = null
            ),
            Product(
              name = "Чехол для телефона",
              price = "890 ₽",
              description = "Защитный чехол с подставкой",
              categoryId = categoryIds[5].toInt(),
              imageByteArray = null
            )
          )
        )
      }

      products.forEach { product ->
        productDao.insert(product)
      }
    }
  }
}