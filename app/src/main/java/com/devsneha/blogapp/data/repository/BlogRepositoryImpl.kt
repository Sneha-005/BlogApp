package com.devsneha.blogapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devsneha.blogapp.data.remote.BlogApi
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val blogApi: BlogApi
) : BlogRepository {
    override suspend fun getBlogs() = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            BlogPagingSource(blogApi)
        }
    ).flow
}