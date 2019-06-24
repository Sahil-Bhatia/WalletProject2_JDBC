package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cg.bean.Account;

public class AccountDAOImpl implements AccountDAO {
static	Connection con=null;
	
	//public static Map<Long,Account> accmap=new HashMap<Long,Account>();
	
	@Override
	public boolean addAccount(Account ob) {
		// TODO Auto-generated method stub
		getConnection();
		PreparedStatement updateSt=null;
		//accmap.put(ob.getMobile(), ob);	
		try {
			updateSt=con.prepareStatement("insert into account values(?,?,?,?)");

			updateSt.setInt(1, ob.getAid());
			updateSt.setLong(2,ob.getMobile());
			updateSt.setString(3,ob.getAccountholder());
			updateSt.setDouble(4, ob.getBalance());
			int i1=updateSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		
		
	}

	@Override
	public boolean updateAccount(Account ob) {
		PreparedStatement updateSt=null;
		//accmap.put(ob.getMobile(), ob);	
		try {
			updateSt=con.prepareStatement("update account set mobileno=?, accountholder=?,balance=? where aid=?");

			updateSt.setInt(4, ob.getAid());
			updateSt.setLong(1,ob.getMobile());
			updateSt.setString(2,ob.getAccountholder());
			updateSt.setDouble(3, ob.getBalance());
			int i1=updateSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAccount(int aid) {
		PreparedStatement updateSt=null;
		//accmap.put(ob.getMobile(), ob);	
		try {
			updateSt=con.prepareStatement("delete from account where aid=?");

			updateSt.setInt(1, aid);
//			updateSt.setLong(2,ob.getMobile());
//			updateSt.setString(3,ob.getAccountholder());
//			updateSt.setDouble(4, ob.getBalance());
			int i1=updateSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account findAccount(int aid) {
		// TODO Auto-generated method stub
		PreparedStatement selectSt=null;
		
		Account ob=null;
		try {
			selectSt=con.prepareStatement("select * from account where aid=?");
			
			selectSt.setInt(1, aid);
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0 ;
			long mb1=0L;
			String ah1="";
			if(rs1!=null) {//resultset cannot be null
				if(rs1.next()) {
					//System.out.println(rs1.getString(3));
					bal1=rs1.getDouble(4);
					mb1=rs1.getLong("mobileno");
					ah1=rs1.getString("accountholder");
					//System.out.println("Your balance is: "+bal1);
					 ob=new Account(aid,mb1,ah1,bal1);
					return ob;
					
					
				}
				else {
					return null;
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return ob;
		
		
		
	}

	@Override
	public ArrayList< Account> getAllAccounts() {
		// TODO Auto-generated method stub
		PreparedStatement selectSt=null;
		ArrayList<Account>al=new ArrayList<>();
		Account ob=null;
		try {
			selectSt=con.prepareStatement("select * from account ");
			
			ResultSet rs1=selectSt.executeQuery();
			int aid=0;
			double bal1=0.0 ;
			long mb1=0L;
			String ah1="";
			if(rs1!=null) {//resultset cannot be null
				while(rs1.next()) {
					//System.out.println(rs1.getString(3));
					aid=rs1.getInt("aid");
					bal1=rs1.getDouble(4);
					mb1=rs1.getLong("mobileno");
					ah1=rs1.getString("accountholder");
					//System.out.println("Your balance is: "+bal1);
					 ob=new Account(aid,mb1,ah1,bal1);
					
					al.add(ob);
					
				}}
				else {
					return null;
				}
				
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return al;
		
	}

	@Override
	public String transferMoney(Account from, Account to, double amount) {
		// TODO Auto-generated method stub
		
		double from_new_balance=from.getBalance()-amount;
		if(from_new_balance<1000.00) {
			from_new_balance=from.getBalance();
			//System.out.println("Insufficient Balance");
			//from.setBalance(new_balance);
			return "Amount cannot be transfered insufficient balance";
		}
		from.setBalance(from_new_balance);
		double b2=to.getBalance()+amount;
		to.setBalance(b2);
		String ans="From Account: "+from.getAid()+" Balance: "+from.getBalance()+"\n"+"To Account: "+to.getAid()+" Balance "+to.getBalance();
		
		return ans;
	}

	public static boolean getConnection() {
		// TODO Auto-generated method stub
		
		
		try {
			
			
			//Load the driver
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Java 1.8 automatically loads the driver
			//No need to write Class.forName or DriverManager.registerDriver
				
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connected");
			con.setAutoCommit(false);//tells that do not commit after every dml statement
			//Java application automatically commits so setAutoCommit false
		return true;
	}catch(SQLException e) {
		//con.rollback();
		System.out.println(e.getMessage()+" "+e.getErrorCode()+" "+e.getSQLState());
		e.printStackTrace();
		return false;
	}

}}
