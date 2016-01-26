/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HelloService;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@WebServlet(name = "HelloController", urlPatterns = {"/HelloController"})
public class HelloController extends HttpServlet {

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
            //this is a convenience method provided by netbeans, you would use this unless 
            //the data from doGet and doPost need to be handled differently
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // M.I.M.E type 
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("username");
            
            HelloService helloSrv = new HelloService();
            String responseMsg = helloSrv.sayHello(name);
            
           
            request.setAttribute("myMsg", responseMsg);
            // setAttribute(Key, Value)
            // Attribute is datatype Object
            // stored as an attribute of the request object ("key", value)
            
            RequestDispatcher view = request.getRequestDispatcher("/helloResponse.jsp");
            view.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

     //   RequestDispatcher view = request.getRequestDispatcher("/helloResponse.jsp");
        // hello response is the view in the MVC
        // use request dispatcher to send information to another page 
        // request dispatcher is a special object
        // this code can be used for ANY instance of needing to send a message
        // have to forward to jsp page, because html can't run java
        // use html when there's no java, jsp when there is java
      //  view.forward(request, response);
        // send both request and response to the page
        // you forward the request as well because you are using it to hold data (response)
        // request is made to server, server creates an object that data is put into as attributes
        // page is then forwarded while it is STILL ON THE SERVER (the jsp page) where the data
        // processing will then take place. Only after this does the page finally get sent to the client
        // There is never any java coming back to the client.
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
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String age = request.getParameter("age");
            String responseMsg = "You are " + age + " years old!";
            request.setAttribute("myMsg", responseMsg);

            RequestDispatcher view = request.getRequestDispatcher("/helloResponse.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
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
