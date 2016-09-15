<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
	<c:redirect url="approverIndexPage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.well {
	background-color: #8ddfe1;
}
</style>
</head>
<body>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert('CLICK OK THE PAGE WILL BE REFRESHED...'
					+ "<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
	<center>
		<h2>CUSTOMER INFORMATION</h2>
		<table>
			<br />
			<br />
			<tr>
				<td align="center" height="40" width="350"><a
					href="getCustomer?customerId=<c:out value="${sessionScope['id']}"/>">CUSTOMER
						DETAIL</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a
					href="getAccountByCustomerId?customerId=<c:out value="${sessionScope['id']}"/>">VIEW ACCOUNT</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a
					href="viewMiniStatementByCustomerId?customerId=<c:out value="${sessionScope['id']}"/>">CUTOMER
						MINI STATEMENT</a></td>
			</tr>
			<tr>
				<td align="center" height="40" width="350"><a
					href="addTransaction">ADD TRANSACTION</a></td>
			</tr>
			</table>
		    <br/><a href="logoutController">LOGOUT</a>
</body>
</html>