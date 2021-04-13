/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>TASK 5 COMPLETED
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 */

public class CustomerOnline extends Customer{
	
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
        
        /**
         * 
         * @return string containing info of an online customer
         */
        @Override
        public String log()
        {
            String dist = df.format(this.distance);
            return super.getCustID() + "," + super.getName() + "," + dist;
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
