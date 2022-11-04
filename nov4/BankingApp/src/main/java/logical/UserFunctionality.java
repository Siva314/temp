package logical;


import java.util.List;
import java.util.Map;

import help.HelperException;
import pojo.AccountInfo;
import pojo.CustomerInfo;
import pojo.TransactionInfo;
import pojo.UserInfo;
import storage.DataProcess;
import storage.StorageLayer;
import util.HelperUtils;

public class UserFunctionality {
	
	StorageLayer storage=new DataProcess();
	
	AccountInfo account;
	CustomerInfo customer;
	UserInfo user;
	TransactionInfo transaction;
	Map<Long,AccountInfo> accountDetails;
	
	private boolean checkBalance(Long accountNumber,double amount) throws HelperException{
		double balance=getAccountDetails(accountNumber).getAccountBalance();
		HelperUtils.isAvailableAmount(balance, amount);
		return true;
	}
	
	public  String checkLogin(int id, String password) throws HelperException {
		user=new UserInfo();
		user.setUserId(id);
		user.setPassword(password);
		UserInfo temp = storage.validateUser(user);
		return temp.getType();
	}
	
	public Map<Long,AccountInfo> getAccounts(int userId)throws HelperException {
		user=new UserInfo();
		user.setUserId(userId);
		accountDetails=storage.getAccountDetails(user);
		return accountDetails;
	}
	
	public AccountInfo getAccountDetails(Long accountNumber) {
		account=accountDetails.get(accountNumber);
		return account;
	}
	
	public CustomerInfo getProfile(int userId) throws HelperException {
		user=new UserInfo();
		user.setUserId(userId);
		return storage.getUserDetails(user);
	}
	
//	double viewAccountBalance(Long accountNumber) throws HelperException;
	public double getTotalAccountBalance(int userId) throws HelperException {		
		return storage.getTotalBalance(user);
	}
	
	public boolean deposit(int userId,Long accountNumber,double amount) throws HelperException {
		HelperUtils.isValidAmount(amount);
		transaction=new TransactionInfo();
		transaction.setUserId(userId);
		Long milliSecond=System.currentTimeMillis();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDate(milliSecond);
		transaction.setTransactionAmount(amount);
		transaction.setSenderAccountNumber(0l);
		transaction.setReceiverAccountNumber(accountNumber);
		transaction.setTransactionType("deposit");
		return storage.makeTransaction(transaction);
	}
	
	public boolean withdraw(int userId,Long accountNumber,double amount) throws HelperException {
		HelperUtils.isValidAmount(amount);
		checkBalance(accountNumber,amount);
		transaction=new TransactionInfo();
		transaction.setUserId(userId);
		Long milliSecond=System.currentTimeMillis();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDate(milliSecond);
		transaction.setSenderAccountNumber(accountNumber);
		transaction.setReceiverAccountNumber(0l);
		transaction.setTransactionAmount(amount);
		transaction.setTransactionType("withdraw");
		return storage.makeTransaction(transaction);
	}
	
	public boolean transfer(int userId,Long accountNumber,Long receiverAccount,double amount) throws HelperException {
		String status=storage.checkAccountAvailability(receiverAccount);
		if(status.equalsIgnoreCase("inactive")) {
			throw new HelperException("Inactive Account");
		}
		HelperUtils.isValidAmount(amount);
		checkBalance(accountNumber,amount);
		transaction=new TransactionInfo();
		transaction.setUserId(userId);
		Long milliSecond=System.currentTimeMillis();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDate(milliSecond);
		transaction.setSenderAccountNumber(accountNumber);
		transaction.setReceiverAccountNumber(receiverAccount);
		transaction.setTransactionAmount(amount);
		transaction.setTransactionType("transfer");
		return storage.makeTransaction(transaction);
	}
	
	public List<TransactionInfo> getTransactionDetails(Long accountNumber) throws HelperException {
		account=new AccountInfo();
		account.setAccountNumber(accountNumber);
		return storage.getTransactionDetails(account);
	}
	
	public List<TransactionInfo> getTransactionDetailswithDate(int day) throws HelperException{
		return storage.getAllTransactionDetailsWithDate(day);
	}
	
	public boolean updateProfile(int userId,long mobile,String email)throws HelperException{
		HelperUtils.isValidEmail(email);
		HelperUtils.isValidMobile(mobile);
		user=new UserInfo();
		user.setMobile(mobile);
		user.setUserId(userId);
		user.setEmail(email);
		return storage.updateProfile(user);
	}
	
	public boolean changePassword(int userId,String oldPassword,String newPassword) throws HelperException{
		user=new UserInfo();
		user.setUserId(userId);
		user.setPassword(oldPassword);
		if(storage.changePassword(user, newPassword)) {
			throw new HelperException("Successfully changed");
		}
		return true;
	}
		
}
