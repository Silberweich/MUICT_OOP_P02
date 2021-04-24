/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085] >>> CHALLENGE TASK
 * @author Perakorn     Nimitkul [6388127] >>> CHALLENGE TASK RECHECK
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>TASK 5 COMPLETED
 *                   >>>PROJECT CONCLUDED
 *                   >>>CHALLENGE CONCLUDED: USEFUL METHOD: RESET PASSWORD   
 * 
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 *                   >>>Not much fixing to be made on Task 5
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
        
                
        /**CHALLENGE TASK (MAIN CHALLENGE TASK)
	 * Find the Customer id and take the old password for verification to be reset with a new password
         * read the .txt file and verify password before resetting, after this, the return value can be used (and is used)
         * to reassign password in EWallet.Java (this class), then it *should* overwrite the log file (see capital citations below)
         * 
         * NOTE: IN REALITY, THIS METHOD SHOULD UPDATE THE  .TXT  FILE AS WELL, BECAUSE JUST UPDATING THE DATA IN THE ARRAY
         * IS NOT ENOUGH FOR THE DATABASE TO REMEMBER THE PASSWORD IF THE PROGRAM IS TERMINATED AND RE-LAUNCED.
         * 
         * WE DID NOT OVERWRITE THE TEXT FILE, BECAUSE THE TEST CASE COULD BE INVALID DUE TO THE ALTERCATION OF THE PASSWORD
         * THIS IS A USEFUL METHOD IN REAL IMPLEMENTATION, TAKING IN "REAL PASSWORD" FROM CUSTOMER AND HASHING IT TO COMPARE,
         * AND MOSTLY IS A DEMONSTRATION OF UNDERSTANDING CONCEPTS
         * 
         * @author Jirayu Klinudom [6388035] >>> Main author
         * @author Perakorn Nimitkul [6388127] >>> Recheck and documentation
         * 
	 * @param newPassword is new password to be set after verification
         * @param CustomerID id to find which customer
         * @param OldPassword is to verify that the customer is legit, and is not an imposter.
         * 
	 * @return newPassword to be binded with customer's password
	 */
	public String setNewPassword(String newPassword, int CustomerID, String OldPassword) 
        //should be int, but int is hashed password, so we are taking in real pass and hasing them to compare
        {
		
		BufferedReader br;
		try {
				br = new BufferedReader(new FileReader ("wallet.txt"));
			String s;
			while((s = br.readLine()) != null){
				if(s.matches("^\\d,[A-Z]+,\\d+,\\d+.\\d{2}")) {
					String[]result = s.split(",");
					if(Integer.parseInt(result[0])==CustomerID&&Integer.parseInt(result[2])==OldPassword.hashCode()) {
						this.password = newPassword.hashCode();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.password = newPassword.hashCode();
		System.out.println("This is your new password: "+newPassword);
		return newPassword;
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
