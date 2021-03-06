<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Notizie">
    <jsp:attribute name="head_area">
        <c:set var="i" value="0" />
    </jsp:attribute>

    <jsp:attribute name="body_area">

        <h1 style="text-align: center;">Fast Press Writer</h1>
        <div style="width: 50px; float: left">
            <h3>Categorie</h3>
            <br />
            <a href="Notizie">Tutti</A>
            <br />
            <a href="Notizie?category=Italia">Italia</A>
            <br />
            <a href="Notizie?category=Estero">Estero</A>
        </div>

        <div class="center" style="width: 500px">
            <c:set var="init" value="0" />
            <c:forEach var="article" items="${searchedCorrectedArticles}">
                <div class='notizia'>
                    <div>
                        <div class='element-right'><fmt:formatDate type = "date" value = "${article.date}" /></div>
                        <h3><a href="notizia.html?nid=${article.articleId}">${article.title}</a></h3>
                        <img class='thumb' src="${article.imageURL}"/>
                        <div>${article.articleText}
                    </div>
                    <div style="clear: both; padding-top: 5px;">
                        <div class="element-right">Di ${searchedAuthors[i].name} ${searchedAuthors[i].surname}</div>
                        <div>${article.category}</div>
                    </div>
                </div>
                <fmt:parseNumber var="i" type="number" value="${i}+1" />
            </c:forEach>
        </div>
    </jsp:attribute>
</t:layout>