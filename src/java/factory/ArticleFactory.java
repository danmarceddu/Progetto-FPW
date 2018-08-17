/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import enumeration.ArticleCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Article;
import model.User;

/**
 *
 * @author Alessandro Pilosu
 */
public class ArticleFactory {
    
    static Connection currentCon = null;
    
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
    
    public List<Article> getArticlesByCategory(ArticleCategory category){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM articles WHERE articleCategory = ?");
            ps.setString(1, category.name());
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
                                                 category));
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
    
    public List<Article> getArticlesFromSpecificAuthor(User author) {
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM articles WHERE authorId = ?");
            ps.setInt(1, author.getUserId());
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            List<Article> searchedArticles = new ArrayList<>();
            
            while (rs.next()) {
                searchedArticles.add(new Article(author.getUserId(), 
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
            PreparedStatement ps = currentCon.prepareStatement("INSERT INTO articles (authorId, title, imageURL, articleText, articleCategory) VALUES (?, ?, ?, ?, ?");
            ps.setInt(1, article.getAuthorId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getImageURL());
            ps.setString(4, article.getArticleText());
            ps.setString(5, article.getCategory().name());

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
            PreparedStatement ps = currentCon.prepareStatement("DELETE FROM articles WHERE articleId = ?");
            ps.setInt(1, id);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
