<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:layout title="Articoli">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">
        <h1 style="text-align: center;">I Miei Articoli</h1>

        <div class="center table">
            <table>
                <tr>
                    <th style='min-width: 18%'>Data</th>
                    <th style='min-width: 54%'>Titolo</th>
                    <th style='min-width: 14%'>Modifica</th>
                    <th style='min-width: 14%'>Cancella</th>
                </tr>
                <c:forEach var='article' items='${searchedArticles}'>
                    <tr>
                        <td><fmt:formatDate type = "date" value = "${article.date}" /></td>
                        <td>${article.title}</td> 
                        <td><a href="scriviArticolo.html?nid=${article.articleId}"><i class="fas fa-edit"></i></a></td>
                        <td><a href="CancellaArticolo?nid=${article.articleId}"><i class="fas fa-trash"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <a class='element-right' href='scriviArticolo.html' style="margin-right: 50px;">Aggiungi articolo</a>
        </div>
    </jsp:attribute>
</t:layout>
