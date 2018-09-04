<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Scrivi Articolo">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">

        <h1 style="text-align: center;">Aggiorna un Articolo</h1>

        <div class="center" style="width: 500px">
            <form class='center' action="scriviArticolo.html" style='align: right;' method="post">
                    <input type="hidden" id="articleId" name="articleId" value="${article.articleId}">
                    <input type="hidden" id="authorId" name="authorId" value="${article.authorId}">
                    <input type="hidden" id="date" name="date" value="${article.date}">
                <div class='block'>
                    <label>Titolo</label>
                    <input class="input-center" id="title" name="title" type="text" value="${article.title}" placeholder="Inserisci titolo" required />
                </div>
                <div class='block'>
                    <label>Immagine</label>
                    <input class="input-center" id="image" name="imageURL" type="text" value="${article.imageURL}" placeholder="Inserisci URL immagine" />
                </div>
                <div class='block'>
                    <label>Testo</label>
                    <textarea class="input-center" id="text" name="articleText" rows='3' required />${article.articleText}</textarea>
                </div>
                <div class='block'>
                    <label>Categoria</label>
                </div>
                <div class='block'>
                    <input class="input-center" id="category" name="category" type="radio" value="Italia" required /> Italia
                    <input class="input-center" id="category" name="category" type="radio" value="Estero" /> Estero
                </div>
                <div class='block'>
                    <input class="input-center" id="login-button" type="submit" value="Aggiorna" />
                </div>
            </form>
        </div>
    </jsp:attribute>
</t:layout>