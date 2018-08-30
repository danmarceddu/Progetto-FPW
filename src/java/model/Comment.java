package model;

public class Comment {
    private int commentId;
    private int authorId;
    private int articleId;
    private String commentText;
    
    public Comment(int authorId, int articleId, String commentText){
        this.authorId = authorId;
        this.articleId = articleId;
        this.commentText = commentText;
    }
    
    public Comment(int commentId, int authorId, int articleId, String commentText){
        this.commentId = commentId;
        this.authorId = authorId;
        this.articleId = articleId;
        this.commentText = commentText;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    
    
}
