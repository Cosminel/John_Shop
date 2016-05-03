package eu.ubis.eshop.emailUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.UserDTO;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
//        String recipient = request.getParameter("recipient");
//        String subject = request.getParameter("subject");
//        String content = request.getParameter("content");
    	HttpSession session = request.getSession();
    	UserDTO currentUser = (UserDTO)session.getAttribute("user");
    	String recipient = currentUser.getEmail();
        String subject = "Comanda"; 
        String content ="";
        
        StringBuilder text= new StringBuilder();
        text.append("Ati cumparat urmatoarele produse: ");
        Hashtable<ProductDTO,Integer> products = (Hashtable<ProductDTO,Integer>)session.getAttribute("shoppingcart");
        Enumeration<ProductDTO> enumeration = products.keys();
		while(enumeration.hasMoreElements())
		{
			ProductDTO aux = enumeration.nextElement();
			text.append(aux.getName());
			text.append(", ");
		}
        content=text.toString();
        session.removeAttribute("shoppingcart");
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
           System.out.println("The e-mail was sent successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
           System.out.println( "There were an error: " + ex.getMessage());
        } finally {
        	
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
