<%-- 
    Document   : createType
    Created on : 05.10.2011, 18:45:45
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>create type</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
        <div id="header"><h1>Create new type</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="CreateTypeServlet" method="post">
            <table id="in">
            <tr>
             <td>Name</td>
             <td><input type="text" name="Name"/></td>
            </tr>
            <tr>
             <td>Seating_capacity</td>
             <td><input type="text" name="Seating_capacity"/></td>
            </tr>
            <tr>
             <td>Capacity</td>
             <td><input type="text" name="Capacity"/></td>
            </tr>
            <tr>
             <td>Cost_per_km</td>
             <td><input type="text" name="Cost_per_km"/></td>
            </tr>
            </table>
            <input type="submit" name="create" value="Create type"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
