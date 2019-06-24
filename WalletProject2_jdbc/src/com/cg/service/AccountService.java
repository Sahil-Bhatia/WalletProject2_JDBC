package com.cg.service;

import java.util.ArrayList;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;

public class AccountService  implements Gst,Transaction,AccountOperation{ //Alternate to multiple inheritance

	AccountDAO dao=new AccountDAOImpl();
	/*
	@Override
	public double withdraw(Account ob, double amount) throws InsufficientFundException {
		// TODO Auto-generated method stub
		Account ob1=AccountDAOImpl.accmap.get(ob.getMobile());
		double bal=ob1.getBalance();
		
		double new_balance=bal-amount;
		if(new_balance<1000.00) {
					new_balance=ob1.getBalance();
					//System.out.println("Insufficient Balance");
					
					throw new InsufficientFundException("Insufficient Fund: Cannot Process Withdrawl",new_balance);}
				ob1.setBalance(new_balance);
				boolean r=dao.updateAccount(ob1);
				return new_balance;
	}

	@Override
	public double deposit(Account ob, double amount) {
		// TODO Auto-generated method stub
		Account ob1=AccountDAOImpl.accmap.get(ob.getMobile());
		double bal=ob1.getBalance();
		double new_balance=bal+amount;
		ob1.setBalance(new_balance);
		dao.updateAccount(ob1);
		return new_balance;
	}

	@Override
	public String transferMoney(Account from, Account to, double amount) {// INCOMPLETE
		// TODO Auto-generated method stub
		return dao.transferMoney(from, to, amount);
	}
*/
	@Override
	public double calculateTax(double PCT, double amount) {
		// TODO Auto-generated method stub
		return amount*Gst.PCT_5;
	}

	@Override
	public boolean addAccount(Account ob) {
		// TODO Auto-generated method stub
		return dao.addAccount(ob);
	}

	@Override
	public boolean updateAccount(Account ob) {
		// TODO Auto-generated method stub
		return dao.updateAccount(ob);
	}

	@Override
	public boolean deleteAccount(int aid) {
		// TODO Auto-generated method stub
		return dao.deleteAccount(aid);
	}

	@Override
	public Account findAccount(int aid) {
		// TODO Auto-generated method stub
		return dao.findAccount(aid);
	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return dao.getAllAccounts();
	}

}