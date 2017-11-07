package Servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Principals.Authenticator;
import Principals.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@SuppressWarnings("unused")
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginServlet() {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User(username, password);
		user.setUsername(username);
		user.setPassword(password);

		Authenticator authenticator = new Authenticator();
		String userValidate = authenticator.login(username, password);
		if(userValidate.equals("SUCCESS")) //If function returns success string then user will be rooted to Home page
		{
			request.setAttribute("username", username); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
			request.getRequestDispatcher("/home.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
		}
		else
		{
			request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
			request.getRequestDispatcher("/login.jsp").forward(request, response);//forwarding the request
		}
	}

}

