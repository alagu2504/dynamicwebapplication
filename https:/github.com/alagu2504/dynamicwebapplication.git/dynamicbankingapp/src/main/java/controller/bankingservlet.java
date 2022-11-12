package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankingapplicationPojos.Account;
import bankingapplicationPojos.AccountStatusRequest;
import bankingapplicationPojos.Customer;
import bankingapplicationPojos.CustomerStatusRequest;
import bankingapplicationPojos.Statements;
import bankingapplicationPojos.TransactionRequest;
import bankingapplicationPojos.User;
import customexceptionpackage.CustomException;
import users.Admin;
import users.Operations;


@WebServlet("/bankingservlet")
public class bankingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public bankingservlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scope=request.getParameter("action");
		Operations userObject=new Operations();

		//		String url = request.getServletPath();
		//		System.out.println(url);
		//		System.out.println("OUTSIDE SWITCH :"+scope);
		switch(scope) {
		case "Login":{
			String error="";

			try {
				int userId=Integer.parseInt(request.getParameter("userId"));
				String password=request.getParameter("password");
				boolean isAvailable=false;
				isAvailable=userObject.loginMethod(userId, password);
				if(isAvailable) {
					User user=userObject.getUserDetails(userId);
					String role=user.getRole().trim();
					HttpSession session=request.getSession(true);  
					session.setAttribute("userId",userId);
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("role", role);
					Admin admin=new Admin();
					Map<Integer,Customer> customerDetails=new TreeMap<>(admin.getActiveCustomer());
					session.setAttribute("activeCustomer", customerDetails);

					if(role.equalsIgnoreCase("Customer")) {
						Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
						session.setAttribute("activeAccounts", activeAccounts);
						Customer customer=userObject.getCustomerDetails(userId);
						if(customer.getStatus().trim().equalsIgnoreCase("Inactive")) {
							error="Oops! You are Currently InActive.";
							request.setAttribute("error", error);
							RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/customerstatusrequest.jsp");
							dispatcher.forward(request, response);
							break;
						}
						RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/customer.jsp");
						dispatcher.forward(request, response);
					}
					else if(role.equalsIgnoreCase("Admin")) {
						RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/adminmain.jsp");
						dispatcher.forward(request, response);
					}
				}
				else {
					request.setAttribute("error", error);
					RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
					dispatcher.forward(request, response);
				}
			} 
			catch (ClassNotFoundException e) {
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}
			catch (NumberFormatException e) {
				error="Invalid Credentials !";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
				dispatcher.forward(request, response);
			}
			catch (CustomException e) {
				error="Invalid Credentials !";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		}//end of login

		case "Home":{
			HttpSession session=request.getSession(false);  
			Admin adminObject=new Admin();
			Map<Integer,Map<Long,Account>> accountDetails=adminObject.getAllAccountDetailsOfCustomer();
			Map<Long,Account> accounts=accountDetails.get(session.getAttribute("userId"));
			request.setAttribute("accountDetails", accounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/accountdetails.jsp");
			dispatcher.forward(request, response);
			break;
		}
//		case "Send Request":{
//			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/customerstatusrequest.jsp");
//			dispatcher.forward(request, response);
//			break;
//		}

		case "Change Customer Status":{

			int userId=Integer.parseInt(request.getParameter("userId"));
			String password=request.getParameter("password");
			String error="";
			boolean isAvailable=false;
			try {
				isAvailable=userObject.loginMethod(userId, password);
				if(isAvailable) {
					HttpSession session=request.getSession(true);  
					session.setAttribute("userId",userId);
					User user=userObject.getUserDetails(userId);
					String role=user.getRole().trim();
					Customer customer=userObject.getCustomerDetails(userId);
					if(role.equalsIgnoreCase("Customer")&&customer.getStatus().equalsIgnoreCase("InActive")) {
						userObject.customerStatusRequest(userId, request.getParameter("description"));
					}	 
				}
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
			}
			request.setAttribute("error", error);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
			dispatcher.forward(request, response);
			break;
		}
		case "Deposit":{
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccounts", activeAccounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/deposit.jsp");
			dispatcher.forward(request, response);
			break;
		}//end of deposit


		case "DEPOSIT AMOUNT":{
			String error="Deposit Completed Successfully";
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			try {
				userObject.depositMethod(Long.parseLong(request.getParameter("depositAmount")), Long.parseLong(request.getParameter("accountnumber")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
				request.setAttribute("activeAccounts", activeAccounts);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/deposit.jsp");
				dispatcher.forward(request, response);
				break;
			} 
			request.setAttribute("error", error);
			Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccounts", activeAccounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/deposit.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "Withdraw":{
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccounts", activeAccounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/withdraw.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "WITHDRAW":{
			String error="Withdraw Request Submitted";
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			try {
				userObject.withdrawMethod(Long.parseLong(request.getParameter("withdrawAmount")), Long.parseLong(request.getParameter("accountnumber")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
				request.setAttribute("activeAccounts", activeAccounts);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/withdraw.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error", error);
			Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccounts", activeAccounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/withdraw.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "Transfer":{
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccounts", activeAccounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/transfer.jsp");
			dispatcher.forward(request, response);
			break;
		}


		case "TRANSFER":{
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			String error="Transfer Completed Successfully";
			try {
				userObject.transactionMethod(Long.parseLong(request.getParameter("senderAccountnumber")), Long.parseLong(request.getParameter("receiveraccount")), Long.parseLong(request.getParameter("transferamount")), request.getParameter("password"));
				request.setAttribute("error", error);
				Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
				request.setAttribute("activeAccounts", activeAccounts);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/transfer.jsp");
				dispatcher.forward(request, response);
				break;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				Map<Long,Account> activeAccounts=admin.getActiveAccounts((int)session.getAttribute("userId"));
				request.setAttribute("activeAccounts", activeAccounts);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/transfer.jsp");
				dispatcher.forward(request, response);
				break;
			}

		}

		case "Account Details":{
			HttpSession session=request.getSession(false);  
			Admin adminObject=new Admin();
			Map<Integer,Map<Long,Account>> accountDetails=adminObject.getAllAccountDetailsOfCustomer();
			Map<Long,Account> accounts=accountDetails.get(session.getAttribute("userId"));
			request.setAttribute("accountDetails", accounts);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/accountdetails.jsp");
			dispatcher.forward(request, response);
			break;
		}
		case "ACCOUNT INFO":{
			HttpSession session=request.getSession(false);  
			Admin adminObject=new Admin();
			Map<Integer,Map<Long,Account>> accountDetails=adminObject.getAllAccountDetailsOfCustomer();
			Map<Long,Account> accounts=accountDetails.get(session.getAttribute("userId"));
			Account account=accounts.get(Long.parseLong(request.getParameter("accountNumber")));
			request.setAttribute("account", account);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/accountinfo.jsp");
			dispatcher.forward(request, response);
			break;
		}


		case "Transaction Statements":{
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> activeAccount=admin.getActiveAccounts((int)session.getAttribute("userId"));
			request.setAttribute("activeAccount", activeAccount);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/transactionstatement.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "Active Account":{
			String error="";
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> inActiveAccount=new HashMap<>();
			try {
				inActiveAccount= admin.getInactiveAccount((int)session.getAttribute("userId"));
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error",error);
                request.setAttribute("inActiveAccounts", inActiveAccount);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/activeaccount.jsp");
				dispatcher.forward(request, response);
				break;
			}
            request.setAttribute("inActiveAccounts", inActiveAccount);
			request.setAttribute("error",error);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/activeaccount.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "Active Account Request":{
			String error="Request Send Successfully";
			HttpSession session=request.getSession(false);  
			Admin admin=new Admin();
			Map<Long,Account> inActiveAccount=new HashMap<>();
			try {
			admin.accountStatusRequest(Long.parseLong(request.getParameter("accountnumber")), request.getParameter("describtion"));
			inActiveAccount= admin.getInactiveAccount((int)session.getAttribute("userId"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error",error);
	            request.setAttribute("inActiveAccounts", inActiveAccount);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/activeaccount.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error",error);
            request.setAttribute("inActiveAccounts", inActiveAccount);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/activeaccount.jsp");
			dispatcher.forward(request, response);
			break;
		}



		case "View Users Details":{
			Admin admin=new Admin();
			Map<Integer,User> usersDetails=new TreeMap<>(admin.getAllUsersDetails());
			request.setAttribute("usersDetails", usersDetails);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allusersdetails.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "View Customer Details":{
			Admin admin=new Admin();
			Map<Integer,Customer> customerDetails=admin.getAllCustomerDetails();
			Map<Integer,Customer> treeMap=new TreeMap<>(customerDetails);
			request.setAttribute("customerDetail", treeMap);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerdetails.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "View Account Details":{
			Admin admin=new Admin();
			Map<Integer,Map<Long,Account>> allAccountDetails=admin.getAllAccountDetailsOfCustomer();
			request.setAttribute("allAccountDetails", allAccountDetails);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allaccountsdetails.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "STATEMENTS":{
			Operations user=new Operations();
			Map<Integer,Statements> statements=new TreeMap<>();
			if(request.getParameter("days").trim()=="") {
			 statements=user.getTransactionStatements(Long.parseLong(request.getParameter("accountNumber")),0);
			 request.setAttribute("statement", statements);
			 RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/statement.jsp");
			 dispatcher.forward(request, response);
			 break;
			}
			statements=user.getTransactionStatements(Long.parseLong(request.getParameter("accountNumber")),Integer.parseInt(request.getParameter("days")));
			request.setAttribute("statement", statements);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/statement.jsp");
			dispatcher.forward(request, response);
			break;
		}

//		case "SPECIFIC STAEMENT":{
//			System.out.println("state");
//			Operations user=new Operations();
//			Map<Integer,Statements> statements=user.getTransactionStatements(Long.parseLong(request.getParameter("accountNumber")));
//
//			request.setAttribute("statement", statements);
//			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/specifictransactiondetails.jsp");
//			dispatcher.forward(request, response);
//			break;
//		}

		case "View Transaction Details":{
			Admin admin=new Admin();
			Map<Integer,Statements> secondMap=new HashMap<>();
			Map<Long,Map<Integer,Statements>> allStatements=admin.getAllStatements();
			Iterator<Long> iteratorObject=allStatements.keySet().iterator();
			while(iteratorObject.hasNext()){
				Map<Integer,Statements> mapOfStatements=allStatements.get(iteratorObject.next());
				secondMap.putAll(mapOfStatements);
			}
			Map<Integer,Statements> treeMap=new TreeMap<Integer,Statements>(secondMap);
			request.setAttribute("allStatements", treeMap);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/alltransactiondetails.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "View Customer Status Request":{
			String error="";
			Admin admin=new Admin();
			try {
				Map<Integer, CustomerStatusRequest> customerStatusRequest = admin.getAllCustomerStatusRequest();
				request.setAttribute("statusRequest",customerStatusRequest);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error",error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			}


		}

		case "Update Customer Status Request":{
			String error="";
			Admin admin=new Admin();
			admin.updateCustomerStatus(Integer.parseInt(request.getParameter("customerId1")), Boolean.parseBoolean(request.getParameter("response")));
			try {
				Map<Integer, CustomerStatusRequest> customerStatusRequest = admin.getAllCustomerStatusRequest();
				request.setAttribute("customerStatusRequest", customerStatusRequest);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			}

		}

		case "View Account Status Request":{
			String error="";
			Admin admin=new Admin();
			try {
				List<AccountStatusRequest> accountActiveRequest = admin.getAllAccountStatusRequest();
				request.setAttribute("accountActiveRequest", accountActiveRequest);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allaccountstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allaccountstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			}

		}

		case "Update Account Status Request":{
			Admin admin=new Admin();
			String error="";
			try {
				Account account=userObject.getAccountDetails(Long.parseLong(request.getParameter("accountNumber")))	;
				admin.updateAccountStatus(account, Boolean.parseBoolean(request.getParameter("response")));
				List<AccountStatusRequest> accountActiveRequest=admin.getAllAccountStatusRequest();
				request.setAttribute("accountActiveRequest", accountActiveRequest);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allaccountstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allaccountstatusrequest.jsp");
				dispatcher.forward(request, response);
				break;
			}

		}

		case "View Withdraw Request":{
			String message="";
			Admin admin=new Admin();
			Map<Integer,TransactionRequest> waitingRequest=new TreeMap<>();
			Map<Integer,TransactionRequest> withdrawRequest=admin.getAllWithdrawRequest();
			for(TransactionRequest transactionRequest:withdrawRequest.values()) {
				if(transactionRequest.getRequestStatus().equalsIgnoreCase("Waiting")) {
					waitingRequest.put(transactionRequest.getRequestId(), transactionRequest);
				}
			}
			if(withdrawRequest.isEmpty()) {
				message="No Records Available !";
			}
			request.setAttribute("error", message);
			request.setAttribute("withdrawRequest", waitingRequest);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/withdrawrequest.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		
		case "Update Request":{
			Admin admin=new Admin();
			String error="";
			TransactionRequest transactionRequest=new TransactionRequest();
			transactionRequest.setSenderAccountNumber(Long.parseLong(request.getParameter("accountNumber")));
			transactionRequest.setRequestId(Integer.parseInt(request.getParameter("requestId")));
			transactionRequest.setTransferAmount(Long.parseLong(request.getParameter("withdrawAmount")));
			transactionRequest.setRequestStatus(request.getParameter("status"));
			try {
				admin.updateTransactionRequest(transactionRequest,request.getParameter("state").trim());
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
			}
			Map<Integer,TransactionRequest> waitingRequest=new TreeMap<>();
			Map<Integer,TransactionRequest> withdrawRequest=admin.getAllWithdrawRequest();
			for(TransactionRequest transactionRequest1:withdrawRequest.values()) {
				if(transactionRequest1.getRequestStatus().equalsIgnoreCase("Waiting")) {
					waitingRequest.put(transactionRequest1.getRequestId(), transactionRequest1);
				}
			}
			request.setAttribute("withdrawRequest", waitingRequest);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/withdrawrequest.jsp");
			dispatcher.forward(request, response);
			break;
		}
		

		case "ADD NEW USER":{
			String message="Successfully Inserted";
			Admin admin=new Admin();
			User user=new User();
			user.setUserName(request.getParameter("name"));
			user.setDataOfBirth(request.getParameter("dateofbirth"));
			user.setMobileNumber(Long.parseLong(request.getParameter("mobilenumber")));
			user.setAddress(request.getParameter("address"));
			user.setEmailId(request.getParameter("emailid"));
			user.setPassword(request.getParameter("password"));
			user.setRole(request.getParameter("role"));
			try {
				admin.insertNewUsers(user);
			} catch (CustomException e) {
				message=e.getMessage();
				request.setAttribute("error", message);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addAdmin.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addAdmin.jsp");
			dispatcher.forward(request, response);
			break;
		}

		

		case "Add New Users":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/adduser.jsp");
			dispatcher.forward(request, response);
			break;
		}
		case "Add New Account":{
			//			Admin admin=new Admin();
			//			Map<Integer,Customer> customerDetails=new TreeMap<>(admin.getActiveCustomer());
			//			request.setAttribute("activeCustomer", customerDetails);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addaccount.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "CREATE ACCOUNT":{
			String message="Account Create Successfully";
			Admin admin=new Admin();
			Account account=new Account();
			account.setAccountNumber(Long.parseLong(request.getParameter("accno")));
			account.setAccountType(request.getParameter("acctype"));
			account.setIfscCode(request.getParameter("ifsccode"));
			account.setBranchName(request.getParameter("branchname"));
			account.setBalance(Long.parseLong(request.getParameter("balance")));
			account.setStatus(request.getParameter("status"));
			account.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			try {
				admin.insertAccountInfo(account);
			} catch (CustomException e) {
				message=e.getMessage();
				request.setAttribute("error", message);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addaccount.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addaccount.jsp");
			dispatcher.forward(request, response);
			break;		
		}

		case "Personal Details":{
			HttpSession session=request.getSession(false);  
			Customer customer=userObject.getCustomerDetails((int)session.getAttribute("userId"));
			request.setAttribute("customer", customer);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/personaldetails.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		case "User Details":{
			HttpSession session=request.getSession(false);  
            User user=userObject.getUserDetails((int)session.getAttribute("userId"));
            request.setAttribute("user", user);
            RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/userinfo.jsp");
			dispatcher.forward(request, response);
			break;
			
		}
		case "Update UserInfo":{
			String error="Updated Successfully";
			HttpSession session=request.getSession(false);  
            User user=new User();
            user.setUserId(Integer.parseInt(request.getParameter("userId").trim()));	
            user.setUserName(request.getParameter("userName").trim());
            user.setDataOfBirth(request.getParameter("dateOfBirth").trim());
            user.setMobileNumber(Long.parseLong(request.getParameter("mobileNumber").trim()));
            user.setAddress(request.getParameter("address").trim());
            user.setEmailId(request.getParameter("emailId").trim());
            user.setRole(request.getParameter("role").trim());
            user.setPassword(request.getParameter("password").trim());
            try {
				userObject.updateUserInfo(user);
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				    User user1=userObject.getUserDetails((int)session.getAttribute("userId"));
		            request.setAttribute("user", user1);
		            RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/userinfo.jsp");
					dispatcher.forward(request, response);
					break;
			}
			request.setAttribute("error", error);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/userinfo.jsp");
			dispatcher.forward(request, response);
			break;
            
		}

		case "SaveInfo":{
            
			String error="";
			HttpSession session=request.getSession(false); 

			Customer customer=new Customer();
			customer.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			customer.setUserName(request.getParameter("userName"));
			customer.setDataOfBirth(request.getParameter("dateOfBirth"));
			customer.setMobileNumber(Long.parseLong(request.getParameter("mobileNumber")));
			customer.setAddress(request.getParameter("address"));
			customer.setEmailId(request.getParameter("emailId"));
			customer.setPassword(request.getParameter("password"));
			//            customer.setCustomerId((int)session.getAttribute("userId"));
			customer.setRole(request.getParameter("role"));
			customer.setAadhaarNumber(Long.parseLong(request.getParameter("aadhaarNumber")));
			customer.setPanNumber(request.getParameter("panCardNumber"));
			customer.setStatus(request.getParameter("status"));
			try {
				userObject.changeInfo(customer);
				request.setAttribute("customer", customer);
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				if(session.getAttribute("role").toString().trim().equalsIgnoreCase("Customer")) {
					customer=userObject.getCustomerDetails((int)session.getAttribute("userId"));
					request.setAttribute("customer", customer);
					RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/personaldetails.jsp");
					dispatcher.forward(request, response);
					break;
				}
				else {
					Admin admin=new Admin();
					Map<Integer,Customer> customerDetails=admin.getAllCustomerDetails();
					Map<Integer,Customer> treeMap=new TreeMap<>(customerDetails);
					request.setAttribute("customerDetail", treeMap);
					RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerdetails.jsp");
					dispatcher.forward(request, response);
					break;
				}
				
			}//end of catch
			if(session.getAttribute("role").toString().equalsIgnoreCase("Customer")) {

				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/personaldetails.jsp");
				dispatcher.forward(request, response);
				break;
			}
			else {
				Admin admin=new Admin();
				Map<Integer,Customer> customerDetails=admin.getAllCustomerDetails();
				Map<Integer,Customer> treeMap=new TreeMap<>(customerDetails);
				request.setAttribute("customerDetail", treeMap);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/allcustomerdetails.jsp");
				dispatcher.forward(request, response);
				break;
			}

		}


		case "Change Password":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/changepassword.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "CHANGE PASSWORD":{
			String error="Password Changed Successfully";
			boolean change=false;
			HttpSession session=request.getSession(false);  
			User user=userObject.getUserDetails((int)session.getAttribute("userId"));
			String role=user.getRole().trim();
			try {
				change=userObject.changePassword((int)session.getAttribute("userId"), request.getParameter("newpassword"),request.getParameter("confirm_password"));
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/changepassword.jsp");
				dispatcher.forward(request, response);
				break;
			}
			//			 if(role.equalsIgnoreCase("Customer")&&change==true) {
			//					Admin adminObject=new Admin();
			//					Map<Integer,Map<Long,Account>> accountDetails=adminObject.getAllAccountDetailsOfCustomer();
			//					Map<Long,Account> accounts=accountDetails.get(session.getAttribute("userId"));
			//		            request.setAttribute("accountDetails", accounts);
			//					request.setAttribute("error", error);
			//					RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/customer/accountdetails.jsp");
			//					dispatcher.forward(request, response);
			//					break;
			//				}
			//				else if(role.equalsIgnoreCase("Admin")&&change==true) {
			//					RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/withdrawrequest.jsp");
			//					dispatcher.forward(request, response);
			//					break;
			//				}
			request.setAttribute("error", error);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/changepassword.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "Forgot Password ?":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/forgetpassword.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "FORGET PASSWORD":{
			String error="Password Changed Successfully";
			int userId=Integer.parseInt(request.getParameter("userId"));
			long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
			try {
				userObject.forgetPassword(userId, mobileNumber, request.getParameter("newpassword"), request.getParameter("confirmPassword"));
			} catch (CustomException e) {
				error=e.getMessage();
				request.setAttribute("error", error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/forgetpassword.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error", error);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
			dispatcher.forward(request, response);
			break;
		}


		case "NEW CUSTOMER":{
			String message="Customer Added Successfully !";
			Admin admin=new Admin();
			Customer customer=new Customer();
			customer.setUserName(request.getParameter("name"));
			customer.setDataOfBirth(request.getParameter("dateofbirth"));
			customer.setMobileNumber(Long.parseLong(request.getParameter("mobilenumber")));
			customer.setAddress(request.getParameter("address"));
			customer.setEmailId(request.getParameter("emailid"));
			customer.setPassword(request.getParameter("password"));
			customer.setRole(request.getParameter("role"));
			customer.setAadhaarNumber(Long.parseLong(request.getParameter("aadhaarNo")));
			customer.setPanNumber(request.getParameter("panCardNo"));
			customer.setStatus(request.getParameter("status"));
			try {
				admin.insertCustomerDetails(customer);
			} catch (CustomException e) {
				message=e.getMessage();
				request.setAttribute("error", message);
				RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addcustomer.jsp");
				dispatcher.forward(request, response);
				break;
			}
			request.setAttribute("error", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addcustomer.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "ADD CUSTOMER":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addcustomer.jsp");
			dispatcher.forward(request, response);
			break;
		}

		case "ADD ADMIN":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/admin/addAdmin.jsp");
			dispatcher.forward(request, response);
			break;	
		}

		case "CHANGE ACCOUNT STATUS":{
			break;
		}

		case "Logout":{
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/loginpage.jsp");
			dispatcher.forward(request, response);
			break;
		}


		}//end of switch


	}

}
