package javaservlet;

import java.io.IOException;
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("customer"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("customerhome"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("adminhome"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("changepassword"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("savepassword"):{
			String password=String.valueOf(request.getParameter("newpassword"));
			String newpassword=String.valueOf(request.getParameter("reentry"));
			if(password.equalsIgnoreCase(newpassword)) {
				try {
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("addnewuser"):{
			String userType=request.getParameter("type");
			String path="";
			try {
				int id=admin.createNewUser(request.getParameter("username"), 
						request.getParameter("email"), 
						HelperUtils.convertIntoMillisecond(String.valueOf(request.getParameter("dateofbirth"))),
						Long.parseLong(request.getParameter("mobile")), 
						request.getParameter("address"), userType);
				request.setAttribute("userId", id);
				if(userType.equalsIgnoreCase("customer")) {
					path="jsp/addaccount.jsp";

				}else {
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("viewalltransaction"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			request.setAttribute("visible", "show");
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("showaccount"):{
			Integer id=Integer.parseInt(request.getParameter("id"));
			request.setAttribute("cid", id);
			try {
				request.setAttribute("accountInfo", home.getAccounts(id));
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
			try {
				request.setAttribute("transactionDetails", home.getTransactionDetails(accountNumber));
				if(((String)banking.getAttribute("type")).equalsIgnoreCase("admin")) {
					int id=Integer.parseInt(request.getParameter("id"));
					request.setAttribute("accountInfo", home.getAccounts(id));
					request.setAttribute("cid", id);
					path="jsp/viewalltransaction.jsp";
				}
				else {
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
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
			try {
				home.withdraw((Integer)banking.getAttribute("id"), accountNumber, amount);
				request.setAttribute("message", "Transaction successFull");
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			} 
			try {
				request.setAttribute("accountInfo", home.getAccounts((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher("jsp/withdraw.jsp");
			dispatch.forward(request, response);
			break;
		}
		case("transfer"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
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
				try {
					home.transfer((Integer)banking.getAttribute("id"), accountNumber, receiverAccountNumber, amount);
					request.setAttribute("message", "Transaction successFull");
				} catch (HelperException e) {
					request.setAttribute("message", e.getMessage());
				} 
			}
			try {
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
			try {;;
				long accountNumber=admin.createNewAccount(Integer.parseInt(request.getParameter("userId")), request.getParameter("pancard"),
						Long.parseLong(request.getParameter("aadhaar")), 0,
						request.getParameter("accounttype"), request.getParameter("ifsccode"), request.getParameter("branch"));
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
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
			try {
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
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
				request.setAttribute("customer", home.getProfile((Integer)banking.getAttribute("id")));
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
			break;
		}
		case("viewadminprofile"):{
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();
			try {
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
			Long mobile=Long.parseLong(request.getParameter("mobile"));
			try {
				if(banking.getAttribute("type").equals("customer")) {
					path="jsp/viewprofile.jsp";
					home.updateProfile((Integer)banking.getAttribute("id"),mobile , request.getParameter("email"));
					request.setAttribute("customer", home.getProfile((Integer)banking.getAttribute("id")));
				}
				else {
					path="jsp/viewadminprofile.jsp";
					admin.updateProfile((Integer)banking.getAttribute("id"), mobile, request.getParameter("email"));
					request.setAttribute("customer", admin.getOwnProfile((Integer)banking.getAttribute("id")));
				}
				request.setAttribute("message", "Updation success");
			} catch (HelperException e) {
				request.setAttribute("message", e.getMessage());
			}			
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			System.out.println(path);
			dispatch.forward(request, response);
			break;
		}

		case("logout"): {
			banking.invalidate();
			UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
			String path=siva.getUrl();			
			response.sendRedirect(path);
			break;
		}
		}
	}
}