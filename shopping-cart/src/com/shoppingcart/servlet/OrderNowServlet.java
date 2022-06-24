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
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		try (PrintWriter out = response.getWriter()) {
			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {

				String productId = request.getParameter("id");
				int productQuentity = Integer.parseInt(request.getParameter("quentity"));

				if (productQuentity <= 0) {
					productQuentity = 1;
				}

				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQuentity(productQuentity);
				orderModel.setDate(formatter.format(date));

				OrderDao oderDao = new OrderDao(DBCon.getConnection());
				boolean result = oderDao.insetOrder(orderModel);

				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				} else {
					out.println("order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
