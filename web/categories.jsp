<%-- 
    Document   : categories
    
    
    Stranica sa kategorijama . Klik na svaki od linkova salje zahtev
    ProductServletu koji kreira stranicu sa proizvodima. U zahtevu se nalazi
    fiksna vrednost pod parametrom 'ID' koja naznacava ID kategorije
    za koju se kreira stranica sa pripadajucim proizvodima.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kategorije</title>
       

    </head>
    <body>

        <%@include file="header.jsp"%>

        <%=DBQueries.KlasaNaWeb("a11")%>

        <%@include file="footer.jsp"%>
    </body>
</html>
