package com.example.jetpack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TitleLarge = TextStyle(
  fontSize = 24.sp,
  fontWeight = FontWeight.Bold
)

val TitleMedium = TextStyle(
  fontSize = 20.sp,
  fontWeight = FontWeight.Bold
)

val BodyLarge = TextStyle(
  fontSize = 16.sp,
  fontWeight = FontWeight.Bold
)

val BodyMedium = TextStyle(
  fontSize = 14.sp,
  fontWeight = FontWeight.Normal
)

val LabelLarge = TextStyle(
  fontSize = 16.sp,
  fontWeight = FontWeight.Bold
)

val ButtonLarge = TextStyle(
  fontSize = 16.sp,
  fontWeight = FontWeight.Medium
)

val Typography = Typography(
  titleLarge = TitleLarge,
  titleMedium = TitleMedium,
  bodyLarge = BodyLarge,
  bodyMedium = BodyMedium,
  labelLarge = LabelLarge,
  displayLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 57.sp
  )
)