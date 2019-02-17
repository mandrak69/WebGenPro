/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;

/**
 *
 * @author Jovan
 */
@WebServlet(name = "NewProductServlet", urlPatterns = {"/NewProductServlet"})
public class NewProductServlet extends HttpServlet {

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
        boolean productExists;
        try {
            String imagepath = "images/" + request.getParameter("image");
            if (!imagepath.endsWith("jpg")) {
                request.setAttribute("errorMessage", "notjpg");
                request.getRequestDispatcher("newproduct.jsp").forward(request, response);
            }
            if (!request.getParameter("tb_price").equals("") && !request.getParameter("tb_productName").equals("")
                    && !request.getParameter("tb_description").equals("") && !request.getParameter("dm_category").equals("")
                    && !request.getParameter("dm_manufacturer").equals("") && !imagepath.equals("")) {
                productExists = DBQueries.doesProductExist(request.getParameter("tb_productName"));
                Item p = new Item(Double.parseDouble(request.getParameter("tb_price")), request.getParameter("tb_productName"),
                        request.getParameter("tb_description"), Integer.parseInt(request.getParameter("dm_category")),
                        Integer.parseInt(request.getParameter("dm_manufacturer")), imagepath);
                
                
                if (productExists == true) {
                    request.setAttribute("errorMessage", "exists");
                    request.getRequestDispatcher("newproduct.jsp").forward(request, response);
                } else {
                    System.out.println("produkat"+p.toString());
                    DBQueries.CreateNewProduct(p);
                    request.setAttribute("newproductSuccess", "success");
                    request.getRequestDispatcher("newproduct.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "empty");
                request.getRequestDispatcher("newproduct.jsp").forward(request, response);
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
