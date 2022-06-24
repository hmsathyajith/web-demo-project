package com.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.DBCon;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.model.User;

/**
 * Servlet implementation class LogingServlet
 */
@WebServlet("/user-login")
public class LogingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charsetUTF-8");
		try(PrintWriter out = response.getWriter()){
			out.println("this is login servlet");
			
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			
			UserDao udao = new UserDao(DBCon.getConnection());
			User user = udao.userLogin(email, password);
			
			System.out.println(user);
			
			if(user != null) {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			}else {
				out.println("User login error...");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
