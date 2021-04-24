# https://github.com/Silberweich/MUICT_OOP_P02
*PASSED ALL TEST CASE PROJECT CONCLUDED && CHALLENGE CONCLUDED*. As for Instructor, you can read about what we have done below.  
This project was done by 3 person
```java
/**
 * @author Phichayut    Ngoennim [6388035] >>> Code Skeleton and implementation
 * @author Jirayu       Klinudom [6388085] >>> Algorithm writer and Debug man
 * @author Perakorn     Nimitkul [6388127] >>> Implementation and Debug man
 * /
```
---
## Work Check List and Task Explaination
- [x] Follow the submission and coding style guideline 5%  
- [x] On-time Submission 5%  
> This two above should be easy right? submitting on time and doing it by the guideline.
- [x] Task 1: Implements classes 20%      -> Item, Customer, CustomerOnline, EWallet, CreditCard, Payment and related classes
> Task 1 was mainly done by Phichayut N. [6388035], this one is pretty easy, a foundation for harder code later on
- [x] Task 2: DataManagement.java 20%     -> initCustomer, initStock, initWallet
> Task 2 was mainly done by Perakorn N. [6388127], Reading from existing file was quite easy
- [x] Task 3: Implement Order class 10%
> Task 3 was mainly implemented by Phichayut N. [6388035], as a basecode, on the "makePayment" method, phichayut N. have a brain meltdown.
> Debugging was done by Perakorn N. [6388127] and Jirayu K. [6388085], the debug was done also to fix future Tasks.
- [x] Task 4: DataManagement.java -> initOrder 10%
> Task 4 was mainly done by Perakorn N. [6388127], this is where the Task 3 Order.java was fixed to work with future method
> Jirayu K. [6388085] was also involved with the debugging of this specific task.
- [x] Task 5: Implement Loggable Interface and write to log files 10%
> Task 5 was mainly implemented by Phichayut N. [6388035], adding some small extra method to streamline the work on logging data into .txt file
> The mistake on Order.java was found while doing Task 5, and was debugged by both Perakorn N. [6388127], and Jirayu K. [6388085].
- [x] Task 6: Analyzing Data 20%
> Task 6 was mainly implemented by Phichayut N. [6388035], the calculation and filtering was a piece of cake. And again debugging was done by Jirayu K. [6388085]
- [ ] Task 7: Challenge Task, Do some creative things?
> No one spent time on this yet.
---
## Some Explaination
First of all, we should note that many of our comments and explaination was done inside the javadoc in each and every java file. Many of our file will include a header like this below:
```java
/**
 * @author Phichayut    Ngoennim [6388035] >>> MAIN CONTRIBUTOR
 * @author Jirayu       Klinudom [6388085] >>> DEBUG AND CHECK
 * @author Perakorn     Nimitkul [6388127] >>> DEBUG CLEAN-UP AND DOCUMENTATION
 * Section              2
 * 
 * @status           >>>TASK 3 COMPLETED
 *                   >>>TASK 5 COMPLETED
 * 
 * @note             >>>Minor return bug during makePayment method, fixed by changing return value from enumerator Status
 *                      to return this.paymentStatus that has been modified in switch case instead
 */
```
as seen here, you can see the name, completion status, and some small note included at the top of each and every file in this format. As for code comment, apart from existing comment that was given by the instructor, in some method we have added our own, most of them should have the contributor name included as @author.
```java
 /**
   * EZPringLog was made to facilitate the process of log printing, see param below...
   * Rather than have every store.. method have their printing, we put all the printing 
   * right in this method.
   * same input parameter as all the store.. method but list is arraylist of string extracted
   * from each store.. method.
   * 
   * OH NO I FORGOT TO LOOK ABOVE, THERE IS A METHOD TO EASILY WRITE LOG
   * IM SO STUPID
   * ACTUALLY, MINE IS COOL, I PRINT ARRAY LIST OF STRING
   * 
   * @author Phichayut    Ngoennim [6388035]
   * 
   * @param filename
   * @param append
   * @param list 
   */
   public static void EZPrintLog(String filename, Boolean append, ArrayList<String> list)
   {
   ...
   }
```
---
## Challenge Task
The explaination can also be found on EWallet.java, ***line 108-155.***
> A useful function that we use on our system is "Password Reset for Ewallet User", very useful for a person that want to change their password constantly. 
> This Function finds the Customer id and take the old password for verification to be reset with a new password read the .txt file and verify password before resetting.
> After this, the return value can be used (and is used) to reassign password in EWallet.Java , then it *should* overwrite the log file.  

The Method `public static String filter(String input, String dataType)` in DataManagement.java, can also be considered an extra useful method. ***Line 61-214***
> This method is a collective filtering method that is use to facilitate the existing function that is: 
> + `public static Map<Integer, Customer> initCustomer(String filename)`
> + `public static Map<Integer, EWallet> initWallet(String filename)`
> + `public static Map<String, Item> initStock(String filename)`
> + `public static Map<Integer, Order> initOrder(String filename)`

