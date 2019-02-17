/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import static database.DBQueries.getKorpuforUser;
import static database.DBQueries.getUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Jovan
 *
 * Servlet preuzima podatke iz forme sa 'register.jsp' strane i salje ih u bazu
 * putem upita RegisterUser. U slucaju da je neko od polja prazno ili da
 * username vec postoji u bazi, stranica se ponovo ucitava i izbacuje se
 * odgovarajuce resenje.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean userExists;
        try {
            User u = new User(request.getParameter("tb_firstName"),
                    request.getParameter("tb_lastName"), request.getParameter("tb_phone"),
                    request.getParameter("tb_email"), request.getParameter("tb_adress"),
                    request.getParameter("tb_username"), request.getParameter("tb_password"),0,1);
            userExists = DBQueries.doesUserExist(u.getUsername());
            if (!u.getName().equals("") 
                    && !u.getEmail().equals("")
                     && !u.getUsername().equals("")
                    && !u.getPassword().equals("")) {
                if (userExists == true) {
                    request.setAttribute("errorMessage", "exists");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    int kor = DBQueries.RegisterUser(u);
                    u.setId(kor);
                   
                request.getSession(true).setAttribute("logged", "true");
                request.getSession(true).setAttribute("user", u);
                request.getSession(true).setAttribute("nivo", u.getNivokorisnika_id());
               request.getSession(true).setAttribute("userID", u.getId());
                  request.getSession(true).setAttribute("ukorpi", "0/0"); 
                  
                    request.getSession(true).setAttribute("registerSuccess", "success");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "empty");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
