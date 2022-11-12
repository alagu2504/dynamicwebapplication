package customcheck;

import customexceptionpackage.CustomException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bankingapplicationPojos.Account;
import bankingapplicationPojos.Customer;
import bankingapplicationPojos.User;


public class CustomCheck {
	
	public static void isNull(Object input) throws CustomException{
		if(input==null) {
			throw new CustomException("Given input should Not Be Null");
		}
}//end of isNUll
	
	public static boolean matcher(Pattern patternObject,String input) {
        Matcher matcherObject = patternObject.matcher(String.valueOf(input));
        return  matcherObject.find();
	}
	
	public static long getMillis(int days) {
		Instant currentInsatnt=Instant.ofEpochMilli(System.currentTimeMillis());
		Instant preferedDate=currentInsatnt.minus(Period.ofDays(days));
		return preferedDate.toEpochMilli();
	}
	
	public static void customerValidation(Customer customer) throws CustomException{
		String userName=customer.getUserName().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(customer.getDataOfBirth(), formatter);
    	Pattern mobileNoPattern=Pattern.compile("^\\d{10}$");
        boolean isMobileNumberVerified = matcher(mobileNoPattern,String.valueOf(customer.getMobileNumber()));
        String address=customer.getAddress().trim();
        Pattern emailId=Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}$");
        boolean isEmailIdVerified=matcher(emailId,customer.getEmailId());
		Pattern passwordPattern=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%&]).{8,15}$");
		boolean isPasswordVerified=matcher(passwordPattern,customer.getPassword());
		boolean isAadhaarVerified=matcher(Pattern.compile("^\\d{12}$"),String.valueOf(customer.getAadhaarNumber()));
		boolean isPanVerified=matcher(Pattern.compile("(^[A-Z]{5}[0-9]{4}[A-Z])"),customer.getPanNumber());
		if(userName.equalsIgnoreCase("")) {
			throw new CustomException("UserName Cannot Be Empty !");
		}
		if(localDate.getYear()>Calendar.getInstance().get(Calendar.YEAR)) {
			throw new CustomException("Enter Date of Birth Correctly !");
		}
		if(isMobileNumberVerified==false) {
			throw new CustomException("Enter Correct Mobile Number !");
		}
		if(address.equalsIgnoreCase("")) {
			throw new CustomException("Address Cannot be Empty !");
		}
		if(isEmailIdVerified==false) {
			throw new CustomException("Enter Email Id Correctly !");
		}
		if(isPasswordVerified==false) {
			throw new CustomException("Enter Strong Password !");
		}
		if(isAadhaarVerified==false) {
			throw new CustomException("Enter Valid Aadhaar Number!");
		}
		if(isPanVerified==false) {
			throw new CustomException("Enter Valid Pan Number !");
		}
	}
	
	
	public static void userValitation(User user) throws CustomException {
		String userName=user.getUserName().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(user.getDataOfBirth(), formatter);
    	Pattern mobileNoPattern=Pattern.compile("^\\d{10}$");
        boolean isMobileNumberVerified = matcher(mobileNoPattern,String.valueOf(user.getMobileNumber()));
        String address=user.getAddress().trim();
        Pattern emailId=Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}$");
        boolean isEmailIdVerified=matcher(emailId,user.getEmailId());
		Pattern passwordPattern=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%&]).{8,15}$");
		boolean isPasswordVerified=matcher(passwordPattern,user.getPassword());
		if(userName.equalsIgnoreCase("")) {
			throw new CustomException("UserName Cannot Be Empty !");
		}
		if(localDate.getYear()>Calendar.getInstance().get(Calendar.YEAR)) {
			throw new CustomException("Enter Date of Birth Correctly !");
		}
		if(isMobileNumberVerified==false) {
			throw new CustomException("Enter Correct Mobile Number !");
		}
		if(address.equalsIgnoreCase("")) {
			throw new CustomException("Address Cannot be Empty !");
		}
		if(isEmailIdVerified==false) {
			throw new CustomException("Enter Email Id Correctly !");
		}
		if(isPasswordVerified==false) {
			throw new CustomException("Enter Strong Password !");
		}
	}
	
	public static void accountValidation(Account account) throws CustomException {
		Pattern accountNumberPattern=Pattern.compile("^(\\d{17})$");
        boolean match=matcher(accountNumberPattern,String.valueOf(account.getAccountNumber()));
    	String accountType=account.getAccountType().trim();
    	String ifscCode=account.getIfscCode().trim();
    	String branchName=account.getBranchName().trim();
    	long balance=account.getBalance();
    	if(match==false) {
    		throw new CustomException("Enter Account Number Correctly !");
    	}
    	if(accountType.equalsIgnoreCase("")) {
    		throw new CustomException("Account Type Mismatch !");
    	}
    	if(ifscCode.equalsIgnoreCase("")) {
    		throw new CustomException("Ifsc Code MisMatch !");
    	}
    	if(branchName.equalsIgnoreCase("")) {
    		throw new CustomException("Branch Name Mismatch !");
    	}
    	if(balance<0) {
    		throw new CustomException("Enter Balance Correctly !");
    	}
	}
}
	
