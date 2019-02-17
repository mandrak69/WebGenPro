<%-- 
    Document   : opsti
    Created on :  primi izbor kroz get i pravi stranicu
    Author     : 
    
Opsti pregled dozvoljenih opcia u meniju.Salje na Opstiservlet 
koji vadi info oizabranoj stavki iz tabele i prikazuje kao info
    
--%>
<%
        String izbor = request.getParameter("izb");
     %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Azururanje - Categories</title>
       

    </head>
    <body>

        <%@include file="header.jsp"%>

         <%=DBQueries.KlasaNaWeb(izbor)%>
        <%@include file="footer.jsp"%>
    </body>
</html>
