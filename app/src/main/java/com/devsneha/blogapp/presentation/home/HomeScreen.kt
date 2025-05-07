package com.devsneha.blogapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.devsneha.blogapp.presentation.viewModels.HomeViewModel
import com.devsneha.blogapp.R
import com.devsneha.blogapp.presentation.home.views.ErrorView
import com.devsneha.blogapp.presentation.home.views.LoadingView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit = {}
) {
    val blogs = viewModel.blogs.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.off_white))
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(blogs.itemCount) { index ->
            blogs[index]?.let { blog ->
                BlogItems(blog) { url -> onItemClick(url) }
            }
        }
        when (val state = blogs.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    LoadingView("Loading blogs...")
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorView(state.error.localizedMessage ?: "Something went wrong")
                }
            }

            else -> Unit
        }

        when (val state = blogs.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingView("Loading more...")
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorView(state.error.localizedMessage ?: "Failed to load more blogs")
                }
            }

            else -> Unit
        }
    }
}
