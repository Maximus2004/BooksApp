package com.example.booksapp.network

import com.example.booksapp.data.General

interface AppRepository {
    suspend fun getBooks() : General
}

class BooksAppRepository(private val marsApiService: BooksApiService) : AppRepository {
    override suspend fun getBooks(): General {
        return marsApiService.getBooks()
    }
}