package daos;

public interface LoanFeeDaoInterface {
/**@param loanId, the loan's Id
 * @return int, number of rows affected
 * method pays a user late fee
 *
 * */
    public int insertReturnDate(int loanId);
    /**@param loanId, the loan's Id
     * @param fee, the loan's fee
     * @return int, number of rows affected
     * method pays a user late while keeping track of the late fee**/
    public int payLateFee(int loanId,double fee);
}
