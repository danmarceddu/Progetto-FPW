package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comment;

public class CommentFactory {
    
    static Connection currentCon = null;
    
    public Comment getCommentById(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM comments WHERE commentId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            Comment searchedComment = null;
            
            while (rs.next()) {
                searchedComment = new Comment(id, 
                                              rs.getInt("authorId"),
                                              rs.getInt("articleId"), 
                                              rs.getString("commentText"));
            }
            return searchedComment;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public List<Comment> getCommentsByArticleId(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM comments WHERE articleId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            List<Comment> searchedComments = new ArrayList<>();
            
            while (rs.next()) {
                searchedComments.add(new Comment(rs.getInt("commentId"), 
                                                 rs.getInt("authorId"), 
                                                 id, 
                                                 rs.getString("commentText")));
            }
            return searchedComments;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public void insertComment(Comment comment){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("INSERT INTO comments (authorId, articleId, commentText) VALUES (?, ?, ?");
            ps.setInt(1, comment.getAuthorId());
            ps.setInt(2, comment.getArticleId());
            ps.setString(3, comment.getCommentText());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void updateComment(Comment comment){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("UPDATE comments SET commentText = ? WHERE commentId = ?"); 
            ps.setString(1, comment.getCommentText());
            ps.setInt(2, comment.getCommentId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteComment(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("DELETE FROM comments WHERE commentId = ?");
            ps.setInt(1, id);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
