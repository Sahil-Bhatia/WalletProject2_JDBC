package com.cg.pl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.cg.bean.*;
import com.cg.exception.InsufficientFundException;
import com.cg.service.AccountService;
import com.cg.service.Gst;
import com.cg.service.Transaction;
import com.cg.service.Validator;
public class MyWallet {

	public static void main(String[] args) throws IOException {

		AccountService service=new AccountService();
		//service.printStatement(ob2); //calling default method of Transaction 
		/*double b1=0;
		try {
		 
			b1=service.withdraw(ob2, 55000.00);
			System.out.println("After Withdraw balance is: "+b1);
		}
		catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			System.err.println(e.getMessage());
			System.err.println(e);//invokes toString() method
		}
		double tax=service.calculateTax(Gst.PCT_5, b1);
		System.out.println("Gst: "+tax);

		*/
		
		
		//SavingAccount ob3=new SavingAccount(106,22262722,"Sahil",55000.00);
		//System.out.println(service.transferMoney(ob2, ob3, 10000));
		
		
		//System.out.println(ob2 instanceof SavingAccount);

		//System.out.println(ob2 instanceof Account);
		//System.out.println(ob2 instanceof Object);
		//System.out.println(ob2 instanceof String);//ERROR
		
		//Account ob3=new Account();// ABSTRACT CLASS CANNOT BE INSTANTIZED
		
		
		AccountService ser=new AccountService();
				
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice="";
		//Repeat ask again & again 
		while(true) {
		System.out.println("Menu: ");
		System.out.println("======= ");
		System.out.println("1. Create New Account ");
		System.out.println("2. Print All Accounts ");
		System.out.println("3. Withdraw Money ");
		System.out.println("4. Deposit Money ");
		System.out.println("5. Transfer Money");
		System.out.println("6. Find Account by Mobile Number: ");
		System.out.println("7. Delete Account ");
		System.out.println("8. Update Account ");
		System.out.println("9. Exit");
		System.out.println("Enter your choice: ");
		choice=br.readLine();
		
		switch(choice){
		case "1":	int id=0;
					long mb=0;
					String ah="";
					double bal=0.0;
					//Accepting & validating input for account number
					System.out.println("Enter Account No.: ");
					while(true)
					{
						String s_id=br.readLine();
						boolean ch1=Validator.validatedata(s_id, Validator.aidpattern);
						if(ch1==true) {
							try
							{
								id=Integer.parseInt(s_id);
								break;
							}catch(NumberFormatException e)
							{
								System.out.println("Account No. must be numeric. ReEnter.");
							}
						}
						else {
							System.out.println("ReEnter Account Number in 3 digits."); 
			
						}
					}//End of account number while

					//Accepting & validating input for account mobile
					System.out.println("Enter Mobile No.: ");
					while(true)
					{
						String s_mobile=br.readLine();
						boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
						if(ch1==true) {
							try
							{
								mb=Long.parseLong(s_mobile);
								break;
							}catch(NumberFormatException e)
							{
								System.out.println("Mobile No. must be numeric. ReEnter.");
							}
						}
						else {
							System.out.println("ReEnter Mobile Number in 10 digits."); 
			
						}
					}//End of Mobile number while
					
					//Accepting & validating account holder
					System.out.println("Enter Account Holder Name: "); //
					
					while(true)
					{
						ah=br.readLine();
						boolean ch1=Validator.validatedata(ah, Validator.namepattern);
						if(ch1==true) {
							break;
						}
						else {
							System.out.println("ReEnter Accountholder Name."); 
			
						}
					}//End of AccountHolder while
					
					
					
					//Accepting & validating balance
					System.out.println("Enter Initial Balance min 1000.00 : ");
					double bal3=0.0;
					while(true)
					{
						String bal2=br.readLine();
						 bal3=0.0;
						try {
							bal3=Double.parseDouble(bal2);
						}catch(NumberFormatException e) {
							System.out.println("Balance should be numeric");
						}
						if(bal3<1000.00) {
							System.out.println("Initial balance must be greater than 1000.00"); 
							
						}
						else {break;
							
						}
					}//End of AccountBalance while
					
				
				
					Account ob=new Account(id,mb,ah,bal3);
					if(ser.addAccount(ob)) {
					System.out.println("Successfully Created");}else {System.err.println("Error");}					
					break;
		case "2": 	ArrayList<Account> m1=ser.getAllAccounts();
					//Collection<Account> vc=m1.values();
					for(Account o:m1) {
							ser.printStatement(o);
							}
					break;
					
		case "3":   long mob3=0;//withdraw
					double amount_w=0.0;
					//Accepting & validating input for account mobile
					System.out.println("Enter Mobile No.: ");
					while(true)
					{
						String s_mobile=br.readLine();
						boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
						if(ch1==true) {
							try
							{
								mob3=Long.parseLong(s_mobile);
								break;
							}catch(NumberFormatException e)
							{
								System.out.println("Mobile No. must be numeric. ReEnter.");
							}
						}
						else {
							System.out.println("ReEnter Mobile Number in 10 digits."); 
			
						}
					}//End of Mobile number while
					
					
					//Account ob_w=ser.findAccount(mob3);
					System.out.println("Enter Amount to withdraw: ");
					try {
					amount_w=Double.parseDouble(br.readLine());}
					catch(Exception e) {
						System.out.println("Enter correct amount");
					}double new_bal_w=0.0;
					try {
					// new_bal_w=ser.withdraw(ob_w, amount_w);
					
					}catch(Exception e) {System.err.println(e.getMessage());}System.out.println("New Balance: "+ new_bal_w);
					break;
		case "4":   //Deposit
					long mob4=0;
					double amount_d=0.0;
					//Accepting & validating input for account mobile
					System.out.println("Enter Mobile No.: ");
					while(true)
					{
						String s_mobile=br.readLine();
						boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
						if(ch1==true) {
							try
							{
								mob4=Long.parseLong(s_mobile);
								break;
							}catch(NumberFormatException e)
							{
						System.out.println("Mobile No. must be numeric. ReEnter.");
							}
						}
						else {
					System.out.println("ReEnter Mobile Number in 10 digits."); 
	
						}
					}//End of Mobile number while
			
			
					//Account ob_d=ser.findAccount(mob4);
					//System.out.println(ob_d);
					System.out.println("Enter Amount to Deposit: ");
					try {
						amount_d=Double.parseDouble(br.readLine());}
					catch(Exception e) {
						System.out.println("Enter correct amount");
					}
					if(amount_d<0) {System.out.println("Amount cannot be negative");break;}
					//System.out.println("New Balance"+ ser.deposit(ob_d, amount_d));
					break;
					
			
		case "5":   //Transfer
					System.out.println("Enter from account mobile no.: ");
					long from_mob=Long.parseLong(br.readLine());
					System.out.println("Enter to account mobile no.: ");
					long to_mob=Long.parseLong(br.readLine());
					System.out.println("Enter Amount to transfer: ");
					double amt_trans=Double.parseDouble(br.readLine());
					//Account from_ob=ser.findAccount(from_mob);
					//Account to_ob=ser.findAccount(to_mob);
					//System.out.println(ser.transferMoney(from_ob, to_ob, amt_trans));
					break;
					
					
						
			
			
			
		case "6":   //Find Account
				long mob5=0;
				
				//Accepting & validating input for account mobile
				System.out.println("Enter Mobile No.: ");
				while(true)
				{
					String s_mobile=br.readLine();
					boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
				if(ch1==true) {
					try
					{
						mob5=Long.parseLong(s_mobile);
						break;
					}catch(NumberFormatException e)
					{
				System.out.println("Mobile No. must be numeric. ReEnter.");
					}
				}
				else {
					System.out.println("ReEnter Mobile Number in 10 digits."); 

				}
				}//End of Mobile number while
				//Account ob_find=ser.findAccount(mob5);
				//ser.printStatement(ob_find);
				break;
	
			
		case "7":   //Delete Account
			long mob_del=0;
			
			//Accepting & validating input for account mobile
			System.out.println("Enter Mobile No.: ");
			while(true)
			{
				String s_mobile=br.readLine();
				boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
			if(ch1==true) {
				try
				{
					mob_del=Long.parseLong(s_mobile);
					break;
				}catch(NumberFormatException e)
				{
			System.out.println("Mobile No. must be numeric. ReEnter.");
				}
			}
			else {
				System.out.println("ReEnter Mobile Number in 10 digits."); 

			}
			}//End of Mobile number while
			//Account ob_del=ser.findAccount(mob_del);
			//System.out.println(ob_del);
			//System.out.println(ser.deleteAccount(ob_del));
			break;
		
			
		case "8":   //update account
				long mob_upd=0;
			
			//Accepting & validating input for account mobile
			System.out.println("Enter Mobile No. for account update: ");
			while(true)
			{
				String s_mobile=br.readLine();
				boolean ch1=Validator.validatedata(s_mobile, Validator.mobilepattern);
			if(ch1==true) {
				try
				{
					mob_upd=Long.parseLong(s_mobile);
					break;
				}catch(NumberFormatException e)
				{
			System.out.println("Mobile No. must be numeric. ReEnter.");
				}
			}
			else {
				System.out.println("ReEnter Mobile Number in 10 digits."); 

			}
			}//End of Mobile number while
			//Account ob_upd=ser.findAccount(mob_upd);
				//ser.printStatement(ob_upd);
				System.out.println("Enter name to update: ");
				while(true)
				{
					ah=br.readLine();
					boolean ch1=Validator.validatedata(ah, Validator.namepattern);
					if(ch1==true) {
						break;
					}
					else {
						System.out.println("ReEnter Accountholder Name."); 
		
					}
				}//End of AccountHolder while
				//ob_upd.setAccountholder(ah);;
				//ser.updateAccount(ob_upd);
			break;
					
					
					
					
			
		case "9":   System.out.println("Exiting Program");
					System.exit(0);
					break;
		default : System.out.println("Invalid Choice!"); 
		}
		
		}//End of menu
	
		
	}

}