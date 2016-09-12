<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
	<c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
	<c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>

	<h1>VIEW BRANCH BY ID</h1>


	<center>
		<h2>
			Choose Branch Id
			</h3>
			<form action="getBranch" method="get">
				<tr>
					<td><input type="text" name="ifsc" placeholder="IFSC" required></td>
					<td><input type="submit" name="view" value="view"></td>
				</tr>
				</br> </br>
			</form>
			<c:if test="${message != null}">
				<script type="text/javascript">
					alert('CLICK OK THE PAGE WILL BE REFRESHED...'
							+ "<c:out value='${message}'/>");
					windows.location.reload();
				</script>
			</c:if>
			<c:if test="${branch != null}">
				<h3>Fetching Data From A Branch Database</h3>
				<table class="table table-striped">
					<tr>
						<th>IFSC CODE</th>
						<th>EMAIL ID</th>
						<th>ADDRESS ID</th>
						<th>ADDRESS</th>
						<th>DALETE</th>
					</tr>
					<c:set value="${branch}" var="branch" />
					<tr>
						<td align="center"><c:out value="${branch.getIFSCode()}" /></td>
						<br />
						<br />
						<td align="center"><c:out value="${branch.getEmailId()}" /></td>
						<br />
						<br />
						<c:choose>
							<c:when test="${null == branch.getAddress()}">
								<td align="center"><c:out value="${'No Address Allocated'}" /></td>
								<br />
								<br />
							</c:when>
							<c:otherwise>
								<td align="center"><c:set value="${branch.getAddress()}"
										var="address" /></td>
								<td align="center"><a
									href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>"
									style="color: blue">VIEW</a></td>
								<br />
								<br />
							</c:otherwise>
						</c:choose>
						<td><a
							href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>"
							style="color: blue">Delete</a></td>
					</tr>
				</table>
			</c:if>
			<c:if test="${branches != null}">
				<h3>Fetching Data From A Project Management System</h3>
				<table class=" table table-bordered">
					<tr>
						<th>S.NO</th>
						<th>IFSC CODE</th>
						<th>EMAIL</th>
						<th>ADDRESS</th>
						<th>ACTION</th>
					</tr>
					<%
						int sno = 1;
					%>
					<c:forEach items="${branches}" var="branches">
						<tr>
							<td><c:out value="<%=sno%>" /></td>
							<td><c:out value="${branches.getIFSCode()}" /></td>
							<td><c:out value="${branches.getEmailId()}" /></td>
							<c:choose>
								<c:when test="${null == branches.getAddress()}">
									<td><c:out value="${'No Address Allocated'}" /></td>
								</c:when>
								<c:otherwise>
									<c:set value="${branches.getAddress()}" var="address" />
									<td><a
										href="viewBranchAddress?addressId=<c:out value="${address.addressId}"/>"
										style="color: blue">VIEW</a></td>
								</c:otherwise>
							</c:choose>
							<td><a
								href="deleteBranchById?ifsc=<c:out value="${branches.getIFSCode()}"/>"
								style="color: blue">Delete</a></td>
						</tr>
						<%
							sno++;
						%>
					</c:forEach>
				</table>
			</c:if>
			<b>Go to main page </b><a href="BranchIndex" style="font-sise: 18px">
				click here</a> <br /> <a href="logoutController" style="width: 300px;">
				LOGOUT</a>
	</center>
</html>