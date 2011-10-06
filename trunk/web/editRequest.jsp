<%-- 
    Document   : edit
    Created on : 01.10.2011, 15:14:22
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Request"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit request</title>
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
             Object c = Query.GetEditItem("Orders", "order_id", id);
             Request req = (Request)c;
             Date TIME_ORD = req.time_ord;
             Date TIME_DEST = req.time_dest;
             String ADDR_DEP = req.addr_dep;
             String ADDR_DEST = req.addr_dest;
             int PASSENGER = req.passenger;
             int STATUS = req.status;
             int DIST = req.dist;
             int COST = req.cost;
          %>
          <div id="header"><h1>Edit request</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="EditRequestServlet" method="post">
            <table id="in">
            <tr>
             <td>Time of receipt</td>
             <td><input type="text" name="Time of receipt" value=<%=TIME_ORD%>></td>
            </tr>
            <tr>
             <td>Time of execution</td>
             <td><input type="text" name="Time of execution" value=<%=TIME_DEST%>></td>
            </tr>
            <tr>
             <td>Departure adress</td>
             <td><input type="text" name="Departure adress" value=<%=ADDR_DEP%>></td>
            </tr>
            <tr>
             <td>Arrival adress</td>
             <td><input type="text" name="Arrival adress" value=<%=ADDR_DEST%>></td>
            </tr>
            <tr>
            <td>Passenger</td>
             <td><input type="text" name="Passenger" value=<%=PASSENGER%>></td>
            </tr>
            <tr>
             <td>Status</td>
             <td><input type="text" name="Status" value=<%=STATUS%>></td>
            </tr>
            <tr>
             <td>Distance</td>
             <td><input type="text" name="Distance" value=<%=DIST%>></td>
            </tr>
             <tr>
             <td>Cost</td>
             <td><input type="text" name="Cost" value=<%=COST%>></td>
            </tr>
            </table>
            <input type="submit" name="edit" value="Edit request"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
