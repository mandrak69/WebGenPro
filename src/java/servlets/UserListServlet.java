/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Ovaj servlet stampa listu svih korisnika koji su trenutno registrovani. Ovoj
 * stranici moze da pristupi samo administrator.
 */
@WebServlet(name = "UserListServlet", urlPatterns = {"/UserListServlet"})
public class UserListServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            User us = (User)request.getSession().getAttribute("user");
            if (us.getNivokorisnika_id()<7) {
                System.out.println("Nemate privilegiju9 za ovo");
                response.sendRedirect("index.jsp");
            }
            ArrayList<User> korisnici = DBQueries.getUsersForUserList();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>User List - " + us.getName() + "</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            request.getRequestDispatcher("header.jsp").include(request, response);
            out.println("<div class=\"userlist\">");
            out.println("<br>");
             out.println("<br>");
              out.println("<br>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>User ID</th>");
            out.println("<th>Username</th>");
            out.println("<th>First Name</th>");
            out.println("<th>Last Name</th>");
            out.println("<th>Phone</th>");
            out.println("<th>E-mail</th>");
            out.println("<th>Address</th>");
            out.println("<th>Nivo</th>");
            out.println("</tr>");
            for (int i = 0; i < korisnici.size(); i++) {
                out.println("<tr>");
                out.println("<td>" + korisnici.get(i).getId()+ "</td>");
                out.println("<td>" + korisnici.get(i).getUsername() + "</td>");
                out.println("<td>" + korisnici.get(i).getName()+ "</td>");
                out.println("<td>" + korisnici.get(i).getLast_name()+ "</td>");
                out.println("<td>" + korisnici.get(i).getPhone() + "</td>");
                out.println("<td>" + korisnici.get(i).getEmail() + "</td>");
                out.println("<td>" + korisnici.get(i).getAddress() + "</td>");
                out.println("<td>" + korisnici.get(i).getNivokorisnika_id()+ "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("footer.jsp").include(request, response);
            out.println("</body>");
            out.println("</html>");
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
