package com.example.booksapp.data

import com.example.booksapp.network.AppRepository
import com.example.booksapp.network.BooksApiService
import com.example.booksapp.network.BooksAppRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


// all dependencies
interface AppContainer {
    val booksPhotosRepository : AppRepository
}

class BooksAppContainer() : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // "ленивая" инициализация API сервиса
    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    // передаём API, с которым будем работать - Dependency Injection (DI)
    override val booksPhotosRepository: AppRepository = BooksAppRepository(retrofitService)
}