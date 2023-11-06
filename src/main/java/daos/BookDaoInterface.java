package daos;

import business.Book;

public interface BookDaoInterface {
    public Book DisplayAllBook();

    public int removeBook(int bookId);

    public int borrowBook(int bookId, int genreId, String title,String author,int numberOfCopies);
}
