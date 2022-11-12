package users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bankingapplicationPojos.Account;
import bankingapplicationPojos.AccountStatusRequest;
import bankingapplicationPojos.Customer;
import bankingapplicationPojos.CustomerStatusRequest;
import bankingapplicationPojos.Statements;
import bankingapplicationPojos.TransactionRequest;
import bankingapplicationPojos.User;
import customcheck.CustomCheck;
import customexceptionpackage.CustomException;

public class Admin extends Operations {
	
    Scanner scanner=new Scanner(System.in);

	
	
	public  Map<Integer,Customer> getAllCustomerDetails(){
		return storageLayerObject.getCustomerDetails();
	}
	
	public Map<Integer,Customer> getActiveCustomer(){
		Map<Integer,Customer> activeCustomer=new TreeMap<>();
		Map<Integer,Customer> allCustomer=getAllCustomerDetails();
		for(Customer customer:allCustomer.values()) {
			if(customer.getStatus().trim().equalsIgnoreCase("Active")) {
				activeCustomer.put(customer.getUserId(), customer);
			}
		}
		return activeCustomer;
	}
	
	
	public Map<Long,Map<Integer,Statements>>getAllStatements(){
		return storageLayerObject.getAllTransactionStatements();
	}
	
	public Map<Integer,Map<Long,Account>> getAllAccountDetailsOfCustomer(){
		return storageLayerObject.getAllAccountDetailsOfCustomer();
	}

	public Map<Long,Account> getActiveAccounts(int userId){
		Map<Long,Account> activeAccounts=new HashMap<>();
		Map<Integer,Map<Long,Account>> allAccounts=storageLayerObject.getAllAccountDetailsOfCustomer();
		Map<Long,Account> accounts=allAccounts.get(userId);
		for(Account account:accounts.values()) {
			if(account.getStatus().equalsIgnoreCase("Active")) {
				activeAccounts.put(account.getAccountNumber(), account);
			}
		}
		return activeAccounts;
	}
	
	public Map<Long,Account> getInactiveAccount(int userId) throws CustomException{
		Map<Long,Account> accountsMap=new HashMap<>();
		Map<Integer,Map<Long,Account>> allAccounts=storageLayerObject.getAllAccountDetailsOfCustomer();
		Map<Long,Account> accounts=allAccounts.get(userId);
		for(Account account:accounts.values()) {
			if(account.getStatus().trim().equalsIgnoreCase("Inactive")) {
				accountsMap.put(account.getAccountNumber(), account);
			}
		}
		if(accountsMap.isEmpty()||accountsMap==null) {
			throw new CustomException("No More InActive Accounts !");
		}
		return accountsMap;
	}

	public Map<Integer,User> getAllUsersDetails(){
	return storageLayerObject.getUserDetails();	
	}
	
    public void insertNewUsers(User user) throws CustomException {
    	CustomCheck.userValitation(user);
    	storageLayerObject.insertUsersDetails(user);
    }
    
    public void insertCustomerDetails(Customer customer) throws CustomException {
    	CustomCheck.customerValidation(customer);
    	storageLayerObject.insertCustomerDetails(customer);
    }
    
    public void insertAccountInfo(Account account) throws CustomException {
    	CustomCheck.accountValidation(account);
    	storageLayerObject.insertAccountDetails(account);
    }
    
    
    public Map<Integer,CustomerStatusRequest> getAllCustomerStatusRequest() throws CustomException{

    	if(storageLayerObject.getAllCustomerStatusRequest().isEmpty()) {
			throw new CustomException("No Request Available !");
		}   
    	
    	return  storageLayerObject.getAllCustomerStatusRequest();
    }
    
    public Map<Integer,TransactionRequest> getAllWithdrawRequest(){
    	return storageLayerObject.getAllTransactionRequest();
    }
    
    public void updateTransactionRequest(TransactionRequest requestPojoObject,String action) throws CustomException {
    	if(action.equalsIgnoreCase("Accepted")) {
    		executeTransaction(requestPojoObject);
    	}
    	storageLayerObject.updateTransactionRequest(requestPojoObject, action);
    }
    
	public void updateCustomerStatus(int customerId,boolean status) {
		String requestStatus;
		String customerStatus;
    	if(status) {
    		customerStatus="Active";
    		requestStatus="Accepted";
    	}
    	else {
    		customerStatus="InActive";
    		requestStatus="Rejected";
    	}
		storageLayerObject.updateCustomerStatus(customerId, requestStatus, customerStatus);
	}
	
	public List<AccountStatusRequest> getAllAccountStatusRequest() throws CustomException{
		if(storageLayerObject.getAllAccountStatusRequest().isEmpty()) {
			throw new CustomException("No Records Available !");
		}
		return storageLayerObject.getAllAccountStatusRequest();
	}	
	public void updateAccountStatus(Account account,boolean status) {
		String accountStatus;
		String requestStatus;
    	if(status) {
    		requestStatus="Accepted";
    		accountStatus="Active";
    	}
    	else {
    		requestStatus="Rejected";
    		accountStatus="Inactive";
    	}
    	storageLayerObject.updateAccountStatus(account, accountStatus, requestStatus);
	}

}
