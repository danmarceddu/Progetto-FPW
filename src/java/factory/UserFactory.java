package factory;

import enumeration.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import model.User;

public class UserFactory {
    
    static Connection currentCon = null;
    
    public User getUserById(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM users WHERE userId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            User searchedUser = null;
            
            while (rs.next()) {
                searchedUser = new User(id, 
                                        rs.getString("name"), 
                                        rs.getString("surname"),
                                        rs.getString("username"),
                                        rs.getDate("birthday"),
                                        rs.getString("profileImageURL"),
                                        rs.getString("biography"));
            }
            return searchedUser;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public User getUserToModifyById(int id){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM users WHERE userId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();
            
            User searchedUser = null;
            
            while (rs.next()) {
                searchedUser = new User(id, 
                                        rs.getString("name"), 
                                        rs.getString("surname"),
                                        rs.getString("password"),
                                        rs.getString("username"),
                                        rs.getDate("birthday"),
                                        rs.getString("profileImageURL"),
                                        rs.getString("biography"),
                                        UserRole.valueOf(rs.getString("role")));
            }
            return searchedUser;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public List<User> getUsersByComments(List<Comment> comments){
        try {
            currentCon = ConnectionManager.getConnection();
            List<User> searchedUsers = new ArrayList();
            
            for(Comment comm : comments){
                PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM users WHERE userId = ?");
                ps.setInt(1, comm.getAuthorId());
                ResultSet rs = ps.executeQuery();

                if(rs == null)
                    throw new Exception();

                while (rs.next()) {
                    searchedUsers.add(new User (comm.getAuthorId(), 
                                                rs.getString("name"), 
                                                rs.getString("surname"),
                                                rs.getString("username"),
                                                rs.getDate("birthday"),
                                                rs.getString("profileImageURL"),
                                                rs.getString("biography")));
                }
            }
            return searchedUsers;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<User> getUsersBySearchInput(String query) {
        try {
            currentCon = ConnectionManager.getConnection();
            ArrayList<User> searchedUsers = new ArrayList();
            
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM users WHERE name = ? OR surname = ?");
            ps.setString(1, query);
            ResultSet rs = ps.executeQuery();

            if(rs == null)
                throw new Exception();

            while (rs.next()) {
                searchedUsers.add(new User (rs.getInt("userId"), 
                                            rs.getString("name"), 
                                            rs.getString("surname")));
            }
            return searchedUsers;
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public void insertUser(User user){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("INSERT INTO users (name, surname, password, username, birthday, profileImageURL, biography) VALUES (?, ?, ?, ?, ?, ?, ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());
            ps.setDate(5, user.getBirthday());
            ps.setString(6, user.getProfileImageURL());
            ps.setString(7, user.getBiography());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void updateUser(User user){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("UPDATE users SET name = ?, surname = ?, password = ?, username = ?, birthday = ?, profileImageURL = ?, biography = ? WHERE userId = ?"); 
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());
            ps.setDate(5, user.getBirthday());
            ps.setString(6, user.getProfileImageURL());
            ps.setString(7, user.getBiography());
            ps.setInt(8, user.getUserId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteUser(int id){
        currentCon = ConnectionManager.getConnection();
        try {
            PreparedStatement ps = currentCon.prepareStatement("START TRANSACTION;\n" +
                    "DELETE FROM comments WHERE authorId = ?;\n" +
                    "DELETE FROM articles WHERE authorId = ?;\n" +
                    "DELETE FROM users WHERE userId = ?;");
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            
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
    
    public static User Login(String username, String password){
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            User searchedUser = null;
            
            while (rs.next()) {
                searchedUser = new User(rs.getInt("userId"),
                                        rs.getString("name"), 
                                        rs.getString("surname"),
                                        UserRole.valueOf(rs.getString("role")));
            }
            return searchedUser;
        }
        catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
}