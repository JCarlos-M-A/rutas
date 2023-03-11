<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>

    <form action="<%=request.getContextPath()%>/operaciones/suma" method="post">
        <label for="">Numero 1:</label>
        <br>
        <input type="text" name="n1" id="n1">

        <hr>

        <label for="">Numero 2:</label>
        <br>
        <input type="text" name="n2" id="n2">

        <hr>

        <button type="submit">Calcular</button>
    </form>

</body>

</html>