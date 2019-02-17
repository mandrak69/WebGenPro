<%-- 
    Document   : newproduct
    Created on : Jul 5, 2018, 4:08:49 PM
    Author     : Jovan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roland Music - Register</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/design.js"></script>
    </head>

    <%if (request.getAttribute("errorMessage") == "exists") {%>
    <script>productExists();</script>
    <%}%>

    <%if (request.getAttribute("errorMessage") == "notjpg") {%>
    <script>imageInvalid();</script>
    <%}%>

    <%if (request.getAttribute("errorMessage") == "empty") {%>
    <script>fieldsEmpty();</script>
    <%}%>

    <%if (request.getAttribute("newproductSuccess") == "success") {%>
    <script>newproductSuccess();</script>
    <%}%>

    <body>

        <%@include file="header.jsp"%>

        <div id="newproductscreen">
            <div id="notice">
                <p>NOTICE: </p>
                <p>Unositenovartikal--pazljivo</p><br><br></div>
            <form id="newproductform" action="NewProductServlet" method="post">
                <label>Product Name: </label><input type="text" name="tb_productName" /><br>
                <br>
                <label>Category: </label><select name="dm_category">
                    <option value=1>v1</option>
                    <option value=2>v2 Guitar</option>
                    <option value=3>v3</option>
                    <option value=4>v4</option>
                </select><br>
                <br>
                <label>Manufacturer: </label><select name="dm_manufacturer">
                    <option value=1>v5</option>
                    <option value=2>v6</option>
                    <option value=3>v7</option>
                    <option value=4>v8</option>
                   
                </select><br>
                <br>
                <label>Price: </label><input type="text" name="tb_price" /><br>
                <br>
                <label>Description: </label><input type="text" name="tb_description" /><br>
                <br>
                <label>Image: </label><input type="file" name="image" accept="image/.jpg"><br>
                <br>
                <input type="submit" value="Add Product" />
            </form>
        </div>

        <%@include file="footer.jsp"%>   

    </body>
</html>