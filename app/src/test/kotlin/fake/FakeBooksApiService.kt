package fake

import com.example.booksapp.data.General
import com.example.booksapp.network.BooksApiService

class FakeBooksApiService: BooksApiService {
    override suspend fun getBooks(): General {
        return General(FakeDataSource.books)
    }
}