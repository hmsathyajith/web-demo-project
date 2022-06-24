package com.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.model.Cart;

/**
 * Servlet implementation class QuentityIncDecServlet
 */
@WebServlet("/quentity-inc-dec")
public class QuentityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Cart c : cart_list) {
						if (c.getId() == id) {
							int quentity = c.getQuentity();
							quentity++;
							c.setQuentity(quentity);
							response.sendRedirect("cart.jsp");
						}
					}
				}else if (action.equals("dec")) {
					for (Cart c : cart_list) {
						if (c.getId() == id && c.getQuentity() > 1) {
							int quentity = c.getQuentity();
							quentity--;
							c.setQuentity(quentity);
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}

			} else {
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
