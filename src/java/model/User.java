/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeration.UserRole;
import java.sql.Date;

/**
 *
 * @author Alessandro Pilosu
 */
public class User {
    private int userId;
    private String name;
    private String surname;
    private String password;
    private String username;
    private Date birthday;
    private String profileImageURL;
    private String biography;
    private UserRole role;
    
    public User(String name, String surname, UserRole role){
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
    
    public User(int userId, String name, String surname, Date birthday, String profileImageURL, String biography){
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.profileImageURL = profileImageURL;
        this.biography = biography;
    }
    
    public User(String name, String surname, String password, String username, Date birthday, String profileImageURL, String biography, UserRole role){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.profileImageURL = profileImageURL;
        this.biography = biography;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
