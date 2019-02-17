<%-- 
    Document   : korpa
    
    Pocetna strana webshopa. Na ovu stranu se cesto vrsi redirect u slucaju
    nedozvoljenog pristupa (ako korisnik nije ulogovan, ili nije ulogovan admin).
--%>

<%@page import="database.Constants"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>

<%@page import="database.DBQueries"%>

<%User u = (User) session.getAttribute("user");%>

<!DOCTYPE html>

<html>
    <head>
 <style>
    ul.products li {
    width: 300px;
    display: inline-block;
    text-align: center;
    background-color:#777 ;
    font-family: "Trebuchet MS", Helvetica, sans-serif; 
    border-radius: 0px; 
    border: 1px solid #585858;
   height: 200px;
  background-repeat: no-repeat;
  background-size: contain;

}
</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Korpa</title>
        <% 
            if (session.getAttribute("logged") == null) {
                session.setAttribute("logged", false);
                 u = new User("Guest", " ", " ", "", "", "", 0, 0);
                session.setAttribute("user", u);
            }
        %>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/slideshow.css">
        <script type="text/javascript" src="javascript/design.js"></script>
        <script type="text/javascript" src="javascript/index.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">  

    </head>
     <body >

       

        <div style=" overflow: hidden;
    padding: 0px;
    margin-top: 0%;
    text-align: 
    center;height: 250px;box-sizing: border-box">


           
         
<%=DBQueries.KlasaNaWeb("vrstekategorija")%>



        </div>

     

    </body>

    <%if (request.getAttribute("registerSuccess") == "success") {%>
    <script>registerSuccess();</script>
    <%}%>

    <%if (request.getAttribute("loginSuccess") == "success") {%>
    <script>loginSuccess();</script>
    <%}%>

</html>
