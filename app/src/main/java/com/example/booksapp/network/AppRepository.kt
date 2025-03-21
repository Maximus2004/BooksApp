package com.example.booksapp.network

import com.example.booksapp.data.General

interface AppRepository {
    suspend fun getBooks(search: String) : General?
}

class BooksAppRepository(private val marsApiService: BooksApiService) : AppRepository {
    override suspend fun getBooks(search: String): General? {
        return runCatching { marsApiService.getBooks(search) }.getOrNull()
    }
}