package fake

import com.example.booksapp.data.General
import com.example.booksapp.network.AppRepository

class FakeBooksRepository: AppRepository {
    override suspend fun getBooks(): General {
        return General(FakeDataSource.books)
    }
}