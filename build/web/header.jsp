<%-- 
 
    Header webshopa. U zavisnosti od toga da li je ulogovan korisnik, administrator,
    ili nije ulogovan niko, menja se prikazani meni u headeru.
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="database.DBQueries"%>

<%User u = (User) session.getAttribute("user");
String drvo = DBQueries.getMenuTree((u.getNivokorisnika_id()));
String korpa=DBQueries.getKorpuforUser(u.getId());
%>

<header>
    <script src='//ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>
    <script type="text/javascript" src="javascript/design.js"></script>  
    <link rel="stylesheet" type="text/css" href="css/navmeni.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</header>
<div id="prazan"></div>
<div class="header">


    <div class="burger">
        <div id="myNav" class="overlay">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <div class="overlay-content">
                <a href="#">About</a>
                <a href="#">Services</a>
                <a href="#">Clients</a>
                <a href="#">Contact<%=u.getName()%></a>
            </div>
        </div>
        <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>
    </div>  
            
            <div class="navdropdown" style="float:left;">
        <button class="navdropbtn">O</button>
        <div class="navdropdown-content">
            <a href="#"><  ></a>
            <a href="#">  </a>
            <a href="#">  ></a>
        </div>


    </div>
    <%=drvo%>

    </div>    

    <div class="navdropdown" style="float:right;">
        <button class="navdropbtn">Korisnik</button>
        <div class="navdropdown-content">
            <a href="#"><%=u.getNivokorisnika_id()%></a>
            <a href="#"><%=u.getName()%></a>
            <a href="#"><%=DBQueries.getKorpuforUser(u.getId())%></a>
        </div>


    </div>




    <% if (u.getNivokorisnika_id()==0) {%>

    <a href="login.jsp" class="loginbutton" style="color:blue"><span style="color:yellow">Sign/</span>Login</a>
    <a href="register.jsp" class="registerbutton"><img src="images/Guest.png" alt=""/></a>
        <%}%>


    <% if (u.getNivokorisnika_id()!=0)  {%>

    <a href="LogoutServlet" class="logoutbutton">Logout</a>
    <%}%>

    <% if (u.getNivokorisnika_id()>8) {%>

    <a href="administration.jsp" class="administrationbutton">AdminStrana</a>
    <%}%>   
    <% if (u.getNivokorisnika_id() > 0) {%><%=u.getUsername()%>
    <a href="account.jsp" class="accountbutton" >
        <img src="images/MyCartBela.png" alt=""/></a>
        <%}%>  







</div> </div> 
<script>
    function openNav() {
        document.getElementById("myNav").style.width = "100%";
    }

    function closeNav() {
        document.getElementById("myNav").style.width = "0%";
    }

    $(document).ready(function () {
        $('.dropdown-submenu a.test').on("click", function (e) {
            $(this).next('ul').toggle();
            e.stopPropagation();
            e.preventDefault();
        });
    });
</script>

