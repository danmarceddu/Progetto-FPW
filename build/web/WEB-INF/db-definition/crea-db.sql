CREATE TABLE Users (
	  userId				int			 	AUTO_INCREMENT PRIMARY KEY
	, name				nvarchar(100)	NOT NULL
	, surname			nvarchar(100)	NOT NULL
	, password			nvarchar(100)	NOT NULL
	, username			nvarchar(100)	NOT NULL
	, birthday 			date 			 	NOT NULL
	, profileImageURL nvarchar(100)
	, biography			nvarchar(100)	NOT NULL
	, role				nvarchar(100)	NOT NULL DEFAULT 'user'
)

CREATE TABLE Articles (
	  articleId			int				AUTO_INCREMENT
	, authorId 			int				NOT NULL
	, title				nvarchar(100) 	NOT NULL
	, imageURL			nvarchar(100)	NOT NULL
	, date				date				NOT NULL
	, articleText		text				NOT NULL
	, articleCategory	nvarchar(100)	NOT NULL DEFAULT 'no_category'
	, PRIMARY KEY (articleId)
	, FOREIGN KEY (authorId)			REFERENCES Users(userId)
)

CREATE TABLE Comments (
	  commentId 		int				AUTO_INCREMENT
	, authorId			int				NOT NULL
	, articleId			int				NOT NULL
	, commentText		text				NOT NULL
	, PRIMARY KEY (commentId)
	, FOREIGN KEY (authorId)			REFERENCES Users(userId)
	, FOREIGN KEY (articleId)			REFERENCES Articles(articleId)
)