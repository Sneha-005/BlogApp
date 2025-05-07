package com.devsneha.blogapp.di

import com.devsneha.blogapp.Constants
import com.devsneha.blogapp.data.remote.BlogApi
import com.devsneha.blogapp.data.repository.BlogRepositoryImpl
import com.devsneha.blogapp.domain.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun providesBlogApi() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BlogApi::class.java)

    @Singleton
    @Provides
    fun providesBlogRepository(blogApi: BlogApi): BlogRepositoryImpl = BlogRepositoryImpl(blogApi)
}