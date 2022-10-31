package javaservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardController(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardController(request, response);
	}
	
	private void forwardController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action=request.getParameter("action");
		System.out.println(request.getServletPath( ));
		UrlFinder siva=UrlFinder.valueOf(action.toUpperCase());
		String path=siva.getUrl();
		if(action.equalsIgnoreCase("logout")) {			
			response.sendRedirect(path);
		}
		else {
			RequestDispatcher dispatch=request.getRequestDispatcher(path);
			dispatch.forward(request, response);
		}
	}

}
