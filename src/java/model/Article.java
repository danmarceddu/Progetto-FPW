/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeration.ArticleCategory;
import java.sql.Date;

/**
 *
 * @author Alessandro Pilosu
 */
public class Article {
    private int articleId;
    private int authorId;
    private String title;
    private String imageURL;
    private Date date;
    private String articleText;
    private ArticleCategory category;
    
    /* Constructors */
    
    public Article(int authorId, String title, String imageURL, String articleText, ArticleCategory category){
        this.authorId = authorId;
        this.title = title;
        this.imageURL = imageURL;
        this.articleText = articleText;
        this.category = category;
    }
    
        public Article(int articleId, int authorId, String title, String imageURL, String articleText, ArticleCategory category){
        this.articleId = articleId;
        this.authorId = authorId;
        this.title = title;
        this.imageURL = imageURL;
        this.articleText = articleText;
        this.category = category;
    }
    
    public Article(int articleId, int authorId, String title, String imageURL, Date date, String articleText, ArticleCategory category){
        this.articleId = articleId;
        this.authorId = authorId;
        this.title = title;
        this.imageURL = imageURL;
        this.date = date;
        this.articleText = articleText;
        this.category = category;
    }
        
    /* Methods */

    public int getArticleId() {
        return articleId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }
}
