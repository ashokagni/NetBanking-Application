<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
    <h2>NET BANKING APPLICATION</h2>
    <h4>INSERT EMAIL ID</h4>
    <style type="text/css">
    input[type=submit] {
             width: 20%;
             background-color: #4CAF50;
             color: white;
             padding: 14px 20px;
             margin: 8px 0;
             border: none;
             border-radius: 100px;
             cursor: pointer;
             outline:none;
         }
         input[type=text] {
             width: 20%;
             padding: 12px 20px;
             margin: 8px 0;
             display: inline-block;
             border: 1px solid #ccc;
             border-radius: 100px;
             box-sizing: border-box;
             outline:none;
         }
    </style>
    </head>
    <body bgcolor="#99a38f">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <center>
          <form:form method="post" action="insertBranch" modelAttribute="Branch">
                <form:input path="emailId" type="text" placeholder="EMAIL ID" />
                <br/>
                <input type="submit" path="addBranch" value="ADD" />
            </form:form>
         <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
     <b>Go to main page </b><a href="BranchIndex" style="font-sise:18px"> click here</a></br></br>
     </center>
</body>
</html>