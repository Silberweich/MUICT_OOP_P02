/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>PROJECT CONCLUDED
 *                   >>>CHALLENGE CONCLUDED
 * 
 * @Note             >>>almost forgot to do the hashCode()
 */

public class PaymentEWallet extends Payment implements Authorization  {
	
	//**************************** DO NOT MODIFY **********************************//
	private String user;		// username to verify this payment transaction
	private String pwd;		// password to verify this payment transaction
	private EWallet wallet;		// EWallet associated with this payment transaction
	
	//*****************************************************************************//
	
	/**
	 * Constructor initializes the payment method's name as "EWALLET", and paid amount as given amount value 
	 * Then initializes wallet, user, and password as a plain text 
	 * @param amount (amount to be paid)
	 * @param wallet (EWallet object related to this payment)
	 * @param user   (given user to authorize this payment)
	 * @param pwd	 (given pwd to authorize this payment)
	 */
	public PaymentEWallet(double amount, EWallet wallet, String user, String pwd) 
        {
            super("EWALLET",amount);
            this.wallet = wallet;
            this.user = user;
            this.pwd = pwd;
	}
	
	/**
	 * If the payment is authorized and the wallet has sufficient balance, the paid amount will be deducted from the wallet and return true.
	 * If the payment is authorized but the wallet has insufficient balance, print payment error message and return false.
	 * If the payment is unauthorized, do nothing and return false
	 */
	
	
        @Override
	public boolean paid() 
        {
            if(authorize() && this.wallet.getBalance() >= super.amount)
            {
                this.wallet.deductBalance(super.amount);
                return true;
            }
            else if(authorize() && this.wallet.getBalance() < super.amount)
            {
                System.out.println(Payment.ERROR);
                return false;
            }
            else if(!authorize())
            {
                return false;
            }
            return false;
	}
	
	
	/**
	 * To authorize this payment, the user and pwd have to be checked against the username and password of the wallet
	 * If both username and password are matched, this returns true. 
	 * Otherwise, print authorization error message and return false
	 */
        @Override
	public boolean authorize() 
        {
            if(this.user.equals(this.wallet.getUserName()) && this.pwd.hashCode() == (this.wallet.getEncodedPassword()))
            {
                return true;
            }
            System.out.println(Authorization.ERROR);
            return false;
	}

	//**************************** DO NOT MODIFY **********************************//

    /**
     *
     * @return
     */
        @Override
	public String toString() {
		return "EWALLET::" + wallet.getCustID(); 
	}
	//**************************** DO NOT MODIFY **********************************//
	
}
