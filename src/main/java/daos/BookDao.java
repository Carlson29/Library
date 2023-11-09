package daos;

import business.Book;

import java.sql.*;


public class BookDao extends Dao implements BookDaoInterface {

    public BookDao(String databaseName) {
        super(databaseName);
    }

    public BookDao(Connection con) {
        super(con);
    }

    public Book DisplayAllBook() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        Book b = null;

        try {
            con = getConnection();

            String query = "select * from books";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                b = new Book(rs.getInt("bookId"), rs.getInt("genreId"), rs.getString("title"), rs.getString("author"), rs.getInt("numberOfCopies"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in  the displayAllBooks() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in  the displayAllBooks() method:  " + e.getMessage());
            }
        }


        return b;
    }

    public int removeBook(int bookId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String command = "delete from books where bookId=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, bookId);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the removeBook() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the removeBook() method:  " + e.getMessage());
            }
        }


        return rowsAffected;
    }

    public int addBook(int bookId, int genreId, String title, String author, int numberOfCopies) {
        Connection con = null;
        PreparedStatement ps = null;
        //This will be used to hold the generated ID (i.e. the value auto-generated
        // by MySQL when inserting this entry into the database
        ResultSet generatedKeys = null;
        // Set the newId value to a default of -1
        // If the value returned by the method is -1, we know that the update failed
        // as the id value was never updated
        int newId = -1;

        try {
            con = this.getConnection();

            String query = "INSERT INTO books(bookId,genreId,title,author,numberOfCopies) VALUES (?, ?, ?, ?, ?)";

            // Need to get the id back, so have to tell the database to return the id it generates
            // That is why we include the Statement.RETURN_GENERATED_KEYS parameter
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, bookId);
            ps.setInt(2, genreId);
            ps.setString(3, title);
            ps.setString(4, author);
            ps.setInt(5, numberOfCopies);

            // Because this is CHANGING the database, use the executeUpdate method
            ps.executeUpdate();

            // Find out what the id generated for this entry was
            generatedKeys = ps.getGeneratedKeys();
            // If there was a result, i.e. if the entry was inserted successfully
            if (generatedKeys.next()) {
                // Get the id value that was generated by MySQL when the entry was inserted
                newId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the addBook method:");
            System.err.println("\t" + e.getMessage());
            newId = -1;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the addBook method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    public int BorrowBook(int bookId, int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            // Check if the user is allowed to borrow a book
            String able = "SELECT disable FROM users WHERE userId = ?";
            ps = con.prepareStatement(able);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int userDisable = rs.getInt("disable");
                if (userDisable == 1) {
                    // Check if the book is available (has more than 0 copies)
                    String query = "SELECT numberOfCopies FROM books WHERE bookId = ?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, bookId);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        int numberOfCopies = rs.getInt("numberOfCopies");
                        if (numberOfCopies > 0) {
                            // If the user is allowed to borrow the book, decrement the number of copies and update the database.
                            String borrow = "UPDATE books SET numberOfCopies = ? WHERE bookId = ?";
                            ps = con.prepareStatement(borrow);
                            ps.setInt(1, numberOfCopies - 1);
                            ps.setInt(2, bookId);
                            rowsAffected = ps.executeUpdate();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the BorrowBook method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the BorrowBook method: " + e.getMessage());
            }
        }

        return rowsAffected;
    }
    //
    //
    //   public int returnBook ( int bookId)
    //   {
    //       Connection con = null;
    //       PreparedStatement ps = null;
    //       ResultSet rs = null;
    //       int rowsAffected = 0;
    //
    //       return 0;
    //   }
}

