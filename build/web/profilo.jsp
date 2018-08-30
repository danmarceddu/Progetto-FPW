<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Login">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">

        <h1 style="text-align: center;">Fast Press Writer</h1>

        <div class="center" style="width: 500px">
            <h2>Profilo di ${profileUser.username}</h2>
            <img class="thumb" src="${profileUser.profileImageURL}" style="float: left;" />
            <div>${profileUser.name} ${profileUser.surname}</div>
            <br />
            <div>Nato il ${profileUser.birthday}</div>
            <br />
            <br />
            <h3>${profileUser.biography}</h3>
        </div>
    </jsp:attribute>
</t:layout>