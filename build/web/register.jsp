<%-- 
   
    Stranica sa formom za registrovanje korisnika. Logiku registracije obavlja 
    RegisterServlet pri kliku na dugme 'Register'.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registracija</title>
        <link rel="stylesheet" type="text/css" href="css/registerform.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/design.js"></script>
       
    </head>

    <%if (request.getAttribute("errorMessage") == "exists") {%>
    <script>usernameExists();</script>
    <%}%>

    <%if (request.getAttribute("errorMessage") == "empty") {%>
    <script>fieldsEmpty();</script>
    <%}%>

    <body>

<form  action="RegisterServlet" method="post">
        <h1>Registracija </h1>
        <p>Required fields are followed by <strong><abbr title="required">*</abbr></strong>.</p>
        <section>
            <h2>Contact information</h2>
            
            <p>
              <label for="name">
                <span>Ime </span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
              <input type="text" id="name" name="tb_firstName" required>
            </p>
            <p>
              <label for="name">
                <span>Prezime </span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
              <input type="text" id="name" name="tb_lastName">
            </p>
            <p>
              <label for="adresa">
                <span>Adresa </span>
                <strong></strong>
              </label>
              <input type="text" id="name" name="tb_adress">
            </p>
            <p>
              <label for="mail">
                <span>E-mail: </span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
              <input type="email" id="mail" name="tb_email" required>
            </p>
            <p>
              <label for="username">
                <span>Username: </span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
              <input type="username" id="pwd" name="tb_username" required>
            </p>
            <p>
              <label for="password">
                <span>Password: </span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
              <input type="password" id="pwd" name="tb_password" required>
            </p>
            <p>
                <label for="password"><span>RE Password: </span> </label><input type="password" class="myDugme" id="pwd" name="tb_repassword" pattern=".{6,}" title="Six or more characters" required/><br>
                
             
            </p>
            <p>
              <label for="number">
                <span>Broj telefona:</span>
                <strong><abbr title="required">*</abbr></strong>
              </label>
                <input type="number" id="number" name="tb_phone">
            </p>
        </section>
        <section>
            <h2> I na kraju..</h2>
            <p>
              <label for="card">
                <span>Zelite obavestenja?</span>
              </label>
              <select id="card" name="usercard">
                <option value="visa">Zelim postu</option>
                <option value="mc">Ne zelim postu</option>
                <option value="amex">Zelim samo kataloge</option>
              </select>
            </p>
            
            
        </section>
        <section>
            <p> <button type="submit" class="myDugme" value="Register">Register</button>  </p>
        </section>
    </form>
       
        
        
        
        
        

        <div id="registerscreen">
            <form  >
               <%@include file="footer.jsp"%>
            </form>
        </div>

         

    </body>
</html>
