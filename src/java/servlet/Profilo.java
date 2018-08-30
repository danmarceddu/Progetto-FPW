package servlet;

import factory.UserFactory;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "Profilo", urlPatterns = {"/profilo.html"})
public class Profilo extends HttpServlet {

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
        int id;
        HttpSession session = request.getSession(false);
        UserFactory userDAO = new UserFactory();
        RequestDispatcher rd = null;
        User user = (User) session.getAttribute("user");
        
        try {  
            id = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        
        if(id != 0){
            user = userDAO.getUserById(id);
            session.setAttribute("profileUser", user);
            rd = getServletContext().getRequestDispatcher("/profilo.jsp");
        }
        else if (user != null && id == 0){
            user = userDAO.getUserToModifyById(user.getUserId());
            session.setAttribute("profileUser", user);
            rd = getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
        } else {
            response.sendRedirect("404.jsp");
            return;
        }
        rd.forward(request, response);
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
        
        int id = 0;
        try {  
            id = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {}
        
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        Date date = Date.valueOf(request.getParameter("date"));
        String imageURL = request.getParameter("imageURL");
        String biography = request.getParameter("biography");
        
        User userToBeUpdated = new User(id, name, surname, password, username, date, imageURL, biography);
        UserFactory userDAO = new UserFactory();
        userDAO.updateUser(userToBeUpdated);
        
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
