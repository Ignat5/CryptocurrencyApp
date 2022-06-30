package com.example.cryptocurrencyapp.presentation.navigation

sealed class ScreenRoute(val route: String) {
    object CryptoListScreen: ScreenRoute("crypto_list_screen")
    object CryptoDetailScreen: ScreenRoute("crypto_detail_screen")
}
