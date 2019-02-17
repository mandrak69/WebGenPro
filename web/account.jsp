<%-- 
    

    Stranica za prikaz naloga korisnika pri kliku na dugme Account u headeru.
    Odavde korisnik moze da vidi podatke svog naloga i da pristupi Transaction Listi.
--%>

<%@page import="database.DBQueries"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%User p=(User) session.getAttribute("user");
           ;%>
       
        <title>Account - <%=p.getName()+"/"+session.getAttribute("logged")%></title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/design.js"></script>
    </head>
   
  
    
    
    
    <body>
        <%@include file="header.jsp"%>
        <div id="account">
            <div id="userinfo">
               
                First name: <%out.println(p.getName());%><br>
                Last name: <%out.println(p.getLast_name());%><br>
                Phone number: <%out.println(p.getPhone());%><br>
                E-mail: <%out.println(p.getEmail());%><br>
                Address: <%out.println(p.getAddress());%>
            </div>
            <br>
             <% if (session.getAttribute("logged")=="true"){%>
            <div id="accountnavbuttons">
                <a href="TransactionListServlet" class="accountnavbutton">Vasa korpa</a>
            </div><%}%>
            
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
