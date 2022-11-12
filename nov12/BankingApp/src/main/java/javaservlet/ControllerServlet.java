package javaservlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import help.HelperException;
import logical.AdminFunctionality;
import logical.UserFunctionality;
import pojo.AccountInfo;
import pojo.TransactionInfo;
import util.HelperUtils;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	UserFunctionality home;
	AdminFunctionality admin;
	private static final long serialVersionUID = 1L;

	public ControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardController(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardController(request, response);
	}

	private void forwardController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession banking=request.getSession(true);
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("login")) {
			home=new UserFunctionality();
			int id=Integer.parseInt(request.getParameter("username"));
			banking.setAttribute("id", id);
			String type="";
			String password=String.valueOf(request.getParameter("password"));
			try {
				type=home.checkLogin(id, password);
				banking.setAttribute("type", type);
			} catch (HelperException e) {
				request.setAttribute("errormessage", "Connection error");
			}
			if(Objects.isNull(type)) {
				request.setAttribute("errormessage", "Invaild login");
				RequestDispatcher dispatch=request.getRequestDispatcher("jsp/login.jsp");
				dispatch.forward(request, response);
			}
			else {
				action=type;
			}
		}
		switch(action) {

		case("admin"):{
			admin=new AdminFunctionality();
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("customer"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("customerhome"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("adminhome"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("changepassword"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("savepassword"):{
			String password=String.valueOf(request.getParameter("newpassword"));
			String newpassword=String.valueOf(request.getParameter("reentry"));
			if(password.equalsIgnoreCase(newpassword)) {
				try {
					banking=request.getSession(false);
					home.changePassword((Integer)banking.getAttribute("id"), request.getParameter("oldpassword"), password);
				} catch (HelperException e) {
					request.setAttribute("message", e.getMessage());
				}
			}
			else {
				request.setAttribute("message", "Password Mismatch");
			}
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/changepassword.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("viewalluser"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			try {
				request.setAttribute("allUserDetails", admin.viewAllUserDetails());
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			dispatch.forward(request, response);
			break;
		}
		case("viewallaccount"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {
				request.setAttribute("allaccountdetails", admin.viewAllAccount());
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("adduser"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("addnewuser"):{
			String userType=request.getParameter("type");
			String path="";
			try {

				if(userType.equalsIgnoreCase("customer")) {
					int id=admin.createNewUser(request.getParameter("username"), 
							request.getParameter("email"), 
							HelperUtils.convertIntoMillisecond(String.valueOf(request.getParameter("dateofbirth"))),
							Long.parseLong(request.getParameter("mobile")), 
							request.getParameter("address"), userType);
					admin.createNewCustomer(id, Long.parseLong(request.getParameter("aadhaar")), request.getParameter("pancard"));
					request.setAttribute("userId", id);
					path="jsp/addaccount.jsp";

				}else {
					int id=admin.createNewUser(request.getParameter("username"), 
							request.getParameter("email"), 
							HelperUtils.convertIntoMillisecond(String.valueOf(request.getParameter("dateofbirth"))),
							Long.parseLong(request.getParameter("mobile")), 
							request.getParameter("address"), userType);
					request.setAttribute("userId", id);
					request.setAttribute("message", "Successfully created");
					path="jsp/adduser.jsp";
				}
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
				path="jsp/adduser.jsp";
			}

			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("addaccount"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("viewalltransaction"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id"),"active"));
			} catch (HelperException e) {
				request.setAttribute("path", e.getMessage());
			}
			request.setAttribute("visible", "show");
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("viewtransaction"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			request.setAttribute("search", "show");
			request.setAttribute("visible", "show");
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("showaccount"):{
			Integer id=Integer.parseInt(request.getParameter("id"));
			request.setAttribute("cid", id);
			try {
				Map<Long,AccountInfo> accountInfo=home.getAccounts(id);
				if((accountInfo.size())==0) {
					request.setAttribute("message", "invalid id");
					request.setAttribute("search", "show");
				}else {
					request.setAttribute("accountInfo", accountInfo);
				}
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			request.setAttribute("visible", "show");
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/viewalltransaction.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("showusertransaction"):{
			Long accountNumber=Long.parseLong(request.getParameter("accountNumber"));
			String path="";
			try {banking=request.getSession(false);
			if(((String)banking.getAttribute("type")).equalsIgnoreCase("admin")) {
				List<TransactionInfo> transaction= home.getTransactionDetails(accountNumber);
				request.setAttribute("transactionDetails",transaction);
				int id=Integer.parseInt(request.getParameter("id"));
				request.setAttribute("accountInfo", home.getAccounts(id));
				request.setAttribute("cid", id);
				path="jsp/viewalltransaction.jsp";
			}
			else {
				int day=Integer.parseInt(request.getParameter("days"));
				request.setAttribute("transactionDetails", home.getTransactionDetails(accountNumber,day));
				request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id"),"active"));
				path="jsp/transaction.jsp";
			}
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}

			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("withdraw"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id"),"active"));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("makewithdraw"):{
			Long accountNumber=Long.parseLong(request.getParameter("accountNumber"));
			double amount=Double.parseDouble(request.getParameter("amount"));
			try {banking=request.getSession(false);
			home.withdraw((Integer)banking.getAttribute("id"), accountNumber, amount);
			request.setAttribute("message", "Transaction successFull");
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			} 
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/withdraw.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("transfer"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id"),"active"));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("maketransfer"):{
			Long accountNumber=Long.parseLong(request.getParameter("accountNumber"));
			Long receiverAccountNumber=Long.parseLong(request.getParameter("receiver"));
			if(accountNumber.equals(receiverAccountNumber)) {
				request.setAttribute("message", "Cannot Do Transfer to Same Account");
			}
			else {
				double amount=Double.parseDouble(request.getParameter("amount"));
				try {banking=request.getSession(false);
				home.transfer((Integer)banking.getAttribute("id"), accountNumber, receiverAccountNumber, amount);
				request.setAttribute("message", "Transaction successFull");
				} catch (HelperException e) {
					request.setAttribute("message", e.getMessage());
				} 
			}
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/transfer.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("checkprofile"):{
			Integer customerId=Integer.parseInt(request.getParameter("userId"));
			try {
				request.setAttribute("customer", admin.viewCustomerProfile(customerId));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			request.setAttribute("userId", customerId);
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/addaccount.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("addnewaccount"):{
			try {
				banking=request.getSession(false);
				long accountNumber=admin.createNewAccount(Integer.parseInt(request.getParameter("userId")), 0,
						request.getParameter("accounttype"), request.getParameter("branch"));
				home.deposit((Integer)banking.getAttribute("id"),  accountNumber,Double.parseDouble(request.getParameter("balance")));
				request.setAttribute("message", "success");
			}
			catch(HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/addaccount.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("deposit"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id"),"active"));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("makedeposit"):{
			Long accountNumber=Long.parseLong(request.getParameter("accountNumber"));
			double amount=Double.parseDouble(request.getParameter("amount"));
			try {banking=request.getSession(false);
			home.deposit((Integer)banking.getAttribute("id"), accountNumber, amount);
			request.setAttribute("message", "Transaction successFull");
			request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			} 
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/withdraw.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("viewprofile"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("customer", home.getProfile((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("viewadminprofile"):{
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();
			try {banking=request.getSession(false);
			request.setAttribute("customer", admin.getOwnProfile((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("saveprofile"):{
			String path="";
			long mobile=Long.parseLong(request.getParameter("mobile"));
			try {banking=request.getSession(false);
			if(banking.getAttribute("type").equals("customer")) {
				path="jsp/viewprofile.jsp";
				request.setAttribute("customer", home.getProfile((Integer)banking.getAttribute("id")));
				home.updateProfile((Integer)banking.getAttribute("id"),mobile , request.getParameter("email"));
			}
			else {
				path="jsp/viewadminprofile.jsp";
				request.setAttribute("customer", admin.getOwnProfile((Integer)banking.getAttribute("id")));
				admin.updateProfile((Integer)banking.getAttribute("id"), mobile, request.getParameter("email"));

			}
			request.setAttribute("message", "Updation success");
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
				//request.setAttribute("message", "error occured");
				//e.printStackTrace();
				//System.out.println(e.getMessage());

			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}

		case("logout"): {
			banking.invalidate();
			UrlFinder urlFinder=UrlFinder.valueOf(action.toUpperCase());
			String path=urlFinder.getUrl();			
			response.sendRedirect(path);
			break;
		}
		}
	}
}