<%-- 
    Document   : navigationType
    Created on : 05.10.2011, 18:42:59
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Type"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>navigation car</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
         <%
           Vector<Object> types = Query.GetVector("Types");
           String count = "typeServlet?count="+Integer.toString(types.size());
          %>
          <div id="header"><h1>Taxi types</h1></div>
          <form method="post" action=<%=count%>>
          <div id="sidebar">
              <input style="width: 35%" value="Create type" type="submit" name="create"><br><br>
              <input style="width: 35%" value="Edit type" type="submit" name="edit"><br><br>
              <a href="index.jsp">tittle page</a>
           </div>
           <div id="content">
            <h2>Types</h2>
            <table border="1" cellspacing="5" cellpadding="4">
                <tr style="color: blue">
                    <td style="width: 10px"></td>
                    <td>NAME</td>
                    <td>seating_capacity</td>
                    <td>capacity</td>
                    <td>cost_per_km</td>
                </tr>
                <%
                 types = Query.GetVector("Types");
                 String Type_id,checkbox;
                 for (int i = 0; i< types.size();i++) {
                     Type_id = ""+((Type)(types.elementAt(i))).type_id;
                     checkbox = "cb"+i;
                     String NAME = ((Type)(types.elementAt(i))).name;
                     int seating_capacity = ((Type)(types.elementAt(i))).seating_capacity;
                     int capacity = ((Type)(types.elementAt(i))).capacity;
                     int cost_per_km = ((Type)(types.elementAt(i))).cost_per_km;
                %>
                <tr>
                    <td><input value=<%=Type_id%> type="checkbox" name=<%=checkbox%>></td>
                    <td><%=NAME%></td>
                    <td><%=seating_capacity%></td>
                    <td><%=capacity%></td>
                    <td><%=cost_per_km%></td>
                </tr>
                <%
                     }
                %>
               
            </table>
      </div>
     </form>
             <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
