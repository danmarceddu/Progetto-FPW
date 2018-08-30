<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Scrivi Articolo">
    <jsp:attribute name="head_area">

    </jsp:attribute>

    <jsp:attribute name="body_area">

        <h1 style="text-align: center;">Scrivi un Articolo</h1>

        <div class="center" style="width: 500px">
            <form class='center' action="" style='align: right;'>
                <div class='block'>
                    <label>Titolo</label>
                    <input class="input-center" id="title" name="title" type="text" placeholder="Inserisci titolo" required />
                </div>
                <div class='block'>
                    <label>Data</label>
                    <input class="input-center" id="data" name="data" type="date" required />
                </div>
                <div class='block'>
                    <label>Immagine</label>
                    <input class="input-center" id="image" name="title" type="text" placeholder="Inserisci URL immagine" />
                </div>
                <div class='block'>
                    <label>Testo</label>
                    <textarea class="input-center" id="text" name="text" rows='3' required /></textarea>
                </div>
                <div class='block'>
                    <label>Categoria</label>
                </div>
                <div class='block'>
                    <input class="input-center" id="category" name="category" type="radio" value="1" required /> Italia
                    <input class="input-center" id="category" name="category" type="radio" value="2" /> Estero
                </div>
                <div class='block'>
                    <input class="input-center" id="login-button" type="button" value="submit" />
            </form>
        </div>
    </jsp:attribute>
</t:layout>