/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBQueries;
import static database.DBQueries.getItemNameForTransactionList;
import static database.DBQueries.getUsernameForTransactionList;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Ovaj servlet se pokrece klikom na dugme Transaction List bilo na stranici
 * Account (kada je ulogovan korisnik), bilo na stranici Administration (kada je
 * ulogovan administrator). Na imena korisnika i kupljenih proizvoda moze se
 * kliknuti, na sta se dobija nov ekran sa detaljima o korisniku/proizvodu.
 *
 * U slucaju da je trenutno ulogovan administrator, TransactionList stampa listu
 * svih transakcija koje administrator moze da potvrdi klikom na 'Approve'
 * dugme.
 *
 * U slucaju da je trenutno ulogovan korisnik, TransactionList stampa iskljucivo
 * listu transakcija koje je ulogovani korisnik obavio. Napomena: kod
 * korisnikove liste transakcija prikazuju se iskljucivo transakcije koje je
 * administrator potvrdio klikom na 'Approve', jer se tek tada racunaju kao
 * izvrsene.
 */
@WebServlet(name = "TransactionListServlet", urlPatterns = {"/TransactionListServlet"})
public class TransactionListServlet extends HttpServlet {

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
        User us=(User) request.getSession().getAttribute("user");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>Transaction List - " + us.getName()+us.getLast_name()+ "</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            out.println("<script type=\"text/javascript\" src=\"javascript/design.js\"></script>");
            
            out.println("<style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}");

 out.println("td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;}");

out.println("tr:nth-child(even) {background-color: #dddddd;}</style>");
            
            out.println("</head>");
            out.println("<body>");
            request.getRequestDispatcher("header.jsp").include(request, response);
            int nv = (int)us.getNivokorisnika_id();
            if (nv>7)    {
                ArrayList<Transaction> transakcije = DBQueries.getTransactionList(us.getId(), nv);
                out.println("<div>");
                out.println("<table>");
                out.println("<tr>");
               out.println("<th style=\"width:40px\">IdTr. ID</th>");
                out.println("<th style=\"width:130px\">Datum</th>");
                out.println("<th style=\"width:50px\">Kupac</th>");
                out.println("<th style=\"width:50px\">Kol</th>");
                out.println("<th style=\"width:330px\">Proizvod</th>");
                out.println("<th style=\"width:130px\">Cena</th>");
                out.println("<th style=\"width:230px\">Stanje</th>");
                out.println("</tr>");
                for (int i = 0; i < transakcije.size(); i++) {
                    out.println("<tr>");
                    out.println("<td style=\"width:40px\">" + transakcije.get(i).getId()+ "</td>");
                    out.println("<td  style=\"width:130px\">" + transakcije.get(i).getDate() + "</td>");
                    out.println("<td  style=\"width:50px\"><a href=\"TransactionListUserServlet?username=" + transakcije.get(i).getUser() + "\">" + getUsernameForTransactionList(transakcije.get(i).getUser()) + "</a></td>");
                    out.println("<td  style=\"width:330px\"><a href=\"TransactionListItemServlet?productName=" + transakcije.get(i).getItem() + "\">" + getItemNameForTransactionList(transakcije.get(i).getItem()) + "</a></td>");
                    out.println("<td style=\"width:130px\"><a href=\"TransactionListItemServlet?transactionId=" + transakcije.get(i).getId()+ "\">" + (transakcije.get(i).getPrice()) + "&euro;</a></td>");
                    if (transakcije.get(i).getApproved().equals("yes")) {
                        out.println("<td style=\"width:130px\">Potvrdjen</td>");
                    } else {
                        out.println("<td style=\"width:130px\"><a href=\"ValidateServlet?transactionID=" + transakcije.get(i).getId() + "\" class=\"validatebutton\">Potvrdi</a></td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</div>");
            } else {
                int user_id = us.getId();
                ArrayList<Transaction> transakcije = DBQueries.getTransactionList(user_id,6);
                out.println("<div>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th style=\"width:40px\">IdTr. ID</th>");
                out.println("<th style=\"width:130px\">Datum</th>");
                out.println("<th style=\"width:50px\">Kupac</th>");
                out.println("<th style=\"width:50px\">Kol</th>");
                out.println("<th style=\"width:330px\">Proizvod</th>");
                out.println("<th style=\"width:130px\">Cena</th>");
                out.println("<th style=\"width:230px\">Stanje</th>");
                out.println("</tr>");
                for (int i = 0; i < transakcije.size(); i++) {
                    if (transakcije.get(i).getApproved().equals("yes")) {
                        out.println("<tr>");
                        out.println("<td style=\"width:40px\">" + transakcije.get(i).getId() + "</td>");
                        out.println("<td style=\"width:130px\">" + transakcije.get(i).getDate() + "</td>");
                        out.println("<td style=\"width:130px\"><a href=\"TransactionListItemServlet?transactionId=" + transakcije.get(i).getId() + "\">" + getItemNameForTransactionList(transakcije.get(i).getItem()) + "</a></td>");
                        out.println("<td style=\"width:230px\"><a href=\"TransactionListItemServlet?transactionId=" + transakcije.get(i).getId() + "\">" + (transakcije.get(i).getPrice())+"&euro;   Potv." + "</a></td>");

                        out.println("</tr>");
                    }else{
                      
                        
                        
                        
                        
                        
                     out.println("<tr>");
                        out.println("<td style=\"width:40px\">" + transakcije.get(i).getId() + "</td>");
                        out.println("<td style=\"width:130px\">" + transakcije.get(i).getDate() + "</td>");
                        out.println("<td style=\"width:130px\"><a href=\"TransactionListItemServlet?transactionId=" + transakcije.get(i).getId() + "\">" + getItemNameForTransactionList(transakcije.get(i).getItem()) + "</a></td>");
                        out.println("<td style=\"width:230px\"><a href=\"TransactionListItemServlet?transactionId=" + transakcije.get(i).getId() + "\">" + (transakcije.get(i).getPrice())+"Na cek." + "</a></td>");

                        out.println("</tr>");
                    }
                }
                out.println("</table>");
                out.println("</div>");
            }
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
