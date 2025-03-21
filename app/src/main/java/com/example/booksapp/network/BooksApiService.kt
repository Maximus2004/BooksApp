package com.example.booksapp.network

import com.example.booksapp.data.General
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") search: String): General
}