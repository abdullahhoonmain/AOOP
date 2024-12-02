<%-- 
    Document   : adminDASH
    Created on : Nov 27, 2024, 8:29:01 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <ul class="nav-links">
            <li><a href="addBook.jsp">Add Book</a></li>
            <li><a href="viewBooks.jsp">View Books</a></li>
            <li><a href="deleteBook.jsp">Delete Books</a></li>

            <li><a href="manageUsers.jsp">Manage Users</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
        <div class="content">
            <h2>Welcome to the Library Admin Panel</h2>
            <p>Manage books, users, and library resources efficiently.</p>
        </div>
    </div>
</body>
</html>
