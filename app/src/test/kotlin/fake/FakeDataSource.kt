package fake

import com.example.booksapp.data.Book
import com.example.booksapp.data.Link
import com.example.booksapp.data.Links

object FakeDataSource {
    val books = listOf(
        Book(Links(Link("url.1"))),
        Book(Links(Link("url.2"))),
        Book(Links(Link("url.3")))
    )
}