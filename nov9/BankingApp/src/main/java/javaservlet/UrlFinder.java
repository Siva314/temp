package javaservlet;

public enum UrlFinder {
	ADMIN("jsp/adminframe.jsp"),
	CUSTOMER("jsp/customerframe.jsp"),
	ADMINHOME("jsp/adminhome.jsp"),
	CUSTOMERHOME("jsp/customerhome.jsp"),
	CHANGEPASSWORD("jsp/changepassword.jsp"),
	VIEWALLUSER("jsp/viewallusers.jsp"),
	VIEWALLACCOUNT("jsp/viewallaccount.jsp"),
	ADDUSER("jsp/adduser.jsp"),
	ADDACCOUNT("jsp/addaccount.jsp"),
	VIEWALLTRANSACTION("jsp/transaction.jsp"),
	VIEWTRANSACTION("jsp/viewalltransaction.jsp"),
	VIEWADMINPROFILE("jsp/viewadminprofile.jsp"),
	VIEWPROFILE("jsp/viewprofile.jsp"),
	DEPOSIT("jsp/deposit.jsp"),
	WITHDRAW("jsp/withdraw.jsp"),
	TRANSFER("jsp/transfer.jsp"),
	LOGOUT("jsp/login.jsp");
	
	private final String url;

	UrlFinder(String url) {
		this.url=url;
	}
	
	public String getUrl() {
		return url;
	}
}
