package com.example.cryptocurrencyapp.presentation.crypto_detail_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let { cryptoDetail ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CryptoMainInfo(cryptoDetail)
                        TagsInfo(tags = cryptoDetail.tags)
                        TeamMembersInfo(cryptoDetail.team)
                    }
                }
            }
        }
    }
}

@Composable
fun TeamMembersInfo(members: List<TeamMember>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Team members")
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(members) {index: Int, member: TeamMember ->
                TeamMemberItem(member = member)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagsInfo(tags: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Tags")
        LazyVerticalGrid(cells = GridCells.Fixed(count = 3)) {
            items(count = tags.size) { index ->
                CryptoTag(tag = tags[index])
            }
        }
    }
}

@Composable
fun CryptoMainInfo(cryptoDetail: CryptoDetail) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${cryptoDetail.rank}. ${cryptoDetail.name} (${cryptoDetail.symbol})",
                fontSize = 16.sp
            )
            Text(
                text = if (cryptoDetail.isActive) "active" else "inactive",
                color = if (cryptoDetail.isActive) Color.Green else Color.Red,
                fontSize = 12.sp
            )
        }
        Text(
            text = cryptoDetail.description,
            modifier = Modifier.fillMaxWidth()

        )
    }
}

@Composable
fun TeamMemberItem(member: TeamMember) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = member.name,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = member.position,
            style = MaterialTheme.typography.body2
            )
        Divider()
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