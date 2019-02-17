<%-- 
    Document   : index
    Created on : Jun 26, 2018, 5:36:26 PM
    Author     : Jovan

    Pocetna strana webshopa. Na ovu stranu se cesto vrsi redirect u slucaju
    nedozvoljenog pristupa (ako korisnik nije ulogovan, ili nije ulogovan admin).
--%>

<%@page import="database.Constants"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
    <head>
    <frameset cols="97.5%,2.5%"> 

        <frameset  rows="83%,17%">
            <frame name="left" src="index.jsp">
                <frame name="right" src="korpa.jsp">
                    </frameset>
                    <frame name="top" src="register.jsp">
                        </frameset>
                        <style>
                            ul.products li {
                                width: 370px;
                                display: inline-block;
                                text-align: center;
                                background-color:#777 ;
                                font-family: "Trebuchet MS", Helvetica, sans-serif; 
                                border-radius: 10px; 
                                border: 2px solid #585858;

                                background-repeat: no-repeat;
                                background-size: contain;

                            }
                        </style>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <title>Pocetna strana</title>
                        <%
                            if (session.getAttribute("logged") == null) {
                                session.setAttribute("logged", false);
                                User u = new User("Guest", " ", " ", "", "", "", 0, 0);
                                session.setAttribute("user", u);
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





                                <ul class="products">

                                    <li>
                                        <img src="mages/Digitech Delay.jpg" style="  z-index: -5; max-width: 10%;max-height: 10%;transform: scale(10);">
                                        <a href="" style="color:white">    
                                            <h4>North Sky Resort Sogod</h4>
                                            <p>Sogod, Cebu</p>
                                        </a>
                                    </li>
                                    <li>

                                        <img src="images/Boss Overdrive.jpg" style=" z-index: -5; max-width: 10%;max-height: 10%;transform: scale(10);">
                                        <a href="#" style="color:white">   
                                            <h4>Rancho Cancio</h4>
                                            <p>Busay, Cebu City</p>
                                        </a>
                                    </li>
                                    <li>

                                        <img src="images/Gibson Les Paul.jpg" style=" max-width: 10%;max-height: 10%;transform: scale(10);">
                                        <a href="#" style="color:white">    
                                            <h4>Marriot</h4>
                                            <p>Cebu Business Park</p>
                                        </a>
                                    </li>
                                    <li>

                                        <img src="images/Ibanez RG560.jpg" style=" max-width: 10%;max-height: 10%;transform: scale(10);">
                                        <a href="#" style="color:white">    
                                            <h4>Plantation Bay</h4>
                                            <p>Mactan, Cebu</p>
                                        </a>
                                    </li><!-- add more list items -->
                                </ul>
                            </div>

                            <%@include file="footer.jsp"%>

                        </body>

                        <%if (request.getAttribute("registerSuccess") == "success") {%>
                        <script>registerSuccess();</script>
                        <%}%>

                        <%if (request.getAttribute("loginSuccess") == "success") {%>
                        <script>loginSuccess();</script>
                        <%}%>

                        </html>
