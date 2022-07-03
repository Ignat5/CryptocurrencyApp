package com.example.cryptocurrencyapp.presentation.crypto_detail_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencyapp.data.dto.TeamMember
import com.example.cryptocurrencyapp.domain.models.CryptoDetail

@Composable
fun CryptoDetailScreen(
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    val state = viewModel.cryptoState.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        state.data?.let { cryptoDetail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CryptoMainInfo(cryptoDetail)
                TagsInfo(tags = cryptoDetail.tags)
                TeamMembersInfo(cryptoDetail.team)
            }
        }
    }
}

@Composable
fun CryptoMainInfo(cryptoDetail: CryptoDetail) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${cryptoDetail.rank}. ${cryptoDetail.name} (${cryptoDetail.symbol})",
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = if (cryptoDetail.isActive) "active" else "inactive",
                color = if (cryptoDetail.isActive) Color.Green else Color.Red,
                fontSize = 18.sp
            )
        }
        Text(
            text = cryptoDetail.description,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagsInfo(tags: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Tags", fontSize = 24.sp, color = Color.White)
        Spacer(Modifier.height(4.dp))
        LazyVerticalGrid(cells = GridCells.Fixed(count = 2)) {
            items(count = tags.size) { index ->
                CryptoTag(tag = tags[index])
            }
        }
    }
}

@Composable
fun CryptoTag(tag: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TeamMembersInfo(members: List<TeamMember>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Team members", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(members) { index: Int, member: TeamMember ->
                TeamMemberItem(member = member)
            }
        }
    }
}

@Composable
fun TeamMemberItem(member: TeamMember) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = member.position,
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = member.name,
            color = Color.White,
            style = MaterialTheme.typography.h6
        )
        Divider(color = Color.White)
    }
}