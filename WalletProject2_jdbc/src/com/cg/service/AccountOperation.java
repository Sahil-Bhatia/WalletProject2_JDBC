package com.cg.service;

import java.util.*;

import com.cg.bean.Account;

public interface AccountOperation {
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(int aid);
	public Account findAccount(int aid);
	public ArrayList<Account> getAllAccounts();
}
