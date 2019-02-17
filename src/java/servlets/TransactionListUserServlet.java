/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Ovaj servlet prikazuje stranicu sa detaljima korisnika kada se na njegovo
 * korisnicko ime klikne u Transaction Listi. Ovoj stranici moze da pristupi
 * samo administrator.
 */
@WebServlet(name = "TransactionListUserServlet", urlPatterns = {"/TransactionListUserServlet"})
public class TransactionListUserServlet extends HttpServlet {

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
            if (!(request.getSession().getAttribute("username").toString()).equals("admin")) {
                response.sendRedirect("index.jsp");
            }
            String username = request.getParameter("username");
            User u = DBQueries.getUserForTransactionList(username);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>User - " + u.getUsername() + "</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            request.getRequestDispatcher("header.jsp").include(request, response);
            out.println("<div id=\"account\">");
            out.println("<div id=\"userinfo\">");
            out.println("<h2>" + u.getUsername() + "</h2>");
            out.println("First name: " + u.getName()+ "<br>");
            out.println("Last name: " + u.getLast_name()+ "<br>");
            out.println("Phone number: " + u.getPhone() + "<br>");
            out.println("E-mail: " + u.getEmail() + "<br>");
            out.println("Address: " + u.getAddress() + "<br>");
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
