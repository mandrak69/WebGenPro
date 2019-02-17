<%-- 
    Document   : index
    Created on : Dec 3, 2018, 2:07:04 PM
    Author     : Grupa1
--%>
<%@page import="java.util.Vector"%>
<%@page import="model.A11"%>
<%@page import="database.DBQueries"%>
<%@page import="model.C1"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>



<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Azururanje - vrstekategoirija</title>


    </head>
    <body>

        <%@include file="header.jsp"%>
        
    
    <% Vector<A11> nizA11
                    = DBQueries.getA1(); %>
    <div class="divvrsta" >
        <% for (int i = 0; i < nizA11.size(); i++) {%>
        <input class="dugmevrsta" type="submit" 
               id ="<%=nizA11.get(i).getId()%>"
               onclick="vratiItems()"
               value="<%=nizA11.get(i).getName()%>"> 

        <br>
        <% }%>
    </div>

    <div id="items">

        <p>Izaberite neku kategoriju za pregled</p>
    </div>
    <!-- <img src="slika.jpg">  -->
   
    <%@include file="footer.jsp"%>
</body>
</html>
