<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Login">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">

        <h1 style="text-align: center;"><i class="fas fa-comments"></i> Fast Press Writer</h1>

        <div>
            <form class='center' id="login-form" action="login.html" method="post">
                <h2>Accesso</h2>
                <label>Username</label>
                <input class="input-center" id="login-username" name="username" type="text" placeholder="Username" />
                <br />
                <label>Password</label>
                <input class="input-center" id="login-password" name="password" type="password" placeholder="Password"/>
                <br />
                <input class="input-center" id="login-button" type="submit" value="Accedi" />
            </form>
        </div>
    </jsp:attribute>
</t:layout>