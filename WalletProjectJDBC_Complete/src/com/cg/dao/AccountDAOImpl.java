package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public class AccountDAOImpl implements AccountDAO {
	Connection con=null;
	
	public AccountDAOImpl(){
	try {	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("Database Connected");
			con.setAutoCommit(false);
		
	}catch(SQLException e) {
		//con.rollback();
		System.out.println(e.getMessage()+" "+e.getErrorCode()+" "+e.getSQLState());
		e.printStackTrace();
		System.exit(0);
	
	}
	}
	
	@Override
	public boolean addAccount(Account ob) {

		PreparedStatement insertSt=null;
		try {
			insertSt=con.prepareStatement("insert into account values(?,?,?,?)");

			insertSt.setInt(1, ob.getAid());
			insertSt.setLong(2,ob.getMobile());
			insertSt.setString(3,ob.getAccountholder());
			insertSt.setDouble(4, ob.getBalance());
			int i1=insertSt.executeUpdate();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}finally {
			try {
				con.commit();
				insertSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean updateAccount(long mobileno,String ah) {
		PreparedStatement updateSt=null;
	
		try {
			updateSt=con.prepareStatement("update account set accountholder=? where mobileno=?");

			
			updateSt.setLong(2,mobileno);
			updateSt.setString(1,ah);
			
			int i1=updateSt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				con.commit();
				updateSt.close();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}}
		return true;
	}

	@Override
	public boolean deleteAccount(long mobileno) {
		PreparedStatement deleteSt=null;

		try {
			deleteSt=con.prepareStatement("delete from account where mobileno=?");
			deleteSt.setLong(1,mobileno);
			int i1=deleteSt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				con.commit();
				deleteSt.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Account findAccount(long mobileno) {
		// TODO Auto-generated method stub
		PreparedStatement selectSt=null;
		
		Account ob=null;
		try {
			selectSt=con.prepareStatement("select * from account where mobileno=?");
			
			selectSt.setLong(1,mobileno);
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0 ;
			long mb1=0L;
			String ah1="";
			int aid=0;
			if(rs1!=null) {//resultset cannot be null
				if(rs1.next()) {
			
					bal1=rs1.getDouble(4);
					aid=rs1.getInt("aid");
					ah1=rs1.getString("accountholder");
					//System.out.println("Your balance is: "+bal1);
					 ob=new Account(aid,mobileno,ah1,bal1);
					return ob;
					
					
				}
				
				
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				selectSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	
	}

	@Override
	public Map<Long, Account> getAllAccounts() {
		// TODO Auto-generated method stub
		PreparedStatement selectSt=null;
		Map<Long,Account> map=new HashMap<>();
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
					
					aid=rs1.getInt("aid");
					bal1=rs1.getDouble(4);
					mb1=rs1.getLong("mobileno");
					ah1=rs1.getString("accountholder");
					
					 ob=new Account(aid,mb1,ah1,bal1);
					
					map.put(ob.getMobile(),ob);
					
				}}
				else {
					return null;
				}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				selectSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		return map;
		
	}

	@Override
	public boolean transferMoney(long from, long to, double amount) throws InsufficientFundException {
			
			Account from_ob=findAccount(from);
			Account to_ob=findAccount(to);
			double from_bal=0.0;
			double to_bal=0.0;
			if(from_ob!=null && to_ob!=null) {
				from_bal=from_ob.getBalance();
				to_bal=to_ob.getBalance();
				if(from_bal-amount<1000) {
					throw new InsufficientFundException("Insufficient Fund: Cannot Process Withdrawl",from_bal);		
				}else {
					withdraw(from,amount);
					deposit(to,amount);
					return true;
				}
			
			}
			else {
				System.out.println("Account not found");
				return false;
			}
	}

	@Override
	public boolean deposit(long mobileno, double amount) {
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		try {
			selectSt=con.prepareStatement("select * from account where mobileno=?");
			selectSt.setLong(1,mobileno);
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0 ;
			if(rs1!=null) {//resultset cannot be null
				if(rs1.next()) {
					bal1=rs1.getDouble(4);
				}

			}else {
				return false;
			}
			
			updateSt=con.prepareStatement("update account set balance=? where mobileno=?");

		
			updateSt.setLong(2,mobileno);
		
			updateSt.setDouble(1, bal1+amount);
			int i1=updateSt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				con.commit();
				updateSt.close();
				selectSt.close();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}}
		return true;
	}

	@Override
	public boolean withdraw(long mobileno, double amount) throws InsufficientFundException {
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		try {
			selectSt=con.prepareStatement("select * from account where mobileno=?");
			selectSt.setLong(1,mobileno);
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0 ;
			if(rs1!=null) {//resultset cannot be null
				if(rs1.next()) {
					bal1=rs1.getDouble(4);
				}

			}else {
				return false;
			}
			if(bal1-amount<1000) {
				System.err.println("Insufficient Fund: Cannot Process Withdrawl");
				throw new InsufficientFundException("Insufficient Fund: Cannot Process Withdrawl",bal1);	
			}else {
				updateSt=con.prepareStatement("update account set balance=? where mobileno=?");

				
				updateSt.setLong(2,mobileno);
			
				updateSt.setDouble(1, bal1-amount);
				int i1=updateSt.executeUpdate();
				
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				con.commit();
				//updateSt.close();
				selectSt.close();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}}
		return true;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
