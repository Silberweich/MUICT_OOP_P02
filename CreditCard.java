/**
 * @author Phichayut    Ngoennim [6388035]
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 */
public class CreditCard{
	
	//**************************** DO NOT MODIFY **********************************//
	public static enum CardType{ VISA, AMERICANEXPRESS, JCB, MASTERCARD }; 
					// different types of credit card 
	
	public static final double CARDLIMIT = 5000.00;	
					// assume that for "each payment transaction" the maximum amount is 5,000
	
	private String number;		// card's number
	private CardType type;		// card's type
	//*****************************************************************************//
	
	/**
	 * Constructor initializes card number and card type
	 * @param number
	 * @param type
	 */
	public CreditCard(String number, CardType type) {
		//******************* YOUR CODE HERE ******************
		this.number = number;
		this.type = type;
		//*****************************************************	
	}
	/**
	 * Verify the validity of the card information. Different card type has different format of card number as follow
	 * VISA -> the number must be 16 digits, and start with number 4
	 * AMERICANEXPRESS -> the number must be 15 digits, and start with either 34 or 37
	 * JCB -> the number must be 16 digits, and start with 3528 to 3589
	 * MASTERCARD -> the number must be 16 digits, and start with 51 or 52
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", this card is valid.
	 * If the card is JCB and has number "4234567890123456", this card is invalid.
	 * 
	 * @return True if the card is valid, otherwise return false
	 */
	public boolean isValid() 
        {
            boolean valid = false;
            switch(type) 
            {
                case VISA:
                    if(number.length()==16&&number.startsWith("4")) 
                    {
                            valid = true;
                    }
                    break;
                case AMERICANEXPRESS:
                    if(number.length()==15&&(number.startsWith("34")||number.startsWith("37"))) 
                    {
                            valid = true;
                    }
                    break;
                case JCB:
                    if(number.length()==16&&(number.startsWith("3428")||number.startsWith("3589"))) 
                    {
                            valid = true;
                    }
                    break;
                case MASTERCARD:
                    if(number.length()==16&&(number.startsWith("51")||number.startsWith("52"))) 
                    {
                            valid = true;
                    }
                    break;
                }
            return valid;	
	}
	
	
	/**
	 * If the card is valid, formats the card's number according to the card's type.
	 * AMERICANEXPRESS (15 digits): #### ###### ##### (4-6-5)
	 * VISA, JCB, MASTERCARD (16 digits): #### #### #### #### (4-4-4-4)
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", 
	 * the string value "4234 5678 9012 3456" will be returned.
	 * 
	 * if the card is AMERICANEXPRESS and has number "343456789012345", 
	 * the string value "3434 567890 12345" will be returned.
	 * 
	 * If the card information is invalid, returns empty string.
	 * 
	 * @return a string of formatted card's number
	 */
	public String getFormattedCardNumber() {
		String	spaceEvery4 = "4",
                spaceEvery5 = "5",
                spaceEvery6 = "6", 
                result = null,
                temp = "";
                temp = null;
		
		String cardNumber = getNumber();
		if(isValid()==true) 
                {
                    switch(type) 
                    {
                        case VISA:
                            result = cardNumber.replaceAll("(.{" + spaceEvery4 + "})", "$1 ").trim();
                            break;
                        case JCB:
                            result = cardNumber.replaceAll("(.{" + spaceEvery4 + "})", "$1 ").trim();
                            break;
                        case MASTERCARD:
                            result = cardNumber.replaceAll("(.{" + spaceEvery4 + "})", "$1 ").trim();
                            break;
                        case AMERICANEXPRESS:
                            for(int i = 0; i < cardNumber.length(); i++) 
                            {
                                    if(i==4||i==11) 
                                    {
                                        result = temp.concat(" ");
                                    } else 
                                    {
                                        result = temp.concat(Character.toString(cardNumber.charAt(i)));
                                    }
                            }
                            break;
                    }
		}
		return result;
	}
	
	
	//**************************** DO NOT MODIFY **********************************//
	
	/**
	 * Get the value of card's type
	 * @return a String of card's type
	 */
	public String getType() {
		return type.name();
	}
	
	
	/**
	 * Get the raw card number before formatted
	 * @return a String of card's number
	 */
	public String getNumber() {
		return this.number;
	}
	
	//*****************************************************************************//

}
