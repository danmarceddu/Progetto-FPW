/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import factory.ArticleFactory;
import factory.CommentFactory;
import factory.UserFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Article;
import model.Comment;
import model.User;

/**
 *
 * @author Alessandro Pilosu
 */
public class NewsDetail extends HttpServlet {

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
        int id = 0;
        try {  
            id = Integer.parseInt(request.getParameter("nid"));
        } catch (NumberFormatException e) {  
            response.sendRedirect("404.html");
            return;
        }
         
        ArticleFactory articleDAO = new ArticleFactory();
        UserFactory userDAO = new UserFactory();
        CommentFactory commentDAO = new CommentFactory();

        Article searchedArticle = articleDAO.getArticleById(id);

        if (searchedArticle != null) {
            User searchedUser = userDAO.getUserById(searchedArticle.getAuthorId());
            List<Comment> searchedComments = commentDAO.getCommentsByArticleId(id);
            List<User> usersComments = userDAO.getUsersFromComments(searchedComments);
            request.setAttribute("article", searchedArticle);
            request.setAttribute("author", searchedUser);
            request.setAttribute("comments", searchedComments);
            request.setAttribute("usersComments", usersComments);
        } else {
            response.sendRedirect("404.html");
            return;
        }
        request.getRequestDispatcher("/notiziaSingola.html").include(request, response);
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