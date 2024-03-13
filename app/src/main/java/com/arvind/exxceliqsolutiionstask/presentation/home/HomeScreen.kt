package com.arvind.exxceliqsolutiionstask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arvind.exxceliqsolutiionstask.R
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import com.arvind.exxceliqsolutiionstask.presentation.home.viewmodel.UsersInfoViewModel
import com.arvind.exxceliqsolutiionstask.ui.theme.GrapeFruitColor
import com.arvind.exxceliqsolutiionstask.utils.WindowSize
import com.arvind.exxceliqsolutiionstask.utils.WindowType
import retrofit2.HttpException
import java.io.IOException

@Composable
fun HomeScreen(
    viewModel: UsersInfoViewModel = hiltViewModel(),
    windowSize: WindowSize
) {

    val usersInfo = viewModel.usersInfo.value.collectAsLazyPagingItems()

    UsersInfoItems(
        pagingItems = usersInfo,
        onErrorClick = {
            viewModel.usersInfo
        },
        windowSize = windowSize
    )
}

@Composable
fun UsersInfoItems(
    pagingItems: LazyPagingItems<UserData>,
    onErrorClick: () -> Unit,
    windowSize: WindowSize
) {
    val maxLines by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 4 else 10)
    }
    when (windowSize.height) {
        WindowType.Expanded -> {
            Column {
                PagingUserInfoData(pagingItems, onErrorClick)
            }
        }

        else -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PagingUserInfoData(pagingItems, onErrorClick)
            }
        }
    }

}

@Composable
private fun PagingUserInfoData(
    pagingItems: LazyPagingItems<UserData>,
    onErrorClick: () -> Unit
) {
    when (pagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            CircularProgressBar()
        }

        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(pagingItems.itemCount) { index ->
                    UserInfoContent(
                        firstname = pagingItems[index]?.first_name,
                        lastname = pagingItems[index]?.last_name,
                        email = pagingItems[index]?.email,
                        avatar = pagingItems[index]?.avatar,
                    )

                }
            }

        }

        is LoadState.Error -> {
            val error = pagingItems.loadState.refresh as LoadState.Error
            val errorMessage = when (error.error) {
                is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                is IOException -> "Connection failed. Tap to retry!"
                else -> "Failed! Tap to retry!"
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(161.25.dp) // maintain the vertical space between two categories
                    .clickable {
                        onErrorClick()
                    }
            ) {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = GrapeFruitColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        else -> {
        }
    }
}

@Composable
fun UserInfoContent(
    firstname: String?,
    lastname: String?,
    email: String?,
    avatar: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatar)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    text = "$firstname $lastname",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = email.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }

    }
}

@Composable
fun CircularProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}
