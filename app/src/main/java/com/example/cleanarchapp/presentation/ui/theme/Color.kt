package com.example.cleanarchapp.presentation.ui.theme

import androidx.compose.ui.graphics.Color

//dark theme
val bluePrimaryDark = Color(0xFF64B5F6)     // Primary — ярко-голубой акцент (кнопки, выделения)
val onBluePrimaryDark = Color(0xFF0A192F)   // OnPrimary — тёмный текст/иконки на голубом
val blueSecondaryDark = Color(0xFF2196F3)   // Secondary — чистый синий (иконки, активные элементы)
val onBlueSecondaryDark = Color(0xFFFFFFFF) // OnSecondary — белый текст/иконки на синем
val tealTertiaryDark = Color(0xFF4DD0E1)    // Tertiary — бирюзовый акцент для разнообразия
val onTealTertiaryDark = Color(0xFF002021)  // OnTertiary — тёмный текст/иконки на бирюзовом
val backgroundDark = Color(0xFF111726)      // Background — глубокий тёмно-синий фон (вместо чистого чёрного)
val onBackgroundDark = Color(0xFFE3F2FD)    // OnBackground — светло-голубой текст на фоне
val surfaceDark = Color(0xFF273549) // Surface — более светлый синий-серый, гармонирует с 0xFF1E293B
val onSurfaceDark = Color(0xFFDEE9F7) // OnSurface — очень светлый голубоватый текст/иконки
val snackbarContainerDark = Color(0xFF1C2836) // фон Snackbar
val snackbarContentDark = onSurfaceDark       // текст
val snackbarActionDark = bluePrimaryDark      // кнопка

//light theme
val bluePrimary = Color(0xFF1565C0)       // Primary — яркий синий, гармонирует с dark Primary
val onBluePrimary = Color(0xFFFFFFFF)     // OnPrimary — белый текст/иконки
val blueSecondary = Color(0xFF5E92F3)     // Secondary — светло-голубой акцент
val onBlueSecondary = Color(0xFFFFFFFF)   // OnSecondary — текст на Secondary
val tealTertiary = Color(0xFF00897B)      // Tertiary — бирюзовый акцент (контрастный к синим)
val onTealTertiary = Color(0xFFFFFFFF)    // OnTertiary — текст/иконки на бирюзовом
val backgroundLight = Color(0xFFFDFDFD)   // Background — почти белый фон
val onBackgroundLight = Color(0xFF1C1B1F) // OnBackground — основной тёмный текст
val surfaceLight = Color(0xFFF5F5F5)      // Surface — серый фон карточек
val onSurfaceLight = Color(0xFF3C3C3C)    // OnSurface — текст на поверхностях
val snackbarContainerLight = Color(0xFF323232) // фон Snackbar
val snackbarContentLight = onBluePrimary        // текст
val snackbarActionLight = bluePrimary          // кнопка
