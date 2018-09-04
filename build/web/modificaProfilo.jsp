<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Login">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">
        
        <div class="center" style="width: 500px">
            <form class='center' action="/FastPressWriter/profilo.html" method="post">
                <c:if test="${not empty profileUser.profileImageURL}">
                    <img class="thumb" src="${profileUser.profileImageURL}" style="float: left;" />
                </c:if>
                <h2>Profilo</h2>
                <br />
                <input type="hidden" id="userid" name="userId" value="${profileUser.userId}">
                <div style="width: 50%; float: left">
                    <label>Username</label>
                    <br />
                    <input class="input-center" name="username" type="text" value="${profileUser.username}" />
                    <br /><br />
                    <label>Nome</label>
                    <br />
                    <input class="input-center" name="name" type="text" value="${profileUser.name}" />
                    <br /><br />
                    <label>Cognome</label>
                    <br />
                    <input class="input-center" name="surname" type="text" value="${profileUser.surname}" />
                    <br /><br />
                    <label>Data di Nascita</label>
                    <br />
                    <input class="input-center" name="birthday" type="date" value="${profileUser.birthday}" />
                </div>
                
                <div style="width: 50%; float: left">
                    <label>Password</label>
                    <br />
                    <input class="input-center" name="password" type="password" />
                    <br /><br />
                    <label>Conferma Password</label>
                    <br />
                    <input class="input-center" name="password" type="password" />
                    <br /><br />
                    <label>URL immagine profilo</label>
                    <br />
                    <input class="input-center" name="imageURL" type="text" value="${profileUser.profileImageURL}" />
                    <br /><br />
                    <label>Biografia</label>
                </div>
                <br />
                <input class="input-center" name="biography" type="text" value="${profileUser.biography}" />
                <br /><br />
                <input class="input-center" type="submit" value="Aggiorna Profilo" />
            </form>
                
            <form style="float: right;" action="CancellaProfilo" method="post">
                <input class="input-center" type="submit" value="Voglio cancellare il mio profilo" />
            </form>
        </div>
    </jsp:attribute>
</t:layout>