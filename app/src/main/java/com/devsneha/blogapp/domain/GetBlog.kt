package com.devsneha.blogapp.domain

import androidx.paging.PagingData
import com.devsneha.blogapp.domain.model.Blog
import com.devsneha.blogapp.domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBlog @Inject constructor(
    private val blogRepository: BlogRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Blog>> = blogRepository.getBlogs()
}