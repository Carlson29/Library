package daos;

import business.Loan;
import business.LoanFee;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoanFeeDao extends Dao implements LoanFeeDaoInterface{


    public LoanFeeDao(String databaseName) {
        super(databaseName);
    }

    public LoanFeeDao(Connection con) {
        super(con);
    }


    /**
     * @param loanId @return int, number of rows affected
     *               method pays a user late fee
     */
    @Override
    public int insertReturnDate(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        try {
                con = getConnection();

                String command = "update loans set returnDate=? where loanId=?";
                ps = con.prepareStatement(command);
                ps.setString(1, (formatter.format(now)));
                ps.setInt(2, loanId);
                rowsAffected = ps.executeUpdate();


            } catch (SQLException e) {
                System.out.println("Exception occured in  the payLateFee() method: " + e.getMessage());
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
                    System.out.println("Exception occured in  the payLateFee() method:  " + e.getMessage());
                }
            }


       return rowsAffected;
    }

    /**
     * @param loanId
     * @param fee    @return int, number of rows affected
     *               method pays a user late while keeping track of the late fee
     **/
    @Override
    public int payLateFee(int loanId, double fee) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String command = "insert into loanFee(loanId,loanFee) values(?,?) ";
            ps = con.prepareStatement(command);
            ps.setInt(1, loanId);
            ps.setDouble(2, fee);
            rowsAffected = ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Exception occured in  the payLateFee() method: " + e.getMessage());
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
                System.out.println("Exception occured in  the payLateFee() method:  " + e.getMessage());
            }
        }


        return rowsAffected;
    }

    public int deleteReturnDate(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        int rowsAffected1 = 0;
        try {
            con = getConnection();

            String command = "update loans set returnDate=null where loanId=? ";
            ps = con.prepareStatement(command);
            ps.setInt(1, loanId);
            rowsAffected = ps.executeUpdate();



        } catch (SQLException e) {
            System.out.println("Exception occured in  the payLateFee() method: " + e.getMessage());
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
                System.out.println("Exception occured in  the payLateFee() method:  " + e.getMessage());
            }
        }


        return rowsAffected;
    }

    public int deleteLateFee(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String command = "delete from loanFee where loanId=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, loanId);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the deleteLateFee() method: " + e.getMessage());
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
                System.out.println("Exception occurred in  the deleteLateFee() method:  " + e.getMessage());
            }
        }


        return rowsAffected;
    }

    public Loan getLoan(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        Loan l=null;
        try {
            con = getConnection();

            String query = "select * from loans where loanId= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, loanId);
            rs = ps.executeQuery();

            if(rs.next())
            {
l = new Loan(rs.getInt("loanId"),rs.getInt("userId"),rs.getInt("bookId"),rs.getDate("dateOfLoan"),rs.getDate("dueDate"),rs.getDate("returnDate"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in  the getLoan() method: " + e.getMessage());
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
                System.out.println("Exception occured in  the getLoan() method:  " + e.getMessage());
            }
        }


        return l;
    }

    public LoanFee getLateLoanfee(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        LoanFee l=null;
        try {
            con = getConnection();

            String query = "select * from loanfee where loanId= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, loanId);
            rs = ps.executeQuery();

            if(rs.next())
            {
                l = new LoanFee(rs.getInt("loanId"),rs.getDouble("loanFee"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in  the checkLateLoanfee() method: " + e.getMessage());
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
                System.out.println("Exception occured in  the checkLateLoanfee() method:  " + e.getMessage());
            }
        }


        return l;
    }

}
