package com.example.cryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.crypto_detail_screen.CryptoDetailScreen
import com.example.cryptocurrencyapp.presentation.crypto_list_screen.CryptoListScreen
import com.example.cryptocurrencyapp.presentation.navigation.ScreenRoute
import com.example.cryptocurrencyapp.presentation.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme(darkTheme = true) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenRoute.CryptoListScreen.route) {
                    composable(route = ScreenRoute.CryptoListScreen.route) {
                        CryptoListScreen(onCryptoItemClick = {
                            navController.navigate(ScreenRoute.CryptoDetailScreen.route + "/${it}")
                        })
                    }
                    composable(route = ScreenRoute.CryptoDetailScreen.route + "/{cryptoId}") {
                        CryptoDetailScreen()
                    }
                }
            }
        }
    }
}