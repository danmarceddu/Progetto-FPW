package servlet;

import enumeration.ArticleCategory;
import enumeration.UserRole;
import factory.ArticleFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Article;
import model.User;

public class NewArticle extends HttpServlet {

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
            ArticleFactory articleDAO = new ArticleFactory();
        
            Article searchedArticle = articleDAO.getArticleById(id);

            if (searchedArticle != null) {
                request.setAttribute("article", searchedArticle);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/modificaArticolo.jsp");
                rd.forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {}
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/scriviArticolo.jsp");
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
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null || UserRole.author != user.getRole()){
            response.sendRedirect("accessoNegato.jsp");
            return;
        }
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
        
        int authorId = Integer.parseInt(request.getParameter("user.userId"));
        String title = request.getParameter("title");
        String imageURL = request.getParameter("imageURL");
        String articleText = request.getParameter("articleText");
        ArticleCategory category = ArticleCategory.valueOf(request.getParameter("category"));
            
        ArticleFactory articleDAO = new ArticleFactory();
        
        try {  
            id = Integer.parseInt(request.getParameter("nid"));
            
            Article article = new Article(id, authorId, title, imageURL, articleText, category);

            articleDAO.updateArticle(article);
            request.setAttribute("searchedArticle", article);
        } catch (NumberFormatException e) {
            Article article = new Article(authorId, title, imageURL, articleText, category);
            
            articleDAO.insertArticle(article);
            request.setAttribute("searchedArticle", article);
        }
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
