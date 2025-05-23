package com.devsneha.blogapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devsneha.blogapp.domain.GetBlog
import com.devsneha.blogapp.domain.model.Blog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlog
) : ViewModel() {

    private val _blogs = MutableStateFlow<PagingData<Blog>>(PagingData.empty())
    val blogs = _blogs.asStateFlow()

    init {
        getBlogs()
    }

    private fun getBlogs() {
        viewModelScope.launch {
            getBlogsUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    _blogs.value = it
                }
        }
    }
}