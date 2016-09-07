<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body bgcolor="#99a38f">
        <center>
            <c:if test="${address != null}">
            <h1>Fetching Data From A Branch Database</h1> 
            <table cellpadding="0" cellspacing="2" bordercolor=#125610 border="3">
                <tr>
	            <th align="center" height="30" width="100">STREET</th>
	            <th align="center" height="30" width="100">CITY</th>
	            <th align="center" height="30" width="100">STATE</th>
	            <th align="center" height="30" width="100">COUNTRY</th>
	            <th align="center" height="30" width="100">PIN CODE</th>
                </tr>
                <c:set value="${address}" var="address"/>
                <tr>
                    <td><c:out value="${address.getStreet()}"/></td>
                    <td><c:out value="${address.getCity()}"/></td>
                    <td><c:out value="${address.getState()}"/></td>
                    <td><c:out value="${address.getCountry()}"/></td>
                    <td><c:out value="${address.getPincode()}"/></td>
                </tr>                   
            </table>
            </c:if>
	    <br/><br/>   
         <c:if test="${message != null}">
                <script type="text/javascript">
                    alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>" );
                    windows.location.reload();
                </script>
            </c:if>
        </center>
        <b>Go to main page </b><a href="index.jsp" style="font-sise:18px"> click here</a></br></br>
        <br/><a href="logoutController" style="width:300px;"> LOGOUT</a><br/><br/>
    </body>
</html>
