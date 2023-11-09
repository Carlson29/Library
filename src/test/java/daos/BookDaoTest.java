package daos;

import business.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    private BookDao bookDao;
    @BeforeEach
    void setUp() {
        bookDao = new BookDao("library");
        // This will allow to BookDao test database connection
    }

    //Test to Display All Books
    @Test
    void testDisplayAllBook() {

        //Should Display all current books.
        Book result = bookDao.DisplayAllBook();
        assertNotNull(result);
        assertEquals(1, result.getBookId());
        assertEquals(1, result.getGenreId());
        assertEquals("Battle Zone", result.getTitle());
        assertEquals("Jacob smith", result.getAuthor());
        assertEquals(40, result.getNumberOfCopies());
    }

    //Test Remove a Book
    @Test
    void testRemoveBook() {
        int bookIdToRemove = 2;
        int rowsAffected = bookDao.removeBook(bookIdToRemove);

        assertEquals(2, rowsAffected);

        //To Prove that the book with the specified bookId is gone
        Book book = bookDao.DisplayAllBook();
        assertNull(book); // book should no longer appear in the database
    }

    //Test to Add a Book
    @Test
    void testAddBook() {
        int bookId = 4;
        int genreId = 2;
        String title = "Jupiter's Legacy";
        String author = "Mark Miller";
        int numberOfCopies = 7;

        int newId = bookDao.addBook(bookId, genreId, title, author, numberOfCopies);

        assertTrue(newId > 0);

        // To check that no book has actually been added to DB
        Book newBook = bookDao.DisplayAllBook();

        assertNotNull(newBook);
        assertEquals(bookId, newBook.getBookId());
        assertEquals(genreId, newBook.getGenreId());
        assertEquals(title, newBook.getTitle());
        assertEquals(author, newBook.getAuthor());
        assertEquals(numberOfCopies, newBook.getNumberOfCopies());
    }
    @Test
    void testBorrowBook() {
        int bookIdToBorrow = 1;
        int userId = 1;

        int rowsAffected = bookDao.BorrowBook(bookIdToBorrow, userId);

        assertTrue(rowsAffected > 0);

        int updatedCopies = bookDao.BorrowBook(1,1);
        assertTrue(updatedCopies >= 0);
    }


@org.junit.jupiter.api.AfterEach
    void tearDown() {
    bookDao = new BookDao("library");
    bookDao.removeBook(2);
    }


}