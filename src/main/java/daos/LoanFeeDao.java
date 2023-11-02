package daos;

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
    public int payLateFee(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean state = false;
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
        return 0;
    }
}
