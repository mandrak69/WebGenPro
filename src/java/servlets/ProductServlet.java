/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import static database.DBQueries.getA11;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.A11;
import model.Item;


/**
 *
 
 *
 * Na osnovu parametra 'categoryID' koji mu je prosledjen zahtevom, dohvata
 * ArrayList svih proizvoda date kategorije i stampa je kroz for petlju.
 * Uslucaju da korisnik nije ulogovan, ili je ulogovan administrator, servlet
 * umesto dugmeta 'Buy' prikazuje 'Log in to shop' koje redirectuje ka
 * 'login.jsp' strani.
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            int cat_id = Integer.parseInt(request.getParameter("categoryID"));
            ArrayList<Item> products = DBQueries.getAllProductsFromItemforCat("item",cat_id);
            String kateg = getA11(cat_id);
            if (!products.isEmpty()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
                out.println("<title>Artikli - " + kateg+ "</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
                out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
                out.println("</head>");
                out.println("<body>");
                if (request.getAttribute("purchaseSuccess") == "success") {
                    out.println("<script>purchaseSuccess();</script>");
                }
               // request.getRequestDispatcher("header.jsp").include(request, response);
                out.println("<div class=\"omotac\">");
                for (int i = 0; i < products.size(); i++) {
                    out.println("<div class=\"ram\">");
                    out.println("<div class=slk><img class=\"prod\" img src="+"'images\\" + products.get(i).getWebslika()+ "'\\></div>");
                    out.println("<div class=\"itemce\">");
                    out.println("<p>" + products.get(i).getName() + "</p>");
                    out.println("<p>");
                    out.println("Opis: " + products.get(i).getWebopis());
                    out.println("</p>");
                    out.println("<p>");
                    out.println("Ime: " + products.get(i).getWebime());
                    out.println("</p>");
                     out.println("<p>");
                    out.println("Cena: " + String.format( "%.2f", products.get(i).getWebcena1())+ "E");
                     out.println("</p>");
                    out.println("</div>");
                    int nn = (int)request.getSession().getAttribute("nivo");
                    if (nn>0) {
                        out.println("<div class='kolic'><input type='number' onkeypress='return event.charCode >= 48' min='1' max='5'>");
                        out.println("<a href=\"javascript:confirmBuy('PurchaseServlet?productID=" + products.get(i).getId()+"&price=" + products.get(i).getWebcena1()+ "&categoryID="+cat_id+"&kolicina="+'1'  + "')\" class=\"kupidugme\">Kupi</a></div>");
                    } else {
                        out.println("<div class='cena'><a href=\"login.jsp\" class=\"loginbutton\" style=\"margin-top: 35px; margin-right: 10px;\">LogIn pre kupovine</a></div>");
                   }
               
                    out.println("</div>");
                }
                out.println("</div>");
                request.getRequestDispatcher("footer.jsp").include(request, response);
                out.println("</body>");
                out.println("</html>");
            }else{
                //nema iz  te kategorije nista
                request.getRequestDispatcher("categories.jsp").include(request, response);
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
                out.println("<div>Nema nicega iz te kategorije</div>");
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
             {
       
        try {
            processRequest(request, response);
        } catch (InstantiationException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost11(HttpServletRequest request, HttpServletResponse response)
             {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | ServletException | IOException | InstantiationException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
