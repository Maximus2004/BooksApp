package com.example.booksapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booksapp.R
import com.example.booksapp.data.Book
import com.example.booksapp.data.General
import com.example.booksapp.data.Link
import com.example.booksapp.ui.BooksUiState
import com.example.booksapp.ui.theme.BooksAppTheme

@Composable
fun HomeScreen(booksUiState: BooksUiState, modifier: Modifier = Modifier) {
    when (booksUiState) {
        is BooksUiState.Success -> SuccessScreen(booksUiState.books.items)
        is BooksUiState.Loading -> LoadingScreen()
        is BooksUiState.Error -> ErrorScreen()
    }
}

@Composable
fun PrintInfo(general: General) {
    Text(text = "$general")
}

@Composable
fun SuccessScreen(books: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
    ) {
        items(books) { book ->
            if (book.volumeInfo.imageLinks?.thumbnail != null)
                BookItem(book.volumeInfo.imageLinks.thumbnail)
            else {
                BookItem(link = "")
            }
        }
    }
}

@Composable
fun BookItem(link: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = 6.dp
    ) {
        if (link == "") {
            Image(
                painter = painterResource(id = R.drawable.ic_broken_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(link.replace("http", "https"))
                    // плавное появление изображения (анимация)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}