<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Notizia">
    <jsp:attribute name="head_area">
        <c:set var="i" value="0" />
    </jsp:attribute>
        
    <jsp:attribute name="body_area">

        <h1 style="text-align: center;">Fast Press Writer</h1>

        <div class="center" style="width: 500px">

            <div class='notizia'>
                <div>
                    <div class='element-right'><fmt:formatDate type = "date" value = "${article.date}" /></div>
                    <h3>${article.title}</h3>
                    <img class='thumb' src="${article.imageURL}"/>
                    <div>${article.articleText}</div>
                </div>
                <div style="clear: both; padding-top: 5px;">
                    <div class="element-right">Di ${author.name} ${author.surname}</div>
                    <div>${article.category}</div>
                </div>
                <br />
                <br />
                <br />
                <c:forEach var="comment" items="${usersComments}">
                    <div>
                        <div>${comments[i].commentText}</div>
                        <br />

                        <div class="element-right">Di ${usersComments[i].name} ${usersComments[i].surname}</div>
                        <br />
                    </div>
                    <fmt:parseNumber var="i" type="number" value="${i}+1" />
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</t:layout>