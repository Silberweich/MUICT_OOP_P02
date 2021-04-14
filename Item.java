/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085]
 * @author Perakorn     Nimitkul [6388127]
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 *                   >>>TASK 5 COMPLETED
 *                   >>>PROJECT CONCLUDED
 * 
 * @Note             >>>Nothing much to say here just followed the instruction
 *                      and run the test case successfully
 *                   >>>Not much fixing to be made on task 5
 */

public class Item implements Loggable{
	
	//**************************** DO NOT MODIFY **********************************//
	private String name;				// item's name
	private double price;				// item's price
	private boolean taxable = false;		// default value is false (non-taxable)
	private int qty = Integer.MAX_VALUE;	// default value is unlimited supply (max_value)
	//*****************************************************************************//
        
        /**
	 * Constructor to initialize name, price
	 * @param name
	 * @param price
	 */
	public Item(String name, double price)
        {
            this.name = name;
            this.price = price;
        }
	/**
	 * Constructor to initialize name, price, and taxable value
	 * @param name
	 * @param price
	 * @param taxable
	 */
	public Item(String name, double price, boolean taxable)
        {
            this.name = name;
            this.price = price;
            this.taxable = taxable;
	}
        /**
	 * Constructor to initialize, price, taxable, and remaining quantity in the stock  
	 * @param name
	 * @param price
	 * @param taxable
	 * @param qty
	 */
	public Item(String name, double price, boolean taxable, int qty)
        {
            this.name = name;
            this.price = price;
            this.taxable = taxable;
            this.qty = qty;   
	}
	
        //*****************************************************************************//
	/**
	 * Return true if the quantity of the item is larger than 0
	 * @return
	 */
	public boolean isAvailable()
        {
            return this.qty > 0;
	}
	
	/**
	 * Decrease the quantity by one
	 * @return remaining quantity in the stock
	 */
	public int sold()
        {
            this.qty -= 1;
            return this.qty;	
	}
	
	/**
	 * Increase the quantity by one
	 * @return remaining quantity in the stock
	 */
	public int restock() 
        {
            this.qty += 1;
            return this.qty;
	}
        
        /**
         *
         * @return string of completed data
         */
        @Override
        public String log()
        {
            String credits = df.format(this.price);
            return this.name + "," + credits + "," + this.taxable + "," + this.qty;
        }
	
	
	//**************************** DO NOT MODIFY **********************************//
	public String getName(){
		return name;
	}
	
	public double getPrice(){
		return price;
	}
	
	public boolean getTaxable(){
		return taxable;
	}
	
	public int getQuantity(){
		return qty;
	}
	
	@Override
	public String toString(){
		return "name::" + name 
				+ ",price::" + price 
				+ ",taxable::" + taxable
				+ ",qty::" + qty;
	}
	//*****************************************************************************//
	

}
