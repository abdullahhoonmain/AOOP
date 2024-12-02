<%-- 
    Document   : addBook.jsp
    Created on : Nov 27, 2024, 8:46:11 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="form-container">
        <h1>Add New Book</h1>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="add">
            <label>Title:</label>
            <input type="text" name="title" required>
            <label>Author:</label>
            <input type="text" name="author" required>
            <label>Genre:</label>
            <input type="text" name="genre">
            <button type="submit">Add Book</button>
        </form>
        <div class="container my-4">
            <a href="adminDASH.jsp">Go back to dashboard </a>
        </div>
    </div>
</body>
</html>
