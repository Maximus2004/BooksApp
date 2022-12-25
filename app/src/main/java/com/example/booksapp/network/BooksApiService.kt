package com.example.booksapp.network

import com.example.booksapp.data.General
import retrofit2.http.GET

interface BooksApiService {
    @GET("volumes?q=пушкин")
    suspend fun getBooks(): General
}