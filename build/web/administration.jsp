<%-- 
    Document   : administration
    Created on : Jun 26, 2018, 5:48:53 PM
    Author     : Jovan

    Stranica za administratorske funkcije. Pristup je dozvoljen samo u slucaju
    kada je administrator ulogovan. Pristup Transaction Listi i User Listi se
    dobija pomocu TransactionListServleta i UserListServleta.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/design.js"></script>
    </head>

    
    <body onload="tabelarno.js">
        <%@include file="header.jsp"%>
        <div id="account">
            <div id="admininfo">
                <h2>Vas racun</h2>
                <div id="accountnavbuttons">
                    <a href="TransactionListServlet" class="accountnavbutton">Transaction List</a>
                    <a href="UserListServlet" class="accountnavbutton">User List</a>
                    <a href="newproduct.jsp" class="accountnavbutton">Add New Product</a>
                    <a href="Tabelarno.jsp" class="accountnavbutton">Azurirajmeni</a>
                </div>
                <a href="Tabelarno.jsp" class="accountnavbutton" onclick='tabelarno.js'>Azurirajmeni</a>
            </div>
            <br>
        </div>
        <%@include file="footer.jsp"%>
    </body>
    <script>
$(document).ready(function(){

var json = [{"name": "name1","email":"email1@domain.com"},{"name": "name2","link":"email2@domain.com"}];
//while running this code the template will be appended in your div with json data
$("#tbody").jPut({
    jsonData:json,
    //ajax_url:"youfile.json",  if you want to call from a json file
    name:"tbody_template",
});

});
</script>   

<table jput="t_template">
 <tbody jput="tbody_template">
     <tr>
         <td>{{name}}</td>
         <td>{{email}}</td>
     </tr>
 </tbody>
</table>

<table>
 <tbody id="tbody">
 </tbody>
</table>
</html>
