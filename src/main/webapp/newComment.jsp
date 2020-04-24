<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">

    <title>Crea un commento</title>
</head>

<body>

    <div>
        <form action="CreateComment" method="POST">
            <% long postId = (long) request.getAttribute("postId");%>
            <input type="hidden" name="postId" value="<%=postId%>">
            <h1>Crea un nuovo commento</h1>
            <textarea  name="text" placeholder="Scrivi il tuo messaggio"></textarea>
            <button type="submit">Crea un commento</button>
        </form>
    </div>
</body>
</html>