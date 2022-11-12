package users;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bankingapplication.BankingApplicationStorage;
import bankingapplication.StorageLayerInterface;
import bankingapplicationPojos.Account;
import bankingapplicationPojos.AccountStatusRequest;
import bankingapplicationPojos.Customer;
import bankingapplicationPojos.CustomerStatusRequest;
import bankingapplicationPojos.Statements;
import bankingapplicationPojos.TransactionRequest;
import bankingapplicationPojos.User;
import customcheck.CustomCheck;
import customexceptionpackage.CustomException;

public class Operations {

	StorageLayerInterface storageLayerObject=new BankingApplicationStorage();
	Logger logger=Logger.getLogger(Operations.class.getName());
	
	public boolean loginMethod(int userId,String password) throws ClassNotFoundException, SQLException, CustomException {
		CustomCheck.isNull(password);
		Map<Integer,User>allUsersDetails = storageLayerObject.getUserDetails();
		User userPojoObject=allUsersDetails.get(userId);
		if(userPojoObject==null) {
			throw new CustomException("User Id is doesn't Match!");
		}
		if(!password.equals(userPojoObject.getPassword())) {
			throw new CustomException("password is incorrect!");
		}
		return true;
	}//end of login method
	
	
	public void depositMethod(long depositeAmount,long accountNumber) throws CustomException {
		if(depositeAmount<100) {
			throw new CustomException("Please Enter Valid Amount !");
		}
		else if(depositeAmount>100000) {
			throw new CustomException("Maximum amount limit exceeded !");
		}
		Account accountPojoObject=getAccountDetails(accountNumber);
        long currentBalance=accountPojoObject.getBalance();
        long finalAmount=currentBalance+depositeAmount;
        storageLayerObject.updateBalance(accountNumber, finalAmount);
        
        Statements statementObject=new Statements();
        statementObject.setReceiverAccount(accountNumber);
        statementObject.setTransferAmount(depositeAmount);
        statementObject.setTime(System.currentTimeMillis());
        statementObject.setCustomerId(accountPojoObject.getCustomerId());
        statementObject.setModeOfTransaction("Deposit");
        statementObject.setTransactionType("Debit");
		storageLayerObject.updateTransactionStatement(statementObject);
	}
	
	
	public boolean withdrawMethod(long withdrawAmount,long accountNumber) throws CustomException {
		if(withdrawAmount<100) {
			throw new CustomException("Please Enter Valid Amount !");
		}
		else if(withdrawAmount>100000) {
			throw new CustomException("Maximum amount limit exceeded !");
		}
		Account accountPojoObject=getAccountDetails(accountNumber);
        long currentBalance=accountPojoObject.getBalance();
        if(currentBalance<withdrawAmount) {
		throw new CustomException("Overdraft!");
        }
        TransactionRequest requestPojoObject=new TransactionRequest();
        requestPojoObject.setSenderAccountNumber(accountNumber);
        requestPojoObject.setTransferAmount(withdrawAmount);
        requestPojoObject.setRequestStatus("Waiting");
        storageLayerObject.transactionRequest(requestPojoObject);
        return true;
	}//end of withdraw Method
	
	
public void executeTransaction(TransactionRequest requestPojoObject) throws CustomException {
	CustomCheck.isNull(requestPojoObject);

	Account accountPojo=getAccountDetails(requestPojoObject.getSenderAccountNumber());
		long Balance=accountPojo.getBalance();
		if(Balance<requestPojoObject.getTransferAmount()) {
			throw new CustomException("Overdraft !");
		}
		Balance=Balance-requestPojoObject.getTransferAmount();
		storageLayerObject.updateBalance(requestPojoObject.getSenderAccountNumber(), Balance);
		
		Statements statementObject=new Statements();
		statementObject.setCustomerId(accountPojo.getCustomerId());
		statementObject.setSenderAccount(requestPojoObject.getSenderAccountNumber());
		statementObject.setTransferAmount(requestPojoObject.getTransferAmount());
		statementObject.setTime(System.currentTimeMillis());
		statementObject.setCustomerId(accountPojo.getCustomerId());
		statementObject.setModeOfTransaction("Withdraw");
		statementObject.setTransactionType("Credit");
		
		
		storageLayerObject.updateTransactionStatement(statementObject);
	}
	
	
	public boolean transactionMethod(long senderAccountNumber,long receiverAccountNumber,long transferAmount,String password) throws CustomException {
		CustomCheck.isNull(password);
		if(transferAmount<=0) {
			throw new CustomException("Please Enter Valid Amount !");
		}
		else if(transferAmount>100000) {
			throw new CustomException("Maximum amount limit exceeded !");
		}
		Account senderAccount=getAccountDetails(senderAccountNumber);
		Account receiverAccount=getAccountDetails(receiverAccountNumber);
		Map<Integer,User>allUsersDetails = storageLayerObject.getUserDetails();
		User sendersUserDetails=allUsersDetails.get(senderAccount.getCustomerId());	
		long senderBalance=senderAccount.getBalance();
		 if(!password.equals(sendersUserDetails.getPassword())) {
			throw new CustomException(" Password is InCorrect!");

		}
		else if(senderBalance<transferAmount) {
			throw new CustomException("Overdraft");
		}
		senderBalance=senderBalance-transferAmount;
		long receiverBalance=receiverAccount.getBalance()+transferAmount;
		storageLayerObject.updateBalance(senderAccountNumber, senderBalance);
		storageLayerObject.updateBalance(receiverAccountNumber, receiverBalance);
		Map<Integer,Customer> allCustomerDetails=storageLayerObject.getCustomerDetails();
		Customer senderCustomerObject=allCustomerDetails.get(senderAccount.getCustomerId());
		Customer receiverCustomerObject=allCustomerDetails.get(receiverAccount.getCustomerId());
		
		Statements senderStatementObject=new Statements();
		senderStatementObject.setCustomerId(senderCustomerObject.getCustomerId());
		senderStatementObject.setSenderAccount(senderAccountNumber);
		senderStatementObject.setReceiverAccount(receiverAccountNumber);
		senderStatementObject.setTransferAmount(transferAmount);
		long time=System.currentTimeMillis();
		senderStatementObject.setTime(time);
		senderStatementObject.setTransactionType("Debit");
		senderStatementObject.setModeOfTransaction("Transfer");
		
		Statements receiverStatementObject=new Statements();
		receiverStatementObject.setCustomerId(receiverCustomerObject.getCustomerId());
		receiverStatementObject.setSenderAccount(senderAccountNumber);
		receiverStatementObject.setReceiverAccount(receiverAccountNumber);
		receiverStatementObject.setTransferAmount(transferAmount);
		receiverStatementObject.setTime(time);
		receiverStatementObject.setTransactionType("Credit");
		receiverStatementObject.setModeOfTransaction("Transfer");
		
		
		storageLayerObject.updateTransactionStatement(senderStatementObject);
		storageLayerObject.updateTransactionStatement(receiverStatementObject);
		return true;
	}//end of transactionMethod
	
	
	public boolean changePassword(int userId,String newPassword,String confirmPassword) throws CustomException {
		CustomCheck.isNull(newPassword);
        if(!newPassword.equalsIgnoreCase(confirmPassword)) {
        	throw new CustomException(" Password is InCorrect!");
        }
		   User user=getUserDetails(userId);
		   user.setPassword(newPassword);
		   updateUserInfo(user);
		   return true;
	}//end of changePassword
	
