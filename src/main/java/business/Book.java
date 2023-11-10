package business;

import java.util.*;

public class Book implements List<Book> {
    private int bookId;
    private int genreId;
    private String title;
    private String author;
    private int numberOfCopies;

    public Book(int bookId, int genreId, String title, String author, int numberOfCopies) {
        this.bookId = bookId;
        this.genreId = genreId;
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @param o element whose presence in this list is to be tested
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public Iterator<Book> iterator() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * @param a   the array into which the elements of this list are to
     *            be stored, if it is big enough; otherwise, a new array of the
     *            same runtime type is allocated for this purpose.
     * @param <T>
     * @return
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * @param book element whose presence in this collection is to be ensured
     * @return
     */
    @Override
    public boolean add(Book book) {
        return false;
    }

    /**
     * @param o element to be removed from this list, if present
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * @param c collection to be checked for containment in this list
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    /**
     * @param c collection containing elements to be added to this collection
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends Book> c) {
        return false;
    }

    /**
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends Book> c) {
        return false;
    }

    /**
     * @param c collection containing elements to be removed from this list
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * @param c collection containing elements to be retained in this list
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     *
     */
    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getBookId() == book.getBookId() && getGenreId() == book.getGenreId() && getNumberOfCopies() == book.getNumberOfCopies() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getGenreId(), getTitle(), getAuthor(), getNumberOfCopies());
    }

    /**
     * @param index index of the element to return
     * @return
     */
    @Override
    public Book get(int index) {
        return null;
    }

    /**
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return
     */
    @Override
    public Book set(int index, Book element) {
        return null;
    }

    /**
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, Book element) {

    }

    /**
     * @param index the index of the element to be removed
     * @return
     */
    @Override
    public Book remove(int index) {
        return null;
    }

    /**
     * @param o element to search for
     * @return
     */
    @Override
    public int indexOf(Object o) {
        return 0;
    }

    /**
     * @param o element to search for
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public ListIterator<Book> listIterator() {
        return null;
    }

    /**
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return
     */
    @Override
    public ListIterator<Book> listIterator(int index) {
        return null;
    }

    /**
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return
     */
    @Override
    public List<Book> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", genreId=" + genreId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}