package servlet;

import enumeration.ArticleCategory;
import factory.ArticleFactory;
import factory.UserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.User;

@WebServlet("/Notizie")
public class Notizie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param category
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, ArticleCategory category)
            throws ServletException, IOException {
        ArticleFactory articleDAO = new ArticleFactory();
        UserFactory userDAO = new UserFactory();

        List <Article> searchedArticles = articleDAO.getArticlesInChronogicalOrder(category);
        
        List<User> searchedAuthors = new ArrayList();
        List <Article> searchedCorrectedArticles = new ArrayList();

        if (searchedArticles != null) {
            
            searchedArticles.stream().map((art) -> {
                searchedAuthors.add(userDAO.getUserById(art.getAuthorId()));
                return art;
            }).forEachOrdered((art) -> {
                final String articleContent = getArticleContent(art);
                
                searchedCorrectedArticles.add(new Article(art.getArticleId(), art.getAuthorId(), art.getTitle(), art.getImageURL()
                        , art.getDate(), articleContent, art.getCategory()));
            });
            request.setAttribute("searchedCorrectedArticles", searchedCorrectedArticles);
            request.setAttribute("searchedAuthors", searchedAuthors);
        } else {
            response.sendRedirect("404.jsp");
            return;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/notizie.jsp");
	rd.forward(request, response);
    }
    
    private String getArticleContent(Article article){
        if (article.getArticleText().length()>100)
            return article.getArticleText().substring(0, 99) + "...";
        return article.getArticleText();
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
        ArticleCategory category;
        try {
            category = ArticleCategory.valueOf(request.getParameter("category"));
        }
        catch(NullPointerException e) {
            category = null;
        }
        processRequest(request, response, category);
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
