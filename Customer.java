/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085] >>> DEBUG AND CHECK
 * @author Perakorn     Nimitkul [6388127] >>> DEBUG CLEAN-UP AND DOCUMENTATION
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>TASK 5 COMPLETED
 *                   >>>PROJECT CONCLUDED
 *                   >>>CHALLENGE CONCLUDED
 * 
 * @Note             >>>Small Confusion on runningID assignment, fixed the issue
 *                   >>>TASK 5 Debug: line 53: changed this.custID = Customer.runningID; to this.custID = id; 
 *                      Wrong ID assignment results in wrong ID (runningID instead of real ID) of customer instantiated by using Customer(int id, String name) 
 */

public class Customer implements Loggable{
	
	//**************************** DO NOT MODIFY **********************************//
	public static int runningID = 0;	// static variable for assigning a unique ID to a customer

	private int custID;                     // customer's ID
	private String name;                    // customer's name
	//*****************************************************************************//
	
	/**
	 * Constructor initializes the customer's name.
	 * The customer's ID will be automatically assigned according to the running ID. 
	 * The first customer will have ID as 1, and the second customer will have ID as 2, and so on
	 * @param name
	 */
	public Customer(String name) 
        {
            this.name = name;
            //normal runningID assignment
            Customer.runningID++;
            this.custID = runningID;  
	}
	
	/**
	 * Constructor initializes both customer's ID and name.
	 * If the provided ID is larger than the running ID, then the running ID will be set to equal to that provided ID. 
	 * @param id
	 * @param name
	 */
	public Customer(int id, String name) 
        {
            this.name = name;
            //special case ID assigment
            if(id > Customer.runningID)
            {
                Customer.runningID = id;
                this.custID = Customer.runningID;
            }
            else
            {
                this.custID = id; //+FIXED: Wrong value assignment: corrected running ID to the param id
            }
	}
       
        /**
         * 
         * @return string containing info of non online customer 
         */
        @Override
        public String log()
        {
            return this.custID + "," + this.name;
        }
	
	//**************************** DO NOT MODIFY **********************************//
	public int getCustID() {
		return this.custID;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "CID::" + custID + ",name::" + name;
	}

	public void print() {
		System.out.printf("%-15s %-4d \n", "Customer ID:", custID);
		System.out.printf("%-15s %-4d \n", "Name:", name);
	}
	//*****************************************************************************//
	
}
