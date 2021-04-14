/**
 * @author Phichayut    Ngoennim [6388035] >>> TASK 5
 * @author Jirayu       Klinudom [6388085] >>> TASK 4 VALIDATION SKELETAL
 * @author Perakorn     Nimitkul [6388127] >>> MAIN CONTRIBUTOR
 * Section              2
 * 
 * @status           >>>TASK 1 COMPLETED
 * @status           >>>TASK 2 COMPLETED
 * @status           >>>TASK 5 ON GOING
 * @Note             >>> import
 * 
 * @import java.io.FileNotFoundException used for error detection during try-catch in reading files
 * @import java.util.Scanner to use scanner.nextline()
 * 
 * FOR TASK 5
 * @import java.io.BufferedWriter;
 * @import java.io.FileOutputStream;
 * @import java.io.OutputStreamWriter;
 * @import java.io.OutputStream;
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class DataManagement {
	
	public static final String SAMPLE_PATH = Paths.get("").toAbsolutePath().toString() 
								+ File.separator + "sample" +  File.separator;
	
	public static final String STOCK_FILE = SAMPLE_PATH + "stocks.txt";
	public static final String CUSTOMER_FILE = SAMPLE_PATH + "customers.txt";
	public static final String WALLET_FILE = SAMPLE_PATH + "wallets.txt";
	public static final String ORDER_FILE = SAMPLE_PATH + "orders.txt";
	
	//**************************** DO NOT MODIFY **********************************//
	
	public static Map<Integer, Customer> customerData = new HashMap<Integer, Customer>();
			// The key of customerData is the customer's ID
	
	public static Map<Integer, EWallet> walletData = new HashMap<Integer, EWallet>();
			// The key of walletData is the customer's ID associated with that EWallet
	
	public static Map<String, Item> stockData = new HashMap<String, Item>();
			// The key of stockData is the item's name
	
	public static Map<Integer, Order> orderData = new HashMap<Integer, Order>();
			// The key of orderData is the order's ID
	
	//*****************************************************************************//
        
        /** * ADDITIONAL METHOD
	 * Validates input from given string, using dataType param to tell the switch
         * on which regex should the string compare to
	 * 
         * @author Perakorn Nimitkul [6388127]
	 * @param input passed from init methods
         * @param dataType tells filter what type this will be filtered for
	 * @return returnOperand according to the assigned values designated for each operation
	 */
	public static String filter(String input, String dataType)
        {
            String returnOperand = "NULL";
            switch (dataType)
            {
                case "Customer": //Customer
                { 
                    if(input.matches("^\\d+,[a-zA-z]+,\\d+\\.\\d{2}$"))
                    {
                        returnOperand = "T:Online";
                                                    //System.out.println("OP is"+returnOperand);
                    }
                    else if(input.matches("^\\d+,[a-zA-z]+$"))
                    {
                        returnOperand = "T:Offline";
                                                    //System.out.println("OP is"+returnOperand);
                    }
                    else
                    {
                        returnOperand = "BAD_TYPE";
                                                    //System.out.println("OP is"+returnOperand);
                    }
                    break;
                }
                
                case "Wallet": //Wallet
                {
                    if(input.matches("^\\d+,[A-Z]+,\\d+,-?\\d+\\.\\d{2}$"))
                    {
                        returnOperand = "T:Encodded";
                    } 
                    /*else if(input.matches("^\\d+,[A-Z]+,[a-zA-Z0-9]+,-?\\d+\\.\\d{2}$"))  //Unused but kept until proven nessecary
                    {
                        returnOperand = "T:Plain";
                    } */
                    break;
                }
                
                case "Stock": //Stock
                {
                    //System.out.println("******PROCESSING STOCK LINE******");
                    //System.out.println("STOCKLine: "+input);
                    if(input.matches("^[a-z\\s]+,\\d+\\.\\d{2},(true|false),\\d+"))
                    {
                        returnOperand = "T:Stock";
                                                    //System.out.println("+1");
                    } 
                    break;
                }
                
                case "Order": //Order
                {
                    Boolean inputVoided = false; //used to check illegal input
                    System.out.println("******PROCESSING ORDER LINE******");
                    System.out.println("ORDERLine: "+input);
                    String[] orderStringSplt = input.split(",");
                    String[] itemsStringSplt = orderStringSplt[2].split("\\|");
                    String[] paymentStringSplt = orderStringSplt[3].split("::");
                    System.out.print("ID: "+orderStringSplt[0]+"\nCUSTID: "+orderStringSplt[1]+"\nITEMS: "+orderStringSplt[2]);
                    
                    if(orderStringSplt[0].matches("\\d+") && orderStringSplt[1].matches("\\d+") && orderStringSplt.length == 4) //Validate OrderID and Cust.ID, also validate length
                    {
                        
                        System.out.println("\nIds Validated!");
                        if(orderStringSplt[2].matches("[a-z\\| ]+")) //Validate that itemString has no illegal character
                        {
                            System.out.println("ItemString Validated!");
                            for(int i = 0; i < itemsStringSplt.length; i++) //Loop validate individual items
                            {
				if(itemsStringSplt[i].matches("[^a-zA-Z ]+"))//Check if the individual items contains illegal character
                                {
                                    inputVoided = true;//if theres an invalid char, input is voided
                                    System.out.println("BADDDDD");
                                    break;
				} 
                            }
                            if(!inputVoided && orderStringSplt[3].matches("[A-Z]+::[A-Z]+")) //input is voided? if not, does the PpaymentStringSplt contains illegal char?
                            {
                                System.out.println("Status Validating...");
                                System.out.println(paymentStringSplt[0]);
                               switch(paymentStringSplt[0]) 
                               {
                                   case "VOIDED":
                                            inputVoided = false;
                                            break;
                                   case "PENDING":
                                            inputVoided = false;
                                            break;
                                   case "PAID":
                                            inputVoided = false;
                                            break;
                                   default:
                                            inputVoided = true;//BAD INPUT
                                            break;
				}
                               if(!inputVoided)// is the first index of paymentStringSplt voided? if not, keeps validating
                               {
                                   System.out.println("Status Validated! Now Type...");
                                   switch(paymentStringSplt[1]) 
                                    {
                                           case "UNKNOWN":
                                                inputVoided = false;
                                                break;
                                           case "CASH":
                                                inputVoided = false;
                                                break;
                                           case "CARD":
                                                inputVoided = false;
                                                break;
                                           case "EWALLET":
                                                inputVoided = false;
                                                break;
                                            default:
                                                inputVoided = true; //BAD INPUT
                                                break;  
                                    }
                               }
                               
                               if(!inputVoided) //Final check, if not void then this string is a legal input
                               {
                                   returnOperand = "T:Order";
                                   System.out.println("+++++LEGIT++++++");
                               }
                               else
                               {
                                   returnOperand = "BAD_INPUT";
                                   System.out.println("-------BAD-------");
                               }
                            }
                        }
                    }    
                }
                break;
                default:
                       returnOperand = "BAD_INPUT"; 
                       break;
            }
            
            return returnOperand;
        }    
	
	/**
	 * Reads data line by line from the text file.
	 * For any valid line with correct customer's format, recreates a Customer object, and put in the customerData map.
	 * The invalid line will be skipped.
	 * 
	 * Note that customer can be either general customer or an online customer.
	 * 
	 * @param filename that keeps customers data
	 * @return Map collection of customers read from the text file
	 */
	public static Map<Integer, Customer> initCustomer(String filename) 
        {
		
		//******************* YOUR CODE HERE ******************
                try 
                {
                    File inputFile = new File(filename);
                    Scanner reader = new Scanner(inputFile);
                                                                            //System.out.println("Boolean Result: "+reader.hasNextLine());    

                    while (reader.hasNextLine()) 
                    {
                        String inputData = reader.nextLine();
                        
                                                                            //System.out.println("Curr Line: "+inputData);
                        
                        String[] spltArr = inputData.split(",");
                                                                            //System.out.println("Curr Splt: "+spltArr[0]+spltArr[1]);
                        switch (filter(inputData,"Customer"))
                        {
                        //case offline customer
                            case "T:Offline":
                            {
                                customerData.put(Integer.parseInt(spltArr[0]) , new Customer(Integer.parseInt(spltArr[0]) , spltArr[1]));
                                //System.out.println("C T1 added");
                            }
                                break;
                        //case online customer
                            case "T:Online":
                            {
                                customerData.put(Integer.parseInt(spltArr[0]) , new CustomerOnline(Integer.parseInt(spltArr[0]) , spltArr[1] , Double.parseDouble(spltArr[2])));
                                //System.out.println("C T2 added");
                            }
                                break;
                            default:
                                continue;
                        }
                    }
                    reader.close();
                } 
            
                catch (FileNotFoundException e) 
                {
                    System.out.println("Customers file not found");
                    //e.printStackTrace();
                }		

               return customerData;
		
		//*****************************************************
	}
	
	/**
	 * Reads data line by line from the text file.
	 * For any valid line with correct wallet's format, recreates a EWallet object, and put in the walletData map.
	 * The invalid line will be skipped.
	 * 
	 * Note that the password in the log file must be encoded password (positive or negative number only)
	 * 
	 * @param filename that keeps e-wallet data
	 * @return Map collection of wallets read from the text file
	 */
	public static Map<Integer, EWallet> initWallet(String filename) {
		
		//******************* YOUR CODE HERE ******************
                try 
                {
                    File inputFile = new File(filename);
                    Scanner reader = new Scanner(inputFile);
                    //System.out.println("Boolean Result: "+reader.hasNextLine());    
                        
                    while (reader.hasNextLine()) 
                    {
                        String inputData = reader.nextLine();
                        String[] spltArr = inputData.split(",");
 
                        switch (filter(inputData,"Wallet"))
                        {
                            case "T:Encodded":
                            {
                                walletData.put(Integer.parseInt(spltArr[0]), new EWallet( Integer.parseInt(spltArr[0]) , spltArr[1] , Integer.parseInt(spltArr[2]) , Double.parseDouble(spltArr[3]) ) );
                            }
                            /*case "T:Plain": Unused, but kept until proven unnecessary
                            {
                                walletData.put(Integer.parseInt(spltArr[0]), new EWallet( Integer.parseInt(spltArr[0]) , spltArr[1] , spltArr[2] , Double.parseDouble(spltArr[3]) ) );
                            }*/
                            break;
                            default:
                                continue;
                        }
                    }
                    reader.close();
                } 
            
                catch (FileNotFoundException e) 
                {
                    System.out.println("Wallet file not found");
                    //e.printStackTrace();
                }		
		return walletData;
		
		//*****************************************************

	}
	
	
	/**
	 * Reads data line by line from the text file.
	 * For any valid line with correct item's format, recreates an Item object, and put in the stockData map.
	 * The invalid line will be skipped.
	 * 
	 * @param filename that keeps stocks data
	 * @return Map collection of items read from the text file
	 */
	public static Map<String, Item> initStock(String filename){
		
		//******************* YOUR CODE HERE ******************
		try 
                {
                    File inputFile = new File(filename);
                    Scanner reader = new Scanner(inputFile);
                    //System.out.println("Boolean Result: "+reader.hasNextLine());    

                    while (reader.hasNextLine()) 
                    {
                        String inputData = reader.nextLine();
                        //System.out.println("THIS IS STOCK: "+inputData);
                        String[] spltArr = inputData.split(",");
                        
                        switch (filter(inputData,"Stock"))
                        {
                            case "T:Stock":
                            {
                                stockData.put(spltArr[0], new Item(spltArr[0] , Double.parseDouble(spltArr[1]) , Boolean.parseBoolean(spltArr[2]) , Integer.parseInt(spltArr[3]) ) );
                            }
                            break;
                            default:
                                continue;
                        }
                    }
                    reader.close();
                } 
            
                catch (FileNotFoundException e) 
                {
                    System.out.println("Stock file not found");
                    //e.printStackTrace();
                }		
                
		return stockData;
		
		//*****************************************************
		
	}
	
	/**
	 * Reads data line by line from the text file.
	 * For any valid line with correct order's format, recreates an Order object, and put in the orderData map.
	 * The invalid line will be skipped.
	 * 
	 * Note that if the qty is equal to 2147483647, this mean the item has unlimited supply
	 * 
	 * @param filename that keeps orders data
	 * @return Map collection of orders read from the text file
	 */
	
	public static Map<Integer, Order> initOrder(String filename) 
        {
            //******************* YOUR CODE HERE ******************
            try 
            {
                File inputFile = new File(filename);
                Scanner reader = new Scanner(inputFile);
                //System.out.println("Boolean Result: "+reader.hasNextLine());    

                while (reader.hasNextLine()) 
               {
                    String inputData = reader.nextLine();
                    
                    String[] orderStringSplt = inputData.split(",");
                    String[] itemsStringSplt = orderStringSplt[2].split("\\|");
                    String[] paymentStringSplt = orderStringSplt[3].split("::");
                    
                    System.out.println("THIS IS ORDER: "+inputData);
                    switch (filter(inputData,"Order"))
                        {
                            case "T:Order":
                            {
                                
                                orderData.put(Integer.parseInt(orderStringSplt[0]), new Order(Integer.parseInt(orderStringSplt[0])));
                                orderData.get(Integer.parseInt(orderStringSplt[0])).setCustomerID(Integer.parseInt(orderStringSplt[1]));
                                for(String item: itemsStringSplt)
                                {
                                    orderData.get(Integer.parseInt(orderStringSplt[0])).addItem(item,true);
                                }
                            }
                            break;
                            default:
                                continue;
                        }
                }
                reader.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("Stock file not found");
                //e.printStackTrace();
            }		
            return orderData;

            //*****************************************************
		
	}
	
	//**************************** DO NOT MODIFY **********************************//
	/**
	 * Initialize customerData, stockData, walletData, and orderData by calling initCustomer, initStock, initWallet, and initOrder respectively
	 * 
	 */
	public static void initData(){
		initStock(STOCK_FILE); 
		initCustomer(CUSTOMER_FILE);
		initWallet(WALLET_FILE);
		initOrder(ORDER_FILE);
	}

	
	/**
	 * Delete existing log files if any
	 */
	public static void removeLogFile(String filename) {
		
		try {
			File log = new File(filename);
			Files.deleteIfExists(log.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A convenient method to write str to the File identified by filename. 
	 * Only works with newer version of Java.
	 * @param filename
	 * @param append
	 * @param str
	 */
	public static void writeDataLog(String filename, Boolean append, String str)
	{
		try {
			FileWriter fileWriter = new FileWriter(filename, append);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.println(str); 
		    printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
        //*****************************************************************************//
        //*****************************************************************************//
        //*****************************************************************************//
        /**
         * EZPringLog was made to facilitate the process of log printing, see param below...
         * Rather than have every store.. method have their printing, we put all the printing 
         * right in this method.
         * same input parameter as all the store.. method but list is arraylist of string extracted
         * from each store.. method.
         * @author Phichayut    Ngoennim [6388035]
         * @param filename
         * @param append
         * @param list 
         */
	public static void EZPrintLog(String filename, Boolean append, ArrayList<String> list)
        {
            File file = new File(filename);
            FileWriter fWrite = null;
            try
            {
                fWrite = new FileWriter(file, append);
                for(String l: list)
                {
                    fWrite.write(l);
                    fWrite.write(System.getProperty( "line.separator" ));
                }
                fWrite.close();
            }
            catch(IOException e)
            {
                System.out.println("IO exception lmao");
            }
        }
	/**
	 * Write the list of items into the given text file
	 * If the append is true, the list of items will be appended into the existing log file
	 * Otherwise, the content in the existing log file will be replaced with this new list of items.
	 * 
	 * @param filename
	 * @param append: whether append the text file or not
	 * @param items: list of items
	 */
	public static void storeItems(String filename, Boolean append, List<Item> items) 
        {
            ArrayList<String> owo = new ArrayList<>();
            items.forEach(temp -> {
                owo.add(temp.log());
            });
            EZPrintLog(filename,append,owo);
	}
	
	/**
	 * Write the list of customers into the given text file
	 * If the append is true, the list of customers will be appended into the existing log file
	 * Otherwise, the content in the existing log file will be replaced with this new list of customers.
	 *   
	 * @param filename
	 * @param append
	 * @param customers
	 */
	public static void storeCustomers(String filename, Boolean append, List<Customer> customers)
        {
            ArrayList<String> owo = new ArrayList<>();
            customers.forEach(temp -> {
                owo.add(temp.log());
            });
            EZPrintLog(filename,append,owo);   
	}
	
	/**
	 * Write the list of wallets into the given text file
	 * If the append is true, the list of wallets will be appended into the existing log file
	 * Otherwise, the content in the existing log file will be replaced with this new list of wallets.
	 * 
	 * @param filename
	 * @param append
	 * @param wallets
	 */
	public static void storeEWallets(String filename, Boolean append, List<EWallet> wallets) 
        {
            ArrayList<String> owo = new ArrayList<>();
            wallets.forEach(temp -> {
                owo.add(temp.log());
            });
            EZPrintLog(filename,append,owo);
	}
	
	/**
	 * Write the list of orders into the given text file
	 * If the append is true, the list of orders will be appended into the existing log file
	 * Otherwise, the content in the existing log file will be replaced with this new list of orders.
	 * 
         * @param filename
	 * @param append
	 * @param orders
	 */
	
	public static void storeOrders(String filename, Boolean append, List<Order> orders) 
        {
            ArrayList<String> owo = new ArrayList<>();
            orders.forEach(temp -> {
                owo.add(temp.log());
            });
            EZPrintLog(filename,append,owo);
	}
        
        //*****************************************************************************//
        //*****************************************************************************//
        //*****************************************************************************//
        
	/**
	 * Filter the orders data (in the Map Collection) by the order's status and/or payment method
	 * and calculate the summation of the sub-total amount of those filtered ordered.
	 * 
         * @author Phichayut N. [6388035]
         * 
	 * @param status
	 * @param method
	 * @return total summation of the sub-total of the orders matched with the given condition
	 */
	public static double filterSubTotal(Order.Status status, String method) 
        {
            //******************* YOUR CODE HERE ******************
            double subtot = 0.0;
            for(Integer key: DataManagement.orderData.keySet())
            {
                if(DataManagement.orderData.get(key).getPaymentStatus() == status 
                        && DataManagement.orderData.get(key).getPaymentMethod().equals(method))
                {
                    subtot += DataManagement.orderData.get(key).calSubTotal();
                }
            }
            
            return subtot;
            //*****************************************************
		
        }
	
	/**
	 * Filter the orders data (in the Map Collection) by the order's status and/or payment method
	 * and calculate the summation of the grand total amount of those filtered ordered.
	 * 
         * @author Phichayut N. [6388035]
         * 
	 * @param status
	 * @param method
	 * @return total summation of the grand total of the orders matched with the given condition
	 */
	
	public static double filterGrandTotal(Order.Status status, String method) 
        {
            //******************* YOUR CODE HERE ******************
            double subtot = 0.0;
            for(Integer key: DataManagement.orderData.keySet())
            {
                if(DataManagement.orderData.get(key).getPaymentStatus() == status 
                        && DataManagement.orderData.get(key).getPaymentMethod().equals(method))
                {
                    subtot += DataManagement.orderData.get(key).calGrandTotal();
                }
            }
            
            return subtot;
            //*****************************************************
				
	}
	
	/**
	 * Calculate the grand total payment group by each payment method for all paid ordered
	 * The voided or pending orders are ignored
	 * 
         * @author Phichayut N. [6388035]
         * 
	 * @return Map<String, Double> where key is the method name, and value is the total of grand total payment
	 */
	public static Map<String, Double> groupGrandTotalByPaymentMethod()
        {
            //******************* YOUR CODE HERE ******************
            HashMap<String, Double> uwu = new HashMap<>();
            double _cash = 0.0, _card = 0.0, _ewal = 0.0;
            for(Integer key: DataManagement.orderData.keySet())
            {
                if(DataManagement.orderData.get(key).getPaymentStatus() == Order.Status.PAID)
                {
                    switch(DataManagement.orderData.get(key).getPaymentMethod())
                    {
                        case "CASH":
                                    _cash += DataManagement.orderData.get(key).calGrandTotal();
                            break;
                        case "CARD":
                                    _card += DataManagement.orderData.get(key).calGrandTotal();
                            break;
                        case "EWALLET":
                                    _ewal += DataManagement.orderData.get(key).calGrandTotal();
                            break;
                    }
                }
            }
            uwu.put("CASH",_cash);
            uwu.put("CARD",_card);
            uwu.put("EWALLET",_ewal);
            return uwu;
            //*****************************************************
				
	}
	
	/**
	 * Sort the orders data (in Map Collection) by their grandTotal
	 * If asc is true, the grandTotal is sorted in ascending order
	 * Otherwise, the grandTotal is sorted in descending order 
	 * Only "paid" orders are included in the output
	 * If the amounts are equal, the order ID will be used later.
	 * 
         * @author Phichayut N. [6388035]
         * 
	 * @param asc indicate ascending or descending order
	 * @return the sorted list of orders
	 */
	public static ArrayList<Order> sortPaidGrandTotal(boolean asc) 
        {
            //******************* YOUR CODE HERE ******************
            ArrayList<Order> unsort = new ArrayList<>();
            int k = 0;
            for(Integer key: DataManagement.orderData.keySet())
            {
                if(DataManagement.orderData.get(key).getPaymentStatus() == Order.Status.PAID)
                {
                    unsort.add(DataManagement.orderData.get(key));
                }
            }
            Order[] unsortA = new Order[unsort.size()];
            for(Order a: unsort)
            {
                unsortA[k] = a;
                k++;
            }
            
            ArrayList<Order> sorted = new ArrayList<>();
            
            int n = unsortA.length;  
                Order temp = null;  
                for(int i=0; i < n; i++)
                {  
                    for(int j=1; j < (n-i); j++)
                    {  
                        if(unsortA[j-1].calGrandTotal() > unsortA[j].calGrandTotal())
                        {  
                            //swap elements  
                            temp = unsortA[j-1];  
                            unsortA[j-1] = unsortA[j];  
                            unsortA[j] = temp;  
                        }            
                    }  
                }
            
            if(asc)//min to max
            {
                for(int l = 0 ; l < n ; l++)
                {
                    sorted.add(unsortA[l]);
                }
            }
            else//max to min
            {
                for(int l = n ; l > -1  ; l--)
                {
                    sorted.add(unsortA[l]);
                }
            }
            return sorted;
            //*****************************************************
	}

}
