package bankingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import bankingapplicationPojos.Account;
import bankingapplicationPojos.AccountStatusRequest;
import bankingapplicationPojos.Customer;
import bankingapplicationPojos.CustomerStatusRequest;
import bankingapplicationPojos.Statements;
import bankingapplicationPojos.TransactionRequest;
import bankingapplicationPojos.User;
import customexceptionpackage.CustomException;

public class BankingApplicationStorage implements StorageLayerInterface {
    Logger logger=Logger.getLogger(BankingApplicationStorage.class.getName());

	public  Connection getConnection() throws ClassNotFoundException, SQLException {
		String url="jdbc:mysql://localhost:3306/banking_system";
        String userName="alagu";
        String password="mysql2504.";
       
			Connection connection=DriverManager.getConnection(url, userName, password);
	        return connection;
			}//end of getConnection
	
	public void insertAccountDetails(Account account) {
		String query="INSERT INTO account_info(ACCOUNT_NUMBER,ACCOUNT_TYPE,IFSC_CODE,"
				+ "BRANCH_NAME,BALANCE,STATUS,CUSTOMER_ID) VALUES(?,?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1, account.getAccountNumber());
			statement.setString(2, account.getAccountType());
			statement.setString(3, account.getIfscCode());
			statement.setString(4, account.getBranchName());
			statement.setLong(5, account.getBalance());
			statement.setString(6, account.getStatus());
			statement.setInt(7, account.getCustomerId());
			statement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
    	logger.info("Successfully Inserted");
	}
	
//	public Map<Integer,Account> getAccountDetails() {
//		Map<Integer,Account> mapOfAccountDetails=new HashMap<>();
//		String query=" SELECT * FROM account_info INNER JOIN customer_info ON account_info.CUSTOMER_ID=customer_info.CUSTOMER_ID";
//		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
//			try(ResultSet resultSet=statement.executeQuery()){
//				while(resultSet.next()) {
//					Account accountPojoObject=new Account();
//					accountPojoObject.setAccountNumber(resultSet.getLong("ACCOUNT_NUMBER"));
//					accountPojoObject.setAccountType(resultSet.getString("ACCOUNT_TYPE"));
//					accountPojoObject.setIfscCode(resultSet.getString("IFSC_CODE"));
//					accountPojoObject.setBranchName(resultSet.getString("BRANCH_NAME"));
//					accountPojoObject.setBalance(resultSet.getLong("BALANCE"));
//					accountPojoObject.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
//					accountPojoObject.setStatus(resultSet.getString("STATUS"));
//					mapOfAccountDetails.put(resultSet.getInt("CUSTOMER_ID"), accountPojoObject);
//				}
//			}
//		}
//		 catch (ClassNotFoundException | SQLException e) {
//	 			e.printStackTrace();
//	 		}
//		return mapOfAccountDetails;
//	}//END OF AccountPojo
	
	
	public Map<Long,Account> getAccountDetailsOfCustomer(int customerId){
		Map<Long,Account> mapOfAccount=new HashMap<>();
		String query="SELECT * FROM account_info WHERE CUSTOMER_ID = ?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, customerId);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				Account account=new Account();
				account.setAccountNumber(resultSet.getLong("ACCOUNT_NUMBER"));
				account.setAccountType(resultSet.getString("ACCOUNT_TYPE"));
				account.setIfscCode(resultSet.getString("IFSC_CODE"));
				account.setBranchName(resultSet.getString("BRANCH_NAME"));
				account.setBalance(resultSet.getLong("BALANCE"));
				account.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
				account.setStatus(resultSet.getString("STATUS"));
				mapOfAccount.put(resultSet.getLong("ACCOUNT_NUMBER"), account);
			}
			}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return mapOfAccount;
	}
	
	public Map<Integer,Map<Long,Account>> getAllAccountDetailsOfCustomer(){
		Map<Integer,Map<Long,Account>> mapOfAllAccount=new HashMap<>();
		String query="SELECT ACCOUNT_NUMBER,CUSTOMER_ID FROM account_info";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				mapOfAllAccount.put(resultSet.getInt("CUSTOMER_ID"),getAccountDetailsOfCustomer(resultSet.getInt("CUSTOMER_ID")) );
			}
			
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
return mapOfAllAccount;
	}

	
	public Map<Integer,User> getUserDetails() {
		Map<Integer,User> mapOfAllUser=new HashMap<>();
		String query="SELECT * FROM user_info ";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			try(ResultSet resultSet=statement.executeQuery()){
				while(resultSet.next()) {
					User user=new User();
					user.setUserId(resultSet.getInt("USER_ID"));
					user.setUserName(resultSet.getString("USER_NAME"));
					user.setDataOfBirth(resultSet.getString("DATE_OF_BIRTH"));
					user.setMobileNumber(resultSet.getLong("MOBILE"));
					user.setAddress(resultSet.getString("ADDRESS"));
					user.setEmailId(resultSet.getString("EMAIL_ID"));
					user.setPassword(resultSet.getString("PASSWORD"));
					user.setRole(resultSet.getString("ROLE"));
					mapOfAllUser.put(resultSet.getInt("USER_ID"), user);
				}
			}
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
		return mapOfAllUser;
	}
	
	public void insertUsersDetails(User user) {
		String query="INSERT INTO user_info(USER_NAME,DATE_OF_BIRTH,MOBILE,ADDRESS,EMAIL_ID,PASSWORD,ROLE) VALUES(?,?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1,user.getUserName());
			statement.setString(2, user.getDataOfBirth());
			statement.setLong(3, user.getMobileNumber());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getEmailId());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getRole());
			statement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCustomerDetails(Customer customer) {
		int userId=0;
		String query="INSERT INTO user_info(USER_NAME,DATE_OF_BIRTH,MOBILE,ADDRESS,EMAIL_ID,PASSWORD,ROLE) VALUES(?,?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1,customer.getUserName());
			statement.setString(2, customer.getDataOfBirth());
			statement.setLong(3, customer.getMobileNumber());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getEmailId());
			statement.setString(6, customer.getPassword());
			statement.setString(7, customer.getRole());
			statement.executeUpdate();
		ResultSet resultSet=statement.getGeneratedKeys();
		while(resultSet.next()) {
			 userId=resultSet.getInt(1);
		}
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String query2="INSERT INTO customer_info(CUSTOMER_ID,AADHAAR_NUMBER,PAN_NUMBER,STATUS) VALUES (?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query2)){
			statement.setInt(1, userId);
			statement.setLong(2, customer.getAadhaarNumber());
			statement.setString(3, customer.getPanNumber());
			statement.setString(4, customer.getStatus());
			statement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer,Customer> getCustomerDetails(){
		Map<Integer,Customer> mapOfCustomer=new HashMap<>();
		String query="SELECT user_info.USER_ID,user_info.USER_NAME,user_info.DATE_OF_BIRTH,"
				+ "user_info.MOBILE,user_info.ADDRESS,user_info.EMAIL_ID,user_info.PASSWORD,"
				+ "user_info.ROLE,customer_info.CUSTOMER_ID,customer_info.AADHAAR_NUMBER,"
				+ "customer_info.PAN_NUMBER,customer_info.STATUS FROM user_info INNER JOIN "
				+ "customer_info ON user_info.USER_ID=customer_info.CUSTOMER_ID";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			try(ResultSet resultSet=statement.executeQuery()){
				while(resultSet.next()) {
					Customer customer=new Customer();
					customer.setUserId(resultSet.getInt("USER_ID"));
					customer.setUserName(resultSet.getString("USER_NAME"));
					customer.setDataOfBirth(resultSet.getString("DATE_OF_BIRTH"));
					customer.setMobileNumber(resultSet.getLong("MOBILE"));
					customer.setAddress(resultSet.getString("ADDRESS"));
					customer.setEmailId(resultSet.getString("EMAIL_ID"));
					customer.setPassword(resultSet.getString("PASSWORD"));
					customer.setRole(resultSet.getString("ROLE"));
					customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
					customer.setAadhaarNumber(resultSet.getLong("AADHAAR_NUMBER"));
					customer.setPanNumber(resultSet.getString("PAN_NUMBER"));
					customer.setUserId(resultSet.getInt("USER_ID"));
					customer.setStatus(resultSet.getString("STATUS"));
					mapOfCustomer.put(resultSet.getInt("USER_ID"), customer);
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return mapOfCustomer;
	}//end of getCustomerDetails
	 
	
	
	public Map<Long,Map<Integer,Statements>> getAllTransactionStatements(){
		
		Map<Long,Map<Integer,Statements>> transactionStatements=new HashMap<>();
		String query="SELECT ACCOUNT_NUMBER,CUSTOMER_ID FROM account_info";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				transactionStatements.put(resultSet.getLong("ACCOUNT_NUMBER"),getStatements(resultSet.getInt("CUSTOMER_ID"),resultSet.getLong("ACCOUNT_NUMBER"),0L));
			}
		}
		
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return transactionStatements;
	}//end of getAllTransactionStatements

	public void statementOperation(String query,Map<Integer,Statements> mapOfStatements){
		
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
	           try(ResultSet resultSet=statement.executeQuery()){
	        	   while(resultSet.next()) {
	        		   Statements statementObject=new Statements();
	               	statementObject.setTransactionId(resultSet.getInt("TRANSACTION_ID"));
	               	statementObject.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
	               	statementObject.setSenderAccount(resultSet.getLong("SENDER_ACCOUNT_NUMBER"));
	               	statementObject.setReceiverAccount(resultSet.getLong("RECEIVER_ACCOUNT_NUMBER"));
	               	statementObject.setTransferAmount(resultSet.getLong("TRANSFER_AMOUNT"));
	               	statementObject.setTransactionType(resultSet.getString("TRANSACTION_TYPE"));
	               	statementObject.setTime(resultSet.getLong("TRANSACTION_TIME"));
	               	statementObject.setModeOfTransaction(resultSet.getString("MODE_OF_TRANSACTION"));
	               	mapOfStatements.put(resultSet.getInt("TRANSACTION_ID"), statementObject);
	               }
	           }
	}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
	}//end of statementOperation
		

	public Map<Integer,Statements> getStatements(int customerId,long accountNumber,long transactionTime){
		Map<Integer,Statements> mapOfStatements=new HashMap<>();
		if(transactionTime==0L) {
			 String query="SELECT * FROM transaction_statements WHERE CUSTOMER_ID = "
						+customerId+"  AND SENDER_ACCOUNT_NUMBER = "+accountNumber;;
							statementOperation(query,mapOfStatements);

							String query2="SELECT * FROM transaction_statements WHERE CUSTOMER_ID = "
							+customerId+" AND RECEIVER_ACCOUNT_NUMBER = "+accountNumber;;
							statementOperation(query2,mapOfStatements);
				            return mapOfStatements;
			
		}
		 String query="SELECT * FROM transaction_statements WHERE CUSTOMER_ID = "
		+customerId+"  AND SENDER_ACCOUNT_NUMBER = "+accountNumber+" AND TRANSACTION_TIME >="+ transactionTime;
			statementOperation(query,mapOfStatements);

			String query2="SELECT * FROM transaction_statements WHERE CUSTOMER_ID = "
			+customerId+" AND RECEIVER_ACCOUNT_NUMBER = "+accountNumber+" AND TRANSACTION_TIME >="+ transactionTime;
			statementOperation(query2,mapOfStatements);
            return mapOfStatements;
	}//end of getStatements
	
	
	
	public void updateBalance(long accountNumber,long finalAmount) {
		String query="UPDATE account_info SET BALANCE = ? WHERE ACCOUNT_NUMBER = ?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1, finalAmount);
			statement.setLong(2, accountNumber);
			statement.executeUpdate();
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
	}//END OF UPDATE BALANCE
	
	public void transactionRequest(TransactionRequest requestPojoObject) {
		String query="INSERT INTO transaction_request(SENDER_ACCOUNT_NUMBER,TRANSFER_AMOUNT,REQUEST_STATUS) VALUES(?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1,requestPojoObject.getSenderAccountNumber() );
			statement.setLong(2, requestPojoObject.getTransferAmount());
			statement.setString(3, requestPojoObject.getRequestStatus());
			statement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
	}
	
	public void updateTransactionRequest(TransactionRequest requestPojoObject,String requestStatus) {
		String query="UPDATE transaction_request SET REQUEST_STATUS= ? WHERE REQUEST_ID = ?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setString(1, requestStatus);
			statement.setInt(2, requestPojoObject.getRequestId());
			statement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
	}
	
	
	public void transactionMethod(long senderAccountNumber,long receiverAccountNumber,long senderBalance,long receiverBalance) {
		updateBalance(senderAccountNumber,senderBalance);
		updateBalance(receiverAccountNumber,receiverBalance);
	}//end of transactionMethod
	
	
	public void updateTransactionStatement(Statements statementObject) {
		String query="INSERT INTO transaction_statements(CUSTOMER_ID,SENDER_ACCOUNT_NUMBER,"
				+ "RECEIVER_ACCOUNT_NUMBER,TRANSFER_AMOUNT,TRANSACTION_TIME,TRANSACTION_TYPE,MODE_OF_TRANSACTION) VALUES(?,?,?,?,?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, statementObject.getCustomerId());
			statement.setLong(2, statementObject.getSenderAccount());
			statement.setLong(3, statementObject.getReceiverAccount());
			statement.setLong(4, statementObject.getTransferAmount());
			statement.setLong(5, statementObject.getTime().getTime());
			statement.setString(6, statementObject.getTransactionType());
			statement.setString(7, statementObject.getModeOfTransaction());
			statement.executeUpdate();
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
	}//end of updateTransactionStatement
	

	
	
	
	
	public int getCustomerId(long accountNumber) {
		int customerId=0;
		String query="SELECT CUSTOMER_ID FROM account_info WHERE ACCOUNT_NUMBER = ?";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1, accountNumber);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				customerId=resultSet.getInt("CUSTOMER_ID");
			}
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return customerId;
	}
	
	public Map<Integer,TransactionRequest> getAllTransactionRequest(){
		Map<Integer,TransactionRequest> requestStatementsMap=new HashMap<>();
		String query="SELECT * FROM transaction_request";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
	           try(ResultSet resultSet=statement.executeQuery()){
	        	   while(resultSet.next()) {
	        		   TransactionRequest requestPojoObject=new TransactionRequest();
	        		   requestPojoObject.setRequestId(resultSet.getInt("REQUEST_ID"));
	        		   requestPojoObject.setSenderAccountNumber(resultSet.getLong("SENDER_ACCOUNT_NUMBER"));
	        		   requestPojoObject.setTransferAmount(resultSet.getLong("TRANSFER_AMOUNT"));
	        		   requestPojoObject.setRequestStatus(resultSet.getString("REQUEST_STATUS"));
	        		   requestStatementsMap.put(resultSet.getInt("REQUEST_ID"), requestPojoObject);
	        	   }
	           }
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return requestStatementsMap;
		}//end of getAllTransactionRequest
	
	public void changeStatus(String query) {
		
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.executeUpdate();
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
	}
	
	public void changeInfo(Customer customer){
		if(customer.getUserName()!=null) {
			String query="UPDATE user_info SET USER_NAME = '"+customer.getUserName()+"' WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
		if(customer.getDataOfBirth()!=null) {
			String query="UPDATE user_info SET DATE_OF_BIRTH = '"+customer.getDataOfBirth()+"' WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
	   if(customer.getMobileNumber()!=0L) {
			String query="UPDATE user_info SET MOBILE = "+customer.getMobileNumber()+" WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
		if(customer.getAddress()!=null) {
			String query="UPDATE user_info SET ADDRESS = '"+customer.getAddress()+"' WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
		if(customer.getAadhaarNumber()!=0L) {
			String query="UPDATE customer_info SET AADHAAR_NUMBER= "+customer.getAadhaarNumber()+" WHERE CUSTOMER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
		if(customer.getPanNumber()!=null) {
			String query="UPDATE customer_info SET PAN_NUMBER= '"+customer.getPanNumber()+" ' WHERE CUSTOMER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
		if(customer.getEmailId()!=null) {
			String query="UPDATE user_info SET EMAIL_ID = '"+customer.getEmailId()+"' WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
		}
	    if(customer.getPassword()!=null) {
			   String query="UPDATE user_info SET PASSWORD = '"+customer.getPassword()+"' WHERE USER_ID = "+customer.getCustomerId();
				changeStatus(query);
		}
	    if(customer.getRole()!=null) {
	    	String query="UPDATE user_info SET ROLE = '"+customer.getRole()+" ' WHERE USER_ID = "+customer.getCustomerId();
			changeStatus(query);
	    }
	    if(customer.getStatus()!=null) {
	    	String query="UPDATE customer_info SET STATUS = '"+customer.getStatus()+" ' WHERE CUSTOMER_ID = "+customer.getCustomerId();
			changeStatus(query);
	    }

	}//end of changeCustomer
	
	public void updateUserInfo(User user) {
		if(user.getUserName()!=null) {
			String query="UPDATE user_info SET USER_NAME= '"+user.getUserName()+"' WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
		}
		if(user.getDataOfBirth()!=null) {
			String query="UPDATE user_info SET DATE_OF_BIRTH= '"+user.getDataOfBirth()+"' WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
		}
		if(user.getMobileNumber()!=0L) {
			String query="UPDATE user_info SET MOBILE = "+user.getMobileNumber()+" WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
		}
		if(user.getAddress()!=null) {
			String query="UPDATE user_info SET ADDRESS = '"+user.getAddress()+"' WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
		}
		if(user.getEmailId()!=null) {
			String query="UPDATE user_info SET EMAIL_ID = '"+user.getEmailId()+"' WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
		}
		  if(user.getPassword()!=null) {
			   String query="UPDATE user_info SET PASSWORD = '"+user.getPassword()+"' WHERE USER_ID = "+user.getUserId();
				changeStatus(query);
		}
	    if(user.getRole()!=null) {
	    	String query="UPDATE user_info SET ROLE = '"+user.getRole()+" ' WHERE USER_ID = "+user.getUserId();
			changeStatus(query);
	    }
		
		
	}
	
	public void updateCustomerStatus(int customerId,String requestStatus,String customerStatus) {    ///
		String query="UPDATE customer_info SET STATUS= '"+customerStatus+"' WHERE CUSTOMER_ID = "+customerId;
		changeStatus(query);
		String query1="UPDATE customer_status_request SET STATUS= '"+requestStatus+"' WHERE CUSTOMER_ID = "+customerId;
		changeStatus(query1);
	}//end of changeCustomerStatus
	
	public void customerStatusRequest(CustomerStatusRequest request) {
		String query="INSERT INTO customer_status_request(CUSTOMER_ID,DESCRIPTION,STATUS)VALUES(?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setInt(1, request.getCustomerId());
			statement.setString(2, request.getDescription());
			statement.setString(3,request.getStatus());
			statement.executeUpdate();
		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
	}//end of customerStatusRequest
	
	public Map<Integer,CustomerStatusRequest> getAllCustomerStatusRequest(){
		Map<Integer,CustomerStatusRequest> customerRequestMap=new HashMap<>();
		String query="SELECT * FROM customer_status_request WHERE STATUS='Waiting'";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
	           try(ResultSet resultSet=statement.executeQuery()){
	        	   while(resultSet.next()) {
	        		   CustomerStatusRequest request=new CustomerStatusRequest();
	        		   request.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
	        		   request.setDescription(resultSet.getString("DESCRIPTION"));
	        		   request.setStatus(resultSet.getString("STATUS"));
	        		   customerRequestMap.put(resultSet.getInt("CUSTOMER_ID"), request);
	        	   }
	           }
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
		return customerRequestMap;
		}//end of getAllTransactionRequest
	
	
	public void accountStatusRequest(AccountStatusRequest request) {
		String query="INSERT INTO account_status_request(ACCOUNT_NUMBER,DESCRIPTION,REQUEST_STATUS)VALUES(?,?,?)";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			statement.setLong(1,request.getAccountNumber() );
			statement.setString(2, request.getDescription());
			statement.setString(3, request.getStatus());
			statement.executeUpdate();

		}
		 catch (ClassNotFoundException | SQLException e) {
	 			e.printStackTrace();
	 		}
	}//end of accountStatusRequest
	
	public void updateAccountStatus(Account account,String status,String requestStatus) {
		String query="UPDATE account_info SET STATUS= '"+status+"' WHERE ACCOUNT_NUMBER = "+account.getAccountNumber();
		changeStatus(query);
		String query1="UPDATE account_status_request SET REQUEST_STATUS= '"+requestStatus+"' WHERE ACCOUNT_NUMBER = "+account.getAccountNumber();
		changeStatus(query1);
	}//end of delete user
	
	public List<AccountStatusRequest> getAllAccountStatusRequest(){
		
		List<AccountStatusRequest> accountStatusRequest=new ArrayList<>();
		String query="SELECT * FROM account_status_request WHERE REQUEST_STATUS = 'Waiting'";
		try(Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(query)){
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				AccountStatusRequest request=new AccountStatusRequest();
				request.setAccountNumber(resultSet.getLong("ACCOUNT_NUMBER"));
				request.setDescription(resultSet.getString("DESCRIPTION"));
				request.setStatus(resultSet.getString("REQUEST_STATUS"));
				accountStatusRequest.add(request);
			}
		}
		catch (ClassNotFoundException | SQLException e) {
 			e.printStackTrace();
 		}
	   return accountStatusRequest;
	}//end of getAllAccountStatusRequest
	
	
	
	
	
}
