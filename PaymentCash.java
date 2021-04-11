/**
 * @author Phichayut    Ngoennim [6388035]
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 */
public class PaymentCash extends Payment{
	
	//**************************** DO NOT MODIFY **********************************//
			
	private double cashTendered;	// Cash tendered is a sum of money given in payment. It may not be equal to the exact amount owed.
	private double change;		// If the cash tendered is more than the amount, the change have to be calculated
	
	//*****************************************************************************//
	/**
	 * Constructor initializes the payment method's name as "CASH", 
         * paid amount, and cash tendered.The instant file "change" is set to 0 (no change is calculated yet)
         * @param amount
	 * @param paid amount 
	 * @param cash tendered
	 */
	
	public PaymentCash(double amount, double cash) 
        {
            super("CASH",amount);
            this.cashTendered = cash;
            this.change = 0;	
	}
	
	/**
	 * If the cash tendered is more than the amount, the change have to be calculated and return true
	 * Otherwise, print the error message "Sorry, you have insufficient balance!" (Payment.ERROR) and return false
	 */
        @Override
	public boolean paid() 
        {
            if(this.cashTendered > super.amount)
            {
                this.change = this.cashTendered - super.amount;
            }
            System.out.println(Payment.ERROR);
            return false;	
	}
        
	/**
	 * @Override paymentInformation
	 * @return string to provide information of this payment
	 * *** note. you have a freedom to design your output here ***
	 */
        @Override
	public String paymentInformation() 
        {
            return "You Paid with " + super.method + "!, Amount: " + amount + "Thats very cash money of you good sir!";
	}

	//**************************** DO NOT MODIFY **********************************//
	public double getChange() {
		return this.change;
	}
	
        @Override
	public String toString() {
		return "CASH::" + cashTendered;
	}
	//*****************************************************************************//	
}
