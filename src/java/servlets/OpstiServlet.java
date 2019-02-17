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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BazniModel;


/**
 *
 * 
 *
 * Na osnovu parametra 'categoryID' koji mu je prosledjen zahtevom, dohvata
 * ArrayList svih proizvoda date kategorije i stampa je kroz for petlju.
 * Uslucaju da korisnik nije ulogovan, ili je ulogovan administrator, servlet
 * umesto dugmeta 'Buy' prikazuje 'Log in to shop' koje redirectuje ka
 * 'login.jsp' strani.
 */
@WebServlet(name = "OpstiServlet", urlPatterns = {"/OpstiServlet"})
public class OpstiServlet extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tab = request.getParameter("tab");
            int id = Integer.parseInt(request.getParameter("categoryID"));
            System.out.println("tttt"+tab+id);
            ArrayList<BazniModel> products = DBQueries.getAllProductsFromTab(tab,id);
            if (!products.isEmpty()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("<title>izboropstiservlet - " + products.get(0)+ "s</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
                out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
                out.println("</head>");
                out.println("<body>");
                if (request.getAttribute("purchaseSuccess") == "success") {
                    out.println("<script>purchaseSuccess();</script>");
                }
                request.getRequestDispatcher("header.jsp").include(request, response);
                out.println("<div class=\"productlist\">");
                for (int i = 0; i < products.size(); i++) {
                    out.println("<div class=\"product\">");
                    out.println("<img class=\"productimg\" src=\"" +"images/"+ products.get(i).getWebslika()+ "\">");
                    out.println("<div class=\"productinfo\">");
                    out.println("<span>" + products.get(i).getWebime()+ "</span>");
                    out.println("<br>");
                    out.println("Manufacturer: " + products.get(i).getWebopis());
                    out.println("<br>");
                    out.println("Product details: " + products.get(i).getId());
                    out.println("<br>");
                    out.println("Price: " + products.get(i).getName() + "$");
                    out.println("</div>");
                  //  if (request.getSession().getAttribute("logged") != null && !(request.getSession().getAttribute("username").toString()).equals("admin")) {
                //        out.println("<a href=\"javascript:confirmBuy('PurchaseServlet?productID=" + products.get(i).getId()+ "&price=" + products.get(i).getPrice()+ "&categoryID=" + cat_id + "')\" class=\"buybutton\">Buy</a>");
               //     } else {
               //         out.println("<a href=\"login.jsp\" class=\"loginbutton\" style=\"margin-top: 35px; margin-right: 10px;\">Log in to shop</a>");
               //     }
                   out.println("<a href=\"javascript:confirmBuy('PurchaseServlet?productID=" + products.get(i).getId()+ "&categoryID=" + id + "')\" class=\"buybutton\">Pogledaj</a>");
                    
                    out.println("</div>");
                }
                out.println("</div>");
                request.getRequestDispatcher("footer.jsp").include(request, response);
                out.println("</body>");
                out.println("</html>");
            }else{
                //nema iz  te kategorije nista
                request.getRequestDispatcher("opstipregled.jsp").include(request, response);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("<title>Nema nicega iz te kategorije</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
                out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
                out.println("</head>");
                out.println("<body>");
                if (request.getAttribute("purchaseSuccess") == "success") {
                    out.println("<script>purchaseSuccess();</script>");
                }
                request.getRequestDispatcher("header.jsp").include(request, response);
                out.println("<div>Nema nicega iz izabrane kategorije</div>");
                out.println("<div class=\"productlist\">");
                
                out.println("</div>");
                request.getRequestDispatcher("footer.jsp").include(request, response);
                out.println("</body>");
                out.println("</html>");
                
            }
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
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(OpstiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.InstantiationException
     */
    protected void doPost11(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InstantiationException {
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
