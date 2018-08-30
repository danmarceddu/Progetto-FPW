package servlet;

import factory.ArticleFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancellaArticolo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        try {  
            id = Integer.parseInt(request.getParameter("nid"));
            ArticleFactory articleDAO = new ArticleFactory();
            articleDAO.deleteArticle(id);
        } catch (NumberFormatException e) {
            response.sendRedirect("404.jsp");
        }
        response.sendRedirect("articoli.html");
    }
}
