package com.devsneha.blogapp.domain.repository

import androidx.paging.PagingData
import com.devsneha.blogapp.domain.model.Blog
import kotlinx.coroutines.flow.Flow

interface BlogRepository {
    suspend fun getBlogs(): Flow<PagingData<Blog>>
}