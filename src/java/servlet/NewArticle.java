package servlet;

import enumeration.ArticleCategory;
import enumeration.UserRole;
import factory.ArticleFactory;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Article;
import model.User;

@WebServlet("/NewArticle")
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
            } else {
                response.sendRedirect("404.jsp");
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
        int authorId = 0;
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        try {
            authorId = Integer.parseInt(request.getParameter("authorId"));
        } catch (NumberFormatException se) {
            authorId = user.getUserId();
        }
        String title = request.getParameter("title");
        String imageURL = request.getParameter("imageURL");
        String articleText = request.getParameter("articleText");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date = null;
        try {
            date = sdf.parse(request.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(NewArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	java.sql.Date sqlDate = new Date(date.getTime());
        
        ArticleCategory category = ArticleCategory.valueOf(request.getParameter("category"));
            
        ArticleFactory articleDAO = new ArticleFactory();
        
        try {  
            id = Integer.parseInt(request.getParameter("articleId"));
            
            Article article = new Article(id, authorId, title, imageURL, sqlDate, articleText, category);

            articleDAO.updateArticle(article);
            request.setAttribute("searchedArticle", article);
        } catch (NumberFormatException e) {
            Article article = new Article(authorId, title, imageURL, articleText, category);
            
            articleDAO.insertArticle(article);
            request.setAttribute("searchedArticle", article);
            
            id = articleDAO.getLastArticleId();
            response.sendRedirect("scriviArticolo.html?nid=" + id);
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
