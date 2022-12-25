import com.example.booksapp.data.General
import com.example.booksapp.network.BooksAppRepository
import com.example.booksapp.ui.BooksUiState
import com.example.booksapp.ui.BooksViewModel
import fake.FakeBooksApiService
import fake.FakeBooksRepository
import fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import rules.TestDispatcherRule

class NetworkTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun testRepository() = runTest {
        val repository = BooksAppRepository(FakeBooksApiService())
        assertEquals(repository.getBooks(), General(FakeDataSource.books))
    }

    @Test
    fun testViewModel() = runTest {
        val booksViewModel = BooksViewModel(FakeBooksRepository())
        assertEquals(booksViewModel.booksUiState, BooksUiState.Success(General(FakeDataSource.books)))
    }
}