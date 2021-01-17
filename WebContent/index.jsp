<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Home</title>
</head>

<body>
	 <form action="/Exercise/soa/RenderJson/Render" method="post" enctype="multipart/form-data">
        <input type="file" id="fileUpload" name="fileUpload">
        <select name="Type" id="Type">
            <option value="JRS-353">JRS-353</option>
            <option value="Jackson">Jackson</option>
            <option value="Gson">Gson</option>
            <option value="JavaEE8Enhancement">Java EE 8 Enhancement</option>
        </select>
        <input type="submit" value="Submit">
    </form>
    
    <a href="/Exercise/soa/RenderJson/GetData">Manage your data</a>
</body>

</html>