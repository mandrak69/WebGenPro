package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import model.A11;
import model.A1;
import java.util.ArrayList;
import database.DBQueries;
import model.User;
import database.DBQueries;

public final class tabelaGlavnimeni_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/header.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write(" ");
 Vector<A11> lista
                    = DBQueries.getA1(); 
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body >\n");
      out.write("\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
User u = (User) session.getAttribute("user");
String drvo = DBQueries.getMenuTree((u.getNivokorisnika_id()));
String korpa=DBQueries.getKorpuforUser(u.getId());

      out.write("\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    <script src='//ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"javascript/design.js\"></script>  \n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/navmeni.css\">\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("</header>\n");
      out.write("<div id=\"prazan\"></div>\n");
      out.write("<div class=\"header\">\n");
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"burger\">\n");
      out.write("        <div id=\"myNav\" class=\"overlay\">\n");
      out.write("            <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>\n");
      out.write("            <div class=\"overlay-content\">\n");
      out.write("                <a href=\"#\">About</a>\n");
      out.write("                <a href=\"#\">Services</a>\n");
      out.write("                <a href=\"#\">Clients</a>\n");
      out.write("                <a href=\"#\">Contact");
      out.print(u.getName());
      out.write("</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <span style=\"font-size:30px;cursor:pointer\" onclick=\"openNav()\">&#9776; open</span>\n");
      out.write("    </div>  \n");
      out.write("            \n");
      out.write("            <div class=\"navdropdown\" style=\"float:left;\">\n");
      out.write("        <button class=\"navdropbtn\">O</button>\n");
      out.write("        <div class=\"navdropdown-content\">\n");
      out.write("            <a href=\"#\"><  ></a>\n");
      out.write("            <a href=\"#\">  </a>\n");
      out.write("            <a href=\"#\">  ></a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    ");
      out.print(drvo);
      out.write("\n");
      out.write("\n");
      out.write("    </div>    \n");
      out.write("\n");
      out.write("    <div class=\"navdropdown\" style=\"float:right;\">\n");
      out.write("        <button class=\"navdropbtn\">Korisnik</button>\n");
      out.write("        <div class=\"navdropdown-content\">\n");
      out.write("            <a href=\"#\">");
      out.print(u.getNivokorisnika_id());
      out.write("</a>\n");
      out.write("            <a href=\"#\">");
      out.print(u.getName());
      out.write("</a>\n");
      out.write("            <a href=\"#\">");
      out.print(DBQueries.getKorpuforUser(u.getId()));
      out.write("</a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if (u.getNivokorisnika_id()==0) {
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"login.jsp\" class=\"loginbutton\" style=\"color:blue\"><span style=\"color:yellow\">Sign/</span>Login</a>\n");
      out.write("    <a href=\"register.jsp\" class=\"registerbutton\"><img src=\"images/Guest.png\" alt=\"\"/></a>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if (u.getNivokorisnika_id()!=0)  {
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"LogoutServlet\" class=\"logoutbutton\">Logout</a>\n");
      out.write("    ");
}
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if (u.getNivokorisnika_id()>8) {
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"administration.jsp\" class=\"administrationbutton\">AdminStrana</a>\n");
      out.write("    ");
}
      out.write("   \n");
      out.write("    ");
 if (u.getNivokorisnika_id() > 0) {
      out.print(u.getUsername());
      out.write("\n");
      out.write("    <a href=\"account.jsp\" class=\"accountbutton\" >\n");
      out.write("        <img src=\"images/MyCartBela.png\" alt=\"\"/></a>\n");
      out.write("        ");
}
      out.write("  \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</div> </div> \n");
      out.write("<script>\n");
      out.write("    function openNav() {\n");
      out.write("        document.getElementById(\"myNav\").style.width = \"100%\";\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function closeNav() {\n");
      out.write("        document.getElementById(\"myNav\").style.width = \"0%\";\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    $(document).ready(function () {\n");
      out.write("        $('.dropdown-submenu a.test').on(\"click\", function (e) {\n");
      out.write("            $(this).next('ul').toggle();\n");
      out.write("            e.stopPropagation();\n");
      out.write("            e.preventDefault();\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <input type=\"submit\" onclick=\"XMLZahtev()\" value=\"GET TIME\"> <br>\n");
      out.write("        <div id=\"time\"> \n");
      out.write("            Tacno vreme\n");
      out.write("        </div>\n");
      out.write("       \n");
      out.write("        <table>\n");
      out.write("            ");
 for (A11 vr : lista) {
      out.write("\n");
      out.write("         <tr> <td>  <input class=\"navbutton\" type=\"submit\" \n");
      out.write("                   id =\"");
      out.print(vr.getId());
      out.write("\"\n");
      out.write("                   onclick=\"vratiItems()\" value=\"");
      out.print(vr.getWebime());
      out.write("\"> \n");
      out.write("          </td></tr>\n");
      out.write("            \n");
      out.write("          \n");
      out.write("            ");
 }
      out.write("\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        <div id =\"items\">\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div class=\"categories\">\n");
      out.write("            <div class=\"catlink\" >\n");
      out.write("                <a href=\"ProductServlet?categoryID=1\">\n");
      out.write("                    <img class=\"catimage\" src=\"images/Gibson Les Paul.jpg\"/>\n");
      out.write("                </a>\n");
      out.write("                <a href=\"ProductServlet?categoryID=1\">\n");
      out.write("                    <span>Guitars</span>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"catlink\">\n");
      out.write("                <a href=\"ProductServlet?categoryID=2\">\n");
      out.write("                    <img class=\"catimage\" src=\"images/Cort C4H.jpg\" />\n");
      out.write("                </a>\n");
      out.write("                <a href=\"ProductServlet?categoryID=2\">\n");
      out.write("                    <span>Bass Guitars</span>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"catlink\">\n");
      out.write("                <a href=\"ProductServlet?categoryID=3\">\n");
      out.write("                    <img class=\"catimage\" src=\"images/Marshall AC30.jpg\"/>\n");
      out.write("                </a>\n");
      out.write("                <a href=\"ProductServlet?categoryID=3\">\n");
      out.write("                    <span>Amps</span>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"catlink\">\n");
      out.write("                <a href=\"ProductServlet?categoryID=4\">\n");
      out.write("                    <img class=\"catimage\" src=\"images/Boss Overdrive.jpg\"/>\n");
      out.write("                </a>\n");
      out.write("                <a href=\"ProductServlet?categoryID=4\">\n");
      out.write("                    <span>Pedals</span>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- <img src=\"slika.jpg\">  -->\n");
      out.write("        <script type = \"text/javascript\" src=\"javascript/tabelaGson.js\">\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