	public void forgetPassword(int userId,long mobileNumber,String newPassword,String confirmPassword) throws CustomException {
		User user=getUserDetails(userId);
		if(user==null) {
			throw new CustomException("User Id Doesn't Match !");
		}
		else if(user.getMobileNumber()!=mobileNumber) {
			throw new CustomException("Mobile Number Mismatch !");
		}
		changePassword(userId,newPassword,confirmPassword);
	}
	
	
	public Account getAccountDetails(long accountNumber) throws CustomException {
		Map<Integer,Map<Long,Account>>allAccountDetails=storageLayerObject.getAllAccountDetailsOfCustomer();
		Map<Long,Account> mapOfAccount=allAccountDetails.get(storageLayerObject.getCustomerId(accountNumber));
		if(mapOfAccount==null) {
			throw new CustomException("Enter Valid Account Number !");
		}
		Account currentAccountLoginPojo=new Account();
		for(Account account:mapOfAccount.values()) {
			if(accountNumber==account.getAccountNumber()) {
				currentAccountLoginPojo=account;
			}
		}
		return currentAccountLoginPojo;
	}//end of getAccountDetails
	
	
	public User getUserDetails(int userId) {

		Map<Integer,User>allUsersDetails = storageLayerObject.getUserDetails();
		User UserDetails=allUsersDetails.get(userId);
		return UserDetails;
	}
	
	
	public Customer getCustomerDetails(int userId) {

		Map<Integer,Customer> allCustomerDetails=storageLayerObject.getCustomerDetails();
		Customer CustomerPojoObject=allCustomerDetails.get(userId);
		return CustomerPojoObject;
	}
	
	
	public  Map<Integer,Statements> getTransactionStatements(long accountNumber,int days){
		long transactionTime=0L;
		if(days>0) {
			transactionTime=CustomCheck.getMillis(days);
		}
		 Map<Integer,Statements> statements=storageLayerObject.getStatements(storageLayerObject.getCustomerId(accountNumber), accountNumber,transactionTime);
		 Map<Integer,Statements> treeMap=new TreeMap<>(statements);
		return treeMap;
	}
	
	
	public void changeInfo(Customer customer) throws CustomException {
		CustomCheck.customerValidation(customer);
		storageLayerObject.changeInfo(customer);
	}
	
	public void updateUserInfo(User user) throws CustomException{
		CustomCheck.userValitation(user);
		storageLayerObject.updateUserInfo(user);
	}
	
	public void customerStatusRequest(int customerId,String description) throws CustomException {
		CustomCheck.isNull(description);
		CustomerStatusRequest request=new CustomerStatusRequest();
		request.setCustomerId(customerId);
		request.setDescription(description);
		request.setStatus("Waiting");
		storageLayerObject.customerStatusRequest(request);
	}
	
	public void accountStatusRequest(long accountNumber,String description) throws CustomException {
		CustomCheck.isNull(description);
		AccountStatusRequest request=new AccountStatusRequest();
		request.setAccountNumber(accountNumber);
		request.setDescription(description);
		request.setStatus("Waiting");
		storageLayerObject.accountStatusRequest(request);
	}
	
}
