package com.example.cryptocurrencyapp.presentation.crypto_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencyapp.domain.models.Crypto

@Composable
fun CryptoListScreen(
    viewModel: CryptoListViewModel = hiltViewModel(),
    onCryptoItemClick: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = viewModel.cryptoState.value.data.sortedBy { it.rank }
            ) { index, crypto ->
                CryptoListItem(modifier = Modifier
                    .clickable { onCryptoItemClick(crypto.id) },
                    crypto = crypto)
            }
        }
        viewModel.cryptoState.value.errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Center),
            )
        }
        if (viewModel.cryptoState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun CryptoListItem(modifier: Modifier = Modifier, crypto: Crypto) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
            ,
            text = buildAnnotatedString {
                append(crypto.rank.toString())
                append(".")
                append(crypto.name)
                append("(${crypto.symbol})")
            },
            color = Color.White,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = buildAnnotatedString {
            var text = ""
            val style: SpanStyle
            if (crypto.isActive) {
                text = "active"
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 12.sp
                )
            } else {
                text = "inactive"
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
            pushStyle(style)
            append(text)
        }
        )
    }
}