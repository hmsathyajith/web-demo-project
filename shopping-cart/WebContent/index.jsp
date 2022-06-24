<%@page import="java.util.ArrayList"%>
<%@page import="com.shoppingcart.connection.DBCon"%>
<%@page import="com.shoppingcart.model.*"%>
<%@page import="com.shoppingcart.dao.*"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DBCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Shopping Cart</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">

			<%
				if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="product-image/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getImage()%></h5>
						<h6 class="price">
							Priec
							<%=p.getPrice()%></h6>
						<h6 class="category">
							Category :<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add
								to cart</a> <a href="order-now?quentity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy now</a>
						</div>

					</div>
				</div>
			</div>
			<%
				}
			}
			%>

		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>