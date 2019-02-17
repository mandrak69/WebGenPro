<%-- 
    Document   : index
    
    Pocetna strana webshopa. Na ovu stranu se cesto vrsi redirect u slucaju
    nedozvoljenog pristupa (ako korisnik nije ulogovan, ili nije ulogovan admin).
--%>

<%@page import="java.util.Vector"%>
<%@page import="model.A11"%>

<%@page import="database.Constants"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
    <head>
 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pocetna strana</title>
        <% 
            if (session.getAttribute("logged") == null) {
                session.setAttribute("logged", false);
                User u = new User("Guest", " ", " ", "", "", "", 0, 0);
                u.setId(0);
                u.setNivokorisnika_id(0);
                session.setAttribute("user", u);
               
                request.getSession(true).setAttribute("user", u);
                request.getSession(true).setAttribute("nivo", u.getNivokorisnika_id());
               request.getSession(true).setAttribute("userID", u.getId());
            }
        %>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/slideshow.css">
        <script type="text/javascript" src="javascript/design.js"></script>
        <script type="text/javascript" src="javascript/index.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">  

    </head>
    <body onload="currentSlide(1)">

        <%@include file="header.jsp"%>
 
        <div class="main">


            <!-- Slideshow container -->
            <div class="slideshow-container">

                <!-- Full-width images with number and caption text -->
                <div class="mySlides fade">
                    <div class="numbertext">1 / 3</div>
                    <img src="images/img1.png" style="width:100%">
                    <div class="text">Ovde se vrti vasa reklama</div>
                </div>

                <div class="mySlides fade">

                    <div class="numbertext">2 / 3</div>
                    <img src="images/img1.png" style="width:100%">
                    <div class="text">Ovde se vrti vasa reklama</div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">3 / 3</div>
                    <img src="images/img3.png" style="width:100%">
                    <div class="text">Caption ovde svasta ima</div>
                </div>

                <!-- Next and previous buttons -->
                <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                <a class="next" onclick="plusSlides(1)">&#10095;</a>
            </div>



 
    <% Vector<A11> kategorije
                    = DBQueries.getA1(); %>
    <div class="divvrsta" >
        <% for (int i = 0; i < kategorije.size(); i++) {%>
        <input class="dugmevrsta" type="submit" 
               id ="<%=kategorije.get(i).getId()%>"
               onclick="vratiItems()"
               value="<%=kategorije.get(i).getName()%>"> 

        <br>
        <% }%>
    </div>

    <div id="items">

        <p>Izaberite neku kategoriju za pregled</p>
    </div>
    <!-- <img src="slika.jpg">  -->



        </div>

      

    </body>

    <%if (request.getAttribute("registerSuccess") == "success") {%>
    <script>registerSuccess();</script>
    <%}%>

    <%if (request.getAttribute("loginSuccess") == "success") {%>
    <script>loginSuccess();</script>
    <%}%>

</html>
