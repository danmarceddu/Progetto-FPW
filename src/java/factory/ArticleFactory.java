package factory;

import enumeration.ArticleCategory;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import model.User;

public class ArticleFactory {
    
    static Connection currentCon = null;
    
    public int getLastArticleId(){
        int id = 0;
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT articleId FROM articles ORDER BY articleId DESC LIMIT 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            while (rs.next()) {
                id = rs.getInt("articleId");
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return id;
    }
    
    public Article getArticleById(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM articles WHERE articleId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            Article searchedArticle = null;
            
            while (rs.next()) {
                searchedArticle = new Article(id, 
                                              rs.getInt("authorId"), 
                                              rs.getString("title"), 
                                              rs.getString("imageURL"), 
                                              rs.getDate("date"), 
                                              rs.getString("articleText"), 
                                              ArticleCategory.valueOf(rs.getString("articleCategory")));
            }
            return searchedArticle;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public List<Article> getArticlesByAuthor(User user){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM articles WHERE authorId = ?");
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            List<Article> searchedArticles = new ArrayList<>();
            
            while (rs.next()) {
                searchedArticles.add(new Article(rs.getInt("articleId"), 
                                                 user.getUserId(), 
                                                 rs.getString("title"), 
                                                 rs.getString("imageURL"), 
                                                 rs.getDate("date"), 
                                                 rs.getString("articleText"), 
                                                 ArticleCategory.valueOf(rs.getString("articleCategory"))));
            }
            return searchedArticles;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public List<Article> getArticlesByCategories(String category){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM articles WHERE articleCategory = '%?%'");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            List<Article> searchedArticles = new ArrayList<>();
            
            while (rs.next()) {
                searchedArticles.add(new Article(rs.getInt("authorId"), 
                                                 rs.getInt("authorId"), 
                                                 rs.getString("title"), 
                                                 rs.getString("imageURL"), 
                                                 rs.getDate("date"), 
                                                 rs.getString("articleText"), 
                                                 ArticleCategory.valueOf(rs.getString("articleCategory"))));
            }
            return searchedArticles;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public List<Article> getArticlesInChronogicalOrder(ArticleCategory category) {
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps;
            if (category == null) {
                ps = currentCon.prepareStatement("SELECT * FROM articles ORDER BY articleId DESC");
            }
            else {
                ps = currentCon.prepareStatement("SELECT * FROM articles WHERE articleCategory = ? ORDER BY articleId DESC");
                ps.setString(1, category.name());
            }
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            List<Article> searchedArticles = new ArrayList<>();
            
            while (rs.next()) {
                searchedArticles.add(new Article(rs.getInt("articleId"), 
                                                 rs.getInt("authorId"), 
                                                 rs.getString("title"), 
                                                 rs.getString("imageURL"), 
                                                 rs.getDate("date"), 
                                                 rs.getString("articleText"), 
                                                 ArticleCategory.valueOf(rs.getString("articleCategory"))));
            }
            return searchedArticles;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public void insertArticle(Article article){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("INSERT INTO articles (authorId, title, imageURL, articleText, articleCategory, date) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, article.getAuthorId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getImageURL());
            ps.setString(4, article.getArticleText());
            ps.setString(5, article.getCategory().name());
            ps.setDate(6, (java.sql.Date) new Date());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void updateArticle(Article article){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("UPDATE articles SET authorId = ?, title = ?, imageURL = ?, articleText = ?, articleCategory = ? WHERE articleId = ?"); 
            ps.setInt(1, article.getAuthorId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getImageURL());
            ps.setString(4, article.getArticleText());
            ps.setString(5, article.getCategory().name());
            ps.setInt(6, article.getArticleId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteArticle(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("START TRANSLACTION;\n"
                    + "DELETE FROM comments WHERE articleId = ?;\n"
                    + "DELETE FROM articles WHERE articleId = ?");
            ps.setInt(1, id);
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.executeQuery("COMMIT;");
        }
        catch (SQLException se) {
            System.err.println("Got an exception! ");
            System.err.println(se.getMessage());
            try
            {
                PreparedStatement ps = currentCon.prepareStatement("ROLLBACK;");
                ps.executeUpdate();
                }
            catch (SQLException e)
            {
            }
        }
    }
}
