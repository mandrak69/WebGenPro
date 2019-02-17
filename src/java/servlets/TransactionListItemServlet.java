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
import model.Item;

/**
 *
 * @author Jovan
 *
 * Ovom servletu se pristupa iz TransactionList servleta klikom na naziv bilo
 * kojeg proizvoda. Ovaj servlet potom prikazuje detalje o zahtevanom proizvodu.
 */
@WebServlet(name = "TransactionListItemServlet", urlPatterns = {"/TransactionListItemServlet"})
public class TransactionListItemServlet extends HttpServlet {

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
            if (request.getSession().getAttribute("nivo") == null) {
                response.sendRedirect("index.jsp");
            }
            /*             pobroju transakcije iz tabele vadi podatke             */
            
            String productName = request.getParameter("transactionId");
            Item p = DBQueries.getProductForTransactionList(productName);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>Product - " + p.getName() + "s</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            request.getRequestDispatcher("header.jsp").include(request, response);
            out.println("<div class=\"productfromlist\">");
            out.println("<img class=\"productimgfromlist\" src=\"" + p.getWebslika()+ "\">");
            out.println("<div class=\"productinfofromlist\">");
            out.println("<span>" + p.getName() + "</span>");
            out.println("<br><br>");
            out.println("Manufacturer: " + p.getB11_id()+ "");
            out.println("<br><br>");
            out.println("Product details:<br>" + p.getWebopis()+ "");
            out.println("<br><br>");
            out.println("Price: " + p.getCena()+ "$");
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
