/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import static database.DBQueries.getKorpuforUser;
import static database.DBQueries.getTransactionList;

import static database.DBQueries.getUser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Transaction;
import model.User;

/**
 *
 * @author Jovan
 *
 * Preuzima podatke iz tekstualnih polja, iz login forme na login.jsp strani i proverava da li kombinacija usernamea i passworda postoji u bazi. U slucaju da postoji, setuje atribute za sesiju i redirektuje na index.jsp. Ako ne postoji, ponovo ucitava stranu sa porukom 'Invalid User'.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("tb_username");
        String password = request.getParameter("tb_password");
        if ((username != null && password != null) && (!username.equals("") && !password.equals(""))) {
            int id = DBQueries.isUserValidReturnId(username, password);
           
            if (id !=-1) {
                User ulogovani=getUser(id);
                request.getSession(true).setAttribute("logged", "true");
                request.getSession(true).setAttribute("user", ulogovani);
                request.getSession(true).setAttribute("nivo", ulogovani.getNivokorisnika_id());
               request.getSession(true).setAttribute("userID", ulogovani.getId());
                ArrayList<Transaction> kup = getTransactionList(ulogovani.getId(),ulogovani.getNivokorisnika_id());
                
                 request.getSession(true).setAttribute("ukorpi", getKorpuforUser(ulogovani.getId()));
                 
                 
                request.getRequestDispatcher("categories.jsp").forward(request, response);
             //   request.getRequestDispatcher("HTM3in1.html").forward(request, response);
                
            } else {
                
                request.setAttribute("errorMessage", "invalid");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "invalid");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
