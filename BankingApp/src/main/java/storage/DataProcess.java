package storage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import help.HelperException;
import pojo.AccountInfo;
import pojo.CustomerInfo;
import pojo.TransactionInfo;
import pojo.UserInfo;
import util.HelperUtils;

public class DataProcess implements StorageLayer {

	private Connection getConnection() throws HelperException {
		String url = "jdbc:mysql://localhost/BankingApplication";
		String uName = "siva";
		String pass = "SIVA@314";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, uName, pass);
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return conn;
	}
	
	private Map<Integer, UserInfo> storeDetailsInMapForUser(ResultSet tempSet)
			throws HelperException {
		try (ResultSet resultSet = tempSet) {
			Map<Integer, UserInfo> innerMap = new HashMap<Integer, UserInfo>();
			int userId = 0;
			while (resultSet.next()) {
				UserInfo customer = new UserInfo();
				userId = resultSet.getInt("userid");
				customer.setUserId(userId);
				customer.setAddress(resultSet.getString("address"));
				customer.setDateOfBirth(resultSet.getLong("dateofBirth"));
				customer.setEmail(resultSet.getString("email"));
				customer.setMobile(resultSet.getLong("mobile"));
				customer.setName(resultSet.getString("name"));
				customer.setPassword(resultSet.getString("password"));
				customer.setType(resultSet.getString("type"));
				innerMap.put(userId, customer);
			}
			return innerMap;
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	private boolean updateAccountBalance(long account_number, double balance) throws HelperException {
		String query2 = "update AccountInfo set account_balance=? where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query2)) {
			statement.setDouble(1, balance);
			statement.setLong(2, account_number);
			if (statement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return false;
	}

	private int getUserId(long accountNumber) throws HelperException {
		int id = 0;
		String query = "select userid from AccountInfo where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					id = resultSet.getInt("userid");
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return id;
	}

	private List<TransactionInfo> setValuesInTransactionPojo(ResultSet tempSet) throws HelperException {
		List<TransactionInfo> transactionList = new ArrayList<TransactionInfo>();
		try (ResultSet resultSet = tempSet) {
			while (resultSet.next()) {
				TransactionInfo transaction = new TransactionInfo();
				Long id = resultSet.getLong("transactionid");
				transaction.setTransactionId(id);
				transaction.setAccountNumber(resultSet.getLong("account_number"));
				transaction.setCurrentBalance(resultSet.getDouble("current_balance"));
				transaction.setReceiverAccountNumber(resultSet.getLong("receiver_account_number"));
				transaction.setSenderAccountNumber(resultSet.getLong("sender_account_number"));
				transaction.setTransactionAmount(resultSet.getDouble("transaction_amount"));
				transaction.setTransactionType(resultSet.getString("transaction_type"));
				transaction.setUserId(resultSet.getInt("userid"));
				transaction.setTransactionDate(resultSet.getLong("transaction_date"));
				transactionList.add(transaction);
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return transactionList;
	}

	private boolean insertIntoTransactionTable(TransactionInfo transaction) throws HelperException {
		long sender = transaction.getSenderAccountNumber(), receiver = transaction.getReceiverAccountNumber();
		String type = transaction.getTransactionType();
		String query = "insert into TransactionInfo (userid,account_number,transaction_date,sender_account_number,"
				+ "receiver_account_number,transaction_amount,current_balance,transaction_type) values(?,?,?,?,?,?,?,?)";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, transaction.getUserId());
			statement.setLong(2, transaction.getAccountNumber());
			statement.setLong(3, transaction.getTransactionDate());
			statement.setLong(4, sender);
			statement.setLong(5, receiver);
			statement.setDouble(6, transaction.getTransactionAmount());
			statement.setDouble(7, transaction.getCurrentBalance());
			statement.setString(8, type);
			if (statement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return false;
	}

	@Override
	public UserInfo validateUser(UserInfo userDetails) throws HelperException {
		HelperUtils.nullCheck(userDetails);
		UserInfo user = new UserInfo();
		String role = null;
		String query = "select type from UserInfo where userid=? and password=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userDetails.getUserId());
			statement.setString(2, userDetails.getPassword());
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					role = resultSet.getString("type");
				}
			}

		} catch (SQLException e) {
			throw new HelperException(e);
		}
		user.setType(role);
		return user;
	}

	@Override
	public CustomerInfo getUserDetails(UserInfo userDetails) throws HelperException {
		HelperUtils.nullCheck(userDetails);
		CustomerInfo customer = new CustomerInfo();
		String query = "select * from CustomerInfo join UserInfo on CustomerInfo.userid=UserInfo.userid where CustomerInfo.userid=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userDetails.getUserId());
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					customer.setAadhaar(resultSet.getLong("aadhaar"));
					customer.setAddress(resultSet.getString("address"));
					customer.setDateOfBirth(resultSet.getLong("dateofBirth"));
					customer.setEmail(resultSet.getString("email"));
					customer.setMobile(resultSet.getLong("mobile"));
					customer.setName(resultSet.getString("name"));
					customer.setPancard(resultSet.getString("pancard"));
					customer.setUserStatus(resultSet.getString("user_status"));
					customer.setUserId(resultSet.getInt("userid"));
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return customer;
	}

	@Override
	public AccountInfo getAccountDetails(AccountInfo accountInfo) throws HelperException {
		HelperUtils.nullCheck(accountInfo);
		AccountInfo account = new AccountInfo();
		String query = "select * from AccountInfo where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setBigDecimal(1, new BigDecimal(accountInfo.getAccountNumber()));
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					account.setAccountBalance(resultSet.getDouble("account_balance"));
					account.setAccountStatus(resultSet.getString("account_status"));
					account.setAccountNumber(resultSet.getLong("account_number"));
					account.setAccountType(resultSet.getString("account_status"));
					account.setIfscCode(resultSet.getString("ifsc_code"));
					account.setUserId(resultSet.getInt("userid"));
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return account;
	}

	private double getBalance(Long accountNumber) throws HelperException {
		double balance = 0;
		String query = "select account_balance from AccountInfo where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					balance = resultSet.getDouble("account_balance");
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return balance;
	}

	@Override
	public double getTotalBalance(UserInfo user) throws HelperException {
		HelperUtils.nullCheck(user);
		double balance = 0;
		String query = "select account_balance from AccountInfo where userid=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, user.getUserId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					double temp = resultSet.getDouble("account_balance");
					balance = balance + temp;
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return balance;
	}

	@Override
	public Map<Long, AccountInfo> getAccountDetails(UserInfo user) throws HelperException {
		HelperUtils.nullCheck(user);
		int userId = user.getUserId();
		Map<Long, AccountInfo> map = new HashMap<Long, AccountInfo>();
		String query = "select * from AccountInfo where userid=?";
		ResultSet resultSet = null;
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AccountInfo account = new AccountInfo();
				userId = resultSet.getInt("userid");
				account.setUserId(userId);
				account.setAccountBalance(resultSet.getDouble("account_balance"));
				long accountNumber = resultSet.getLong("account_number");
				account.setAccountNumber(accountNumber);
				account.setAccountStatus(resultSet.getString("account_status"));
				account.setAccountType(resultSet.getString("account_type"));
				account.setBranchName(resultSet.getString("branch_name"));
				account.setIfscCode(resultSet.getString("ifsc_code"));
				map.put(accountNumber, account);
			}
			return map;
		} catch (SQLException e) {
			throw new HelperException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public Map<Integer, Map<Long, AccountInfo>> getAllAccountDetails() throws HelperException {
		Map<Integer, Map<Long, AccountInfo>> outerMap = new HashMap<Integer, Map<Long, AccountInfo>>();
		String query = "select * from AccountInfo order by userid";
		ResultSet resultSet = null;
		Map<Long, AccountInfo> innerMap = new HashMap<Long, AccountInfo>();
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				AccountInfo account = new AccountInfo();

				if (!outerMap.containsKey(resultSet.getInt("userid"))) {
					innerMap = new HashMap<Long, AccountInfo>();
				}

				account.setUserId(resultSet.getInt("userid"));
				account.setAccountBalance(resultSet.getDouble("account_balance"));
				account.setAccountNumber(resultSet.getLong("account_number"));
				account.setAccountStatus(resultSet.getString("account_status"));
				account.setAccountType(resultSet.getString("account_type"));
				account.setBranchName(resultSet.getString("branch_name"));
				account.setIfscCode(resultSet.getString("ifsc_code"));

				innerMap.put(resultSet.getLong("account_number"), account);
				outerMap.put(resultSet.getInt("userid"), innerMap);
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
		return outerMap;
	}

	@Override
	public Map<Integer, UserInfo> getAllUserDetails() throws HelperException {
		String query = "select * from UserInfo";
		ResultSet resultSet = null;
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			resultSet = statement.executeQuery();
			return storeDetailsInMapForUser(resultSet);
		} catch (SQLException e) {
			throw new HelperException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<TransactionInfo> getTransactionDetails(AccountInfo account) throws HelperException {
		HelperUtils.nullCheck(account);
		Long accountNumber = account.getAccountNumber();
		String query = "select * from TransactionInfo where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				return setValuesInTransactionPojo(resultSet);
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public List<TransactionInfo> getAllTransactionDetailsWithDate(int days) throws HelperException {
		Long date = HelperUtils.minusDates(days);
		String query = "select * from TransactionInfo where transaction_date > ?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, date);
			ResultSet resultSet = statement.executeQuery();
			return setValuesInTransactionPojo(resultSet);
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public List<TransactionInfo> getSingleUserTransactionDetails(UserInfo user) throws HelperException {
		HelperUtils.nullCheck(user);
		int userId = user.getUserId();
		String query = "select * from TransactionInfo where userid=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				return setValuesInTransactionPojo(resultSet);
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public boolean makeTransaction(TransactionInfo transaction) throws HelperException {
		HelperUtils.nullCheck(transaction);
		double transactionAmount = transaction.getTransactionAmount();
		String type = transaction.getTransactionType();
		if (type.equalsIgnoreCase("withdraw")) {
			long sender = transaction.getSenderAccountNumber();
			double sBalance = getBalance(sender);
			sBalance = sBalance - transactionAmount;
			transaction.setCurrentBalance(sBalance);
			if(insertIntoTransactionTable(transaction)) {
				updateAccountBalance(sender,sBalance);
				return true;
			}
			return false;
		}
		else if (type.equalsIgnoreCase("deposit")) {
			long receiver = transaction.getReceiverAccountNumber();
			double rBalance = getBalance(receiver);
			rBalance = rBalance + transactionAmount;
			transaction.setCurrentBalance(rBalance);
			if(insertIntoTransactionTable(transaction)) {
				updateAccountBalance(receiver,rBalance);
				return true;
			}
			return false;
		} else {
			long sender = transaction.getSenderAccountNumber(), receiver = transaction.getReceiverAccountNumber();
			double rBalance = getBalance(receiver), sBalance = getBalance(sender);
			rBalance = rBalance + transactionAmount;
			transaction.setCurrentBalance(sBalance - transactionAmount);
			if (insertIntoTransactionTable(transaction)) {
				updateAccountBalance(sender,transaction.getCurrentBalance());
				int userid = getUserId(receiver);
				transaction.setUserId(userid);
				transaction.setAccountNumber(receiver);
				transaction.setCurrentBalance(rBalance);
				if(insertIntoTransactionTable(transaction)) {
					updateAccountBalance(receiver,transaction.getCurrentBalance());
					return true;
				}
				
			}
		}

		return false;
	}

	@Override
	public boolean setUserStatus(CustomerInfo customer) throws HelperException {
		HelperUtils.nullCheck(customer);
		int userId = customer.getUserId();
		String status = customer.getUserStatus();
		String query = "update CustomerInfo set user_status=? where userid=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);
			statement.setString(2, status);
			if (statement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return false;
	}

	@Override
	public boolean setAccountStatus(AccountInfo account) throws HelperException {
		HelperUtils.nullCheck(account);
		Long accountNumber = account.getAccountNumber();
		String state = account.getAccountStatus();
		String query = "update AccountInfo set account_states=? where account_number=?";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, accountNumber);
			statement.setString(2, state);
			if (statement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
		return false;
	}

	@Override
	public List<TransactionInfo> getAllTransactionDetails() throws HelperException {
		String query = "select * from TransactionInfo";
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			return setValuesInTransactionPojo(resultSet);
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public UserInfo getDetails(UserInfo user) throws HelperException {
		HelperUtils.nullCheck(user);
		String query="select * from UserInfo where userid=?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, user.getUserId());
			try(ResultSet resultSet=statement.executeQuery()){
				if(resultSet.next()) {
					user.setType(resultSet.getString("type"));
					user.setDateOfBirth(resultSet.getLong("dateofBirth"));
					user.setAddress(resultSet.getString("address"));
					user.setEmail(resultSet.getString("email"));
					user.setMobile(resultSet.getLong("mobile"));
					user.setName(resultSet.getString("name"));
				}
				return user;
			}
		} catch (SQLException e) {
			throw new HelperException(e); 
		}
	}

	@Override
	public boolean changePassword(UserInfo user,String password) throws HelperException {
		HelperUtils.nullCheck(user);
		String query="update UserInfo set password=? where userid=? and password=?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1, password);
			statement.setString(3, user.getPassword());
			statement.setInt(2, user.getUserId());
			statement.execute();
			if(statement.getUpdateCount()==0) {
				throw new HelperException("Old password is wrong");
			}
			return true;
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public int addUser(UserInfo user) throws HelperException {
		HelperUtils.nullCheck(user);
		int id=0;
		String query="insert into UserInfo (password,name,mobile,dateofBirth,type,address,email) values (?,?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)){
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getName());
			statement.setLong(3, user.getMobile());
			statement.setLong(4, user.getDateOfBirth());
			statement.setString(5, user.getType());
			statement.setString(6, user.getAddress());
			statement.setString(7, user.getEmail());
			statement.execute();
			try(ResultSet resultSet=statement.getGeneratedKeys()){
				if(!resultSet.next()) {
					throw new HelperException("Cannot Insert new data");
				}
				else {
					id= resultSet.getInt(1);
				}
				return id;
			}
			
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public boolean updateProfile(UserInfo user) throws HelperException {
		String query="update UserInfo set mobile=?,email=? where userid=?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1, user.getMobile());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getUserId());
			statement.execute();
			return true;
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public String checkAccountAvailability(Long accountNumber) throws HelperException {
		String query="select account_status from AccountInfo where account_number=?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setDouble(1, accountNumber);
			try(ResultSet resultSet=statement.executeQuery()){
				if(resultSet.next()) {
					return resultSet.getString("account_status");
				}
				else {
					throw new HelperException("Invalid Account Number");
				}
			}			
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public Long addAccount(AccountInfo account) throws HelperException {
		String query="insert into AccountInfo (account_type,userid,ifsc_code,branch_name,account_balance,account_status) values(?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)){
			statement.setString(1, account.getAccountType());
			statement.setInt(2, account.getUserId());
			statement.setString(3,account.getIfscCode());
			statement.setString(4, account.getBranchName());
			statement.setDouble(5,account.getAccountBalance());
			statement.setString(6, account.getAccountStatus());
			statement.executeUpdate();
			try(ResultSet resultSet=statement.getGeneratedKeys()){
				if(resultSet.next()) {
					return resultSet.getLong(1);
				}
				else {
					throw new HelperException("New account creation failed..");
				}
			}
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public boolean insertIntoCustomer(CustomerInfo customer) throws HelperException {
		String query="insert into CustomerInfo (userid,aadhaar,pancard,user_status) values (?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, customer.getUserId());
			statement.setLong(2,customer.getAadhaar());
			statement.setString(3, customer.getPancard());
			statement.setString(4, customer.getUserStatus());
			statement.executeUpdate();
			if(statement.getUpdateCount()==0) {
				throw new HelperException("Insert failed");
			}
			return true;
		} catch (SQLException e) {
			throw new HelperException(e);
		}
	}

	@Override
	public Map<Long, AccountInfo> getAccountDetails(UserInfo user,String status) throws HelperException {
		HelperUtils.nullCheck(user);
		int userId = user.getUserId();
		Map<Long, AccountInfo> map = new HashMap<Long, AccountInfo>();
		String query = "select * from AccountInfo where userid=? and account_status=?";
		ResultSet resultSet = null;
		try (Connection connection=getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);
			statement.setString(2, status);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AccountInfo account = new AccountInfo();
				userId = resultSet.getInt("userid");
				account.setUserId(userId);
				account.setAccountBalance(resultSet.getDouble("account_balance"));
				long accountNumber = resultSet.getLong("account_number");
				account.setAccountNumber(accountNumber);
				account.setAccountStatus(resultSet.getString("account_status"));
				account.setAccountType(resultSet.getString("account_type"));
				account.setBranchName(resultSet.getString("branch_name"));
				account.setIfscCode(resultSet.getString("ifsc_code"));
				map.put(accountNumber, account);
			}
			return map;
		} catch (SQLException e) {
			throw new HelperException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}
}
