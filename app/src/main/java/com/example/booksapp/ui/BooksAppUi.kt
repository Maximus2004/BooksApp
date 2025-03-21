package com.example.booksapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.booksapp.ui.screens.HomeScreen

// show HomeScreen
@Composable
fun BooksAppUi(booksViewModel: BooksViewModel, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = "Books App") }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(
                booksUiState = booksViewModel.booksUiState,
                onSearchClick = booksViewModel::getBooks
            )
        }
    }
}