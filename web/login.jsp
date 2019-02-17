<%-- 
    Document   : login
    Created on : Jun 26, 2018, 5:45:11 PM
    Author     : Jovan

    Stranica sa formom za logovanje korisnika. Logiku logovanja izvrsava
    LoginServlet, koji se pokrece pri kliku na dugme 'Log in'.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roland Music - Login</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/design.js"></script>
    </head>
    <body>

       

        <%if (request.getAttribute("errorMessage") == "invalid") {%>
        <script>userInvalid();</script>
        <%}%>

        <%@include file="header.jsp"%>

        <div id="loginscreen">
            <form id="loginform" action="LoginServlet" method="post">
                <label>Username: </label><input type="text" name="tb_username" /><br>
                <br>
                <label>Password: </label><input type="password" name="tb_password" /><br>
                <br>
                <input type="submit" value="Log in" />
                 <c:if test="${sessionScope.logged==null}">
            <a href="register.jsp" class="myDugme">Register</a>
        </c:if>
            </form> 
           
        </div>
       
        <%@include file="footer.jsp"%>
    </body>
</html>
