package com.example.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksapp.ui.BooksAppUi
import com.example.booksapp.ui.BooksViewModel
import com.example.booksapp.ui.theme.BooksAppTheme

// init viewModel and show BooksAppUi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
                val viewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
                BooksAppUi(booksViewModel = viewModel)
            }
        }
    }
}