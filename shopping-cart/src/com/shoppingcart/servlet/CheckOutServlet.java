package com.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.DBCon;
import com.shoppingcart.dao.OrderDao;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.Order;
import com.shoppingcart.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			
			if(cart_list != null && auth != null) {
				for(Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuentity(c.getQuentity());
					order.setDate(formatter.format(date));
					
					OrderDao oDao = new OrderDao(DBCon.getConnection());
					boolean result = oDao.insetOrder(order);
					if(!result) {
						break;
					}
				}
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");
				
			}else {
				if(auth == null) {
					response.sendRedirect("login.jsp");
				}else {
					response.sendRedirect("cart.jsp");
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
