package com.cg.dao;

import java.util.*;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public interface AccountDAO {

	//public boolean getConnection();
	public boolean addAccount(Account ob);
	public boolean updateAccount(long mobileno,String ah);
	public boolean deleteAccount(long mobileno);
	public Account findAccount(long mobileno);
	public Map<Long,Account> getAllAccounts();
	public boolean transferMoney(long from, long to, double amount) throws InsufficientFundException;
	public boolean deposit(long mobileno,double amount);
	public boolean withdraw(long mobileno,double amount) throws InsufficientFundException;
	public void exit();
	
	
	
	
}
