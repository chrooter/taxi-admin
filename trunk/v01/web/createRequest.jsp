<%-- 
    Document   : create
    Created on : 01.10.2011, 14:56:05
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>create request</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
          <div id="header"><h1>Create new request</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="CreateRequestServlet" method="post">
            <table id="in">
            <tr>
             <td>Time of receipt</td>
             <td><input type="text" name="Time of receipt"/></td>
            </tr>
            <tr>
             <td>Time of execution</td>
             <td><input type="text" name="Time of execution"/></td>
            </tr>
            <tr>
             <td>Departure adress</td>
             <td><input type="text" name="Departure adress"/></td>
            </tr>
            <tr>
             <td>Arrival adress</td>
             <td><input type="text" name="Arrival adress"/></td>
            </tr>
            <tr>
             <td>Passenger</td>
             <td><input type="text" name="Passenger"/></td>
            </tr>
            <tr>
             <td>Status</td>
             <td><input type="text" name="Status"/></td>
            </tr>
            <tr>
             <td>Distance</td>
             <td><input type="text" name="Distance"/></td>
            </tr>
             <tr>
             <td>Cost</td>
             <td><input type="text" name="Cost"/></td>
            </tr>
            </table>
            <input type="submit" name="create" value="Create request"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
