<%-- 
    Document   : layout
    Created on : 30-giu-2018, 19.32.47
    Author     : Alessandro Pilosu
--%>

<%@tag description="Layout Fast Press Writer" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<html>
    <head>
        <title>${title} | Fast Press Writer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Progetto di sviluppo Web">
        <meta name="keywords" content="AMM, università, blog">
        <meta name="generator" content="NetBeans">
        <meta name="author" content="Alessandro Pilosu">
        <link href="style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" crossorigin="anonymous">
        <jsp:invoke fragment="head_area"/>
    </head>
    <body>
        <aside>
            <h2 id="logo">Fast Press Writer</h2>
        </aside>
        <main>
            <ul class="menu">
                <li><a href="articoli.html">Notizie</a></li>
            </ul>
            <ul class="menu element-right">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li><a class="element-right" href="Login">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        Bentornato, <c:out value="${sessionScope.user.name}"/>
                        <li><a class="element-right" href="Logout">Logout</a></li>
                    </c:otherwise>
                </c:choose>
                    <c:if test="${sessionScope.user.role == 'author'}">
                        <li><a class="element-right" href="articoli.html">Pagina Articoli</a></li>
                    </c:if>
            </ul>
            <br style="clear: both;" />
            <jsp:invoke fragment="body_area"/>
        </main>
    </body>
</html>