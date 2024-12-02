<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Books</title>
        <link rel="stylesheet" href="style.css">

    
</head>
<body>
    <div class="header">
        <h1>Books in Library</h1>
        <a href="adminDASH.jsp">Back to Dashboard</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
            </tr>
        </thead>
        <tbody>
            <%
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    // Assuming DBConnection is a utility class for database connection
                    conn = DBConnection.getConnect();
                    String sql = "SELECT * FROM books";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String author = rs.getString("author");
                        String genre = rs.getString("genre");
            %>
            <tr>
                <td><%= id %></td>
                <td><%= title %></td>
                <td><%= author %></td>
                <td><%= genre %></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (conn != null) conn.close();
                    } catch (SQLException ex) {
                        out.println("<tr><td colspan='4'>Error closing resources: " + ex.getMessage() + "</td></tr>");
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
