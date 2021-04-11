/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 */

public class CustomerOnline extends Customer {
	
	//**************************** DO NOT MODIFY **********************************//
	private double distance;	// delivery distance
	
	//*****************************************************************************//
	
	/**
	 * Constructor initializes customer's name and delivery distance
	 * @param name
	 * @param distance
	 */
	public CustomerOnline(String name, double distance) 
        {
            super(name);
            this.distance = distance;
	}
	
	/**
	 * Constructor initializes customer's ID, name and delivery distance
	 * @param id
	 * @param name
	 * @param distance
	 */
	public CustomerOnline(int id, String name, double distance) 
        {
            super(id,name);
            this.distance = distance;
	}
	
	//**************************** DO NOT MODIFY **********************************//
	public double getDistance() {
		return this.distance;
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + distance;
	}
	//*****************************************************************************//
	
	
}
