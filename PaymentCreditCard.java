/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127] >>> CHECK AND DEBUG
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 */

public class PaymentCreditCard extends Payment implements Authorization {
	
	//**************************** DO NOT MODIFY **********************************//
	
	private CreditCard card;	// CredtiCard information for this payment
	
	//*****************************************************************************//
	
	/**
	 * Constructor initializes the payment method's name as "CARD", and paid amount as given amount value 
	 * Then initialize the card instance field as a new CreditCard object with the given card's number, and card's type.
	 * @param amount (paid amount)
	 * @param number (credit card's number)
	 * @param type   (credit card's type)
	 */
	public PaymentCreditCard(double amount, String number, CreditCard.CardType type) 
        {
            super("CARD",amount);
            this.card = new CreditCard(number,type);
	}
	
	/**
	 * Constructor initializes the payment method's name as "CARD", paid amount as given amount value, and card as given credit card
	 * @param amount
	 * @param c
	 */
	public PaymentCreditCard(double amount, CreditCard c) 
        {
            super("CARD",amount);
            this.card = c;
	}
	
	/**
	 * If the payment is authorized and the paid amount is less than or equal to the CARDLIMIT, return true
	 * Otherwise, print the payment error message and return false.
	 */
        @Override
	public boolean paid() 
        {
            if(authorize() && super.amount <= CreditCard.CARDLIMIT)
            {
                return true;
            }
            System.out.println(Payment.ERROR);
            return false;
	}
	
	
	/**
	 * Unlike read-world case, if the card information is valid (see CreditCard class), return true.
	 * If the card is invalid, print the authorization error message and return false.
	 */
        @Override
	public boolean authorize() 
        {
            if(this.card.isValid())
            {
                return true;
            }
            System.out.println(Authorization.ERROR);
            return false;
	}

        
	//**************************** DO NOT MODIFY **********************************//
        @Override
	public String toString() {
		return "CARD::" + card.getNumber();
	}
	//*****************************************************************************//
	
}
