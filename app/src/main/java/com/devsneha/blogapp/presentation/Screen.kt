package com.devsneha.blogapp.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object BlogScreen: Screen("blog_screen")
}