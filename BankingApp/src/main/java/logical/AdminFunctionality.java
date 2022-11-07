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

public class AdminFunctionality {
	
	UserInfo userDetails=new UserInfo();
	StorageLayer storage=new DataProcess();
	
	public UserInfo getOwnProfile(int userId)throws HelperException{
		userDetails=new UserInfo();
		userDetails.setUserId(userId);
		return storage.getDetails(userDetails);
	}
	
	public CustomerInfo viewCustomerProfile(int userId) throws HelperException{
		UserInfo user=new UserInfo();
		user.setUserId(userId);
		return storage.getUserDetails(user);
	}
	
	public Map<Integer,Map<Long,AccountInfo>> viewAllAccount()throws HelperException {
		return storage.getAllAccountDetails();
	}
	
	public Map<Integer,UserInfo> viewAllUserDetails()throws HelperException {
		return storage.getAllUserDetails();
	}
	
	public List<TransactionInfo> viewTransaction(Long accountNumber) throws HelperException {
		AccountInfo account=new AccountInfo();
		account.setAccountNumber(accountNumber);
		return storage.getTransactionDetails(account);
	}
	
	public List<TransactionInfo> getTransactionDetailswithDate(int day) throws HelperException{
		return storage.getAllTransactionDetailsWithDate(day);
	}
	
	public boolean changeUserStatus(int userId,String status)throws HelperException {
		CustomerInfo customer=new CustomerInfo();
		customer.setUserId(userId);
		customer.setUserStatus(status);
		return storage.setUserStatus(customer);
	}
	
	public boolean changeAccountStatus(Long accountNumber,String status)throws HelperException {
		AccountInfo account=new AccountInfo();
		account.setAccountNumber(accountNumber);
		account.setAccountStatus(status);
		return storage.setAccountStatus(account);
	}
	public boolean updateProfile(int userId,long mobile,String email)throws HelperException{
		HelperUtils.isValidEmail(email);
		HelperUtils.isValidMobile(mobile);
		userDetails=new UserInfo();
		userDetails.setMobile(mobile);
		userDetails.setUserId(userId);
		userDetails.setEmail(email);
		return storage.updateProfile(userDetails);
	}
	
	public int createNewUser(String name,String email,long dateOfBirth,long mobile,String address,String type)throws HelperException{
		HelperUtils.isValidEmail(email);
		HelperUtils.isValidMobile(mobile);
		userDetails=new UserInfo();
		userDetails.setAddress(address);
		userDetails.setDateOfBirth(dateOfBirth);
		userDetails.setEmail(email);
		userDetails.setMobile(mobile);
		userDetails.setName(name);
		userDetails.setPassword("user@123");
		userDetails.setType(type);
		return storage.addUser(userDetails);
	}
	
	public long createNewAccount(int userId,String pancard,Long aadhaar,double amount,String accountType,String ifscCode,String branchName)throws HelperException {
		CustomerInfo customer=new CustomerInfo();
		AccountInfo account=new AccountInfo();
		customer.setUserId(userId);
		customer.setPancard(pancard);
		customer.setAadhaar(aadhaar);
		customer.setUserStatus("active");
		if(storage.insertIntoCustomer(customer)) {
			account.setAccountBalance(amount);
			account.setAccountStatus("active");
			account.setAccountType(accountType);
			account.setBranchName(branchName);
			account.setIfscCode(ifscCode);
			account.setUserId(userId);
			return storage.addAccount(account);			
		}
		else {
			throw new HelperException("Account creation falied..");
		}
	}
}