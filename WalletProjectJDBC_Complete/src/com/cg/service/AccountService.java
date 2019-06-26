package com.cg.service;

import java.util.ArrayList;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;

public class AccountService  implements Gst,Transaction,AccountOperation{ 
	AccountDAO dao=new AccountDAOImpl();

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
	public boolean updateAccount(long mobileno,String ah) {
		// TODO Auto-generated method stub
		return dao.updateAccount(mobileno,ah);
	}

	@Override
	public boolean deleteAccount(long mobileno) {
		// TODO Auto-generated method stub
		return dao.deleteAccount(mobileno);
	}

	@Override
	public Account findAccount(long mobileno) {
		// TODO Auto-generated method stub
		return dao.findAccount(mobileno);
	}

	@Override
	public Map<Long,Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return dao.getAllAccounts();
	}

	@Override
	public boolean transferMoney(long from, long to, double amount) throws InsufficientFundException {
		// TODO Auto-generated method stub
		return dao.transferMoney(from, to, amount);
	}

	@Override
	public boolean deposit(long mobileno, double amount) {
		// TODO Auto-generated method stub
		return dao.deposit(mobileno, amount);
	}

	@Override
	public boolean withdraw(long mobileno, double amount) throws InsufficientFundException {
		// TODO Auto-generated method stub
		return dao.withdraw(mobileno, amount);
	}

	public void exit() {
		// TODO Auto-generated method stub
		dao.exit();
	}
	

}