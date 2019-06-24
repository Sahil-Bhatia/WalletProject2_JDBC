package com.cg.dao;

import java.util.*;

import com.cg.bean.Account;

public interface AccountDAO {

	//public boolean getConnection();
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(int aid);
	public Account findAccount(int aid);
	public ArrayList<Account> getAllAccounts();
	public String transferMoney(Account from, Account to, double amount);
	
	
	
	
}
