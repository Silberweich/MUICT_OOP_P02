/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>TASK 5 ON GOING
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 */

public class EWallet implements Loggable{
	
	//**************************** DO NOT MODIFY **********************************//
	private int custID;		// customer's ID who owns this wallet
	private String username;
	private int password;		// password will be encoded using hashCode()
	private double balance;		// current balance in this wallet
	
	//*****************************************************************************//
	
	/**
	 * Constructor initializes customer's ID, username, encoded password, and balance
	 * @param custID
	 * @param user
	 * @param encodedPwd
	 * @param balance
	 */
	public EWallet(int custID, String user, int encodedPwd, double balance) 
        {
            this.custID = custID;
            this.username = user;
            this.password = encodedPwd;
            this.balance = balance;
	}
	
	/**
	 * Similar to the previous constructor, but the password is provided as the plain text.
	 * The plain text password have to be encoded before assigned to password instance field.
	 * @param custID
	 * @param user
	 * @param plainPwd
	 * @param balance
	 */
	public EWallet(int custID, String user, String plainPwd, double balance) 
        {
            this.custID = custID;
            this.username = user;
            this.password = plainPwd.hashCode();
            this.balance = balance;
	}
	
	/**
	 * Increase the balance by the given amount and return true.
	 * If the amount is zero or negative, 
	 * print a message ("Cannot add balance. Incorrect amount!") and return false. 
	 * @param amount to be added
	 * @return boolean
	 */
	public boolean addBalance(double amount)
        {
            if(amount > 0)
            {
                this.balance += amount;
                return true;
            }
            System.out.println("Cannot add balance. Incorrect amount!");
            return false;	
	}
	
	/**
	 * Decrease the balance by the given amount and return true.
	 * If the amount is zero or negative, 
	 * print a  message ("Cannot deduct balance. Incorrect amount!") and return false
	 * @param amount to be deducted
	 * @return boolean
	 */
	public boolean deductBalance(double amount)
        {
            if(amount > 0)
            {
                this.balance -= amount;
                return true;
            }
            System.out.println("Cannot deduct balance. Incorrect amount!");
            return false;		
	}
        
        @Override
        public String log()
        {
            String credits = df.format(this.balance);
            return this.custID + "," + this.username + "," + this.password + "," + credits;
        }
	
	//**************************** DO NOT MODIFY **********************************//
	
	public int getCustID() {
		return custID;
	}
	
	public String getUserName() {
		return username;
	}
	
	public int getEncodedPassword() {
		return password;
	}
	
	public double getBalance(){
		return balance;
	}
	
	@Override
	public String toString(){
		return "CID::" + custID 
				+ ",username::" + username 
				+ ",encodedpassword::" + password 
				+ ",balance::" + Loggable.df.format(balance);
	}
	
	//*****************************************************************************//
	
}
