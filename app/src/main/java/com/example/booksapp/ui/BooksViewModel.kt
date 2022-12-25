package com.example.booksapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.booksapp.BooksApplication
import com.example.booksapp.data.Book
import com.example.booksapp.data.General
import com.example.booksapp.network.AppRepository
import kotlinx.coroutines.launch
import java.io.IOException

class BooksViewModel(private val booksAppRepository: AppRepository): ViewModel() {
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            try {
                val result = booksAppRepository.getBooks()
                booksUiState = BooksUiState.Success(result)
            } catch (e: IOException) {
                booksUiState = BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // APPLICATION_KEY помогает найти MarsPhotosApplication, который содержит container
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BooksApplication)
                // container содержит marsPhotosRepository, который нужен MarsViewModel для реализации Dependency Injection
                val booksPhotosRepository = application.container.booksPhotosRepository
                // наконец-то передаём repository во ViewModel
                BooksViewModel(booksAppRepository = booksPhotosRepository)
            }
        }
    }
}

sealed interface BooksUiState {
    data class Success(val books: General): BooksUiState
    object Loading: BooksUiState
    object Error: BooksUiState
}