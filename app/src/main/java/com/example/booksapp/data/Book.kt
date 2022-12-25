package com.example.booksapp.data

import kotlinx.serialization.Serializable

// нужная ссылка на изображение находится по адресу General/Book/volumeInfo/imageLinks/thumbnail
@Serializable
data class General(
    val items: List<Book>
)

@Serializable
data class Book(
    val volumeInfo: Links
)

@Serializable
data class Links(
    val imageLinks: Link? = null
)

@Serializable
data class Link(
    val thumbnail: String? = null
)

