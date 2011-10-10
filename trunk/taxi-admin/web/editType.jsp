<%-- 
    Document   : editType
    Created on : 05.10.2011, 18:46:17
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Type"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit type</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
         <%
             int count = Integer.parseInt(request.getParameter("count"));
             int id = 0;
              for (int i = 0; i < count; i++)
                 {
                  if (request.getParameter("cb"+i) != null) {
                      id = Integer.parseInt(request.getParameter("cb"+i));
                  }
                 }
             Object c = Query.GetEditItem("Types", "type_id", id);
             Type t = (Type)c;
             String name = t.name;
             int seating_capacity = t.seating_capacity;
             int capacity = t.capacity;
             int cost_per_km = t.cost_per_km;
          %>
         <div id="header"><h1>Edit type</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="editTypeServlet" method="post">
            <table id="in">
            <tr>
             <td>Name</td>
             <td><input type="text" name="Name" value=<%=name%>></td>
            </tr>
            <tr>
             <td>Seating_capacity</td>
             <td><input type="text" name="Seating_capacity" value=<%=seating_capacity%>></td>
            </tr>
            <tr>
             <td>Capacity</td>
             <td><input type="text" name="Capacity" value=<%=capacity%>></td>
            </tr>
            <tr>
             <td>Cost_per_km</td>
             <td><input type="text" name="Cost_per_km" value=<%=cost_per_km%>></td>
            </tr>
            </table>
            <input type="submit" name="edit" value="Edit type"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
