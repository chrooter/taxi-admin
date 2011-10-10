
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Car"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit car</title>
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
             Object c = Query.GetEditItem("Cars", "car_id", id);
             Car car = (Car)c;
             int ref_type = car.ref_type;
             int running = car.running;
             String gov_number = car.gov_number;
          %>
         <div id="header"><h1>edit car</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="editCarServlet" method="post">
            <table id="in">
            <tr>
             <td>Ref_type</td>
             <td><select name="Ref_type" style="width: 100%;">
                     <%
                      Vector<String> ides = Query.GetRefId();
                      String ref,ref_id;
                      for (int i = 0; i < ides.size();i++) {
                          ref = "refId"+ides.elementAt(i);
                          ref_id = ides.elementAt(i);
                          if (Integer.parseInt(ref_id) == ref_type) {
                              %>
                              <option value=<%=ref%> selected><%=ref_id%></option>
                     <%
                              }
                          else {
                              %>
                              <option value=<%=ref%>><%=ref_id%></option>
                     <%
                              }
                          }
                     %>

               </select></td>
            </tr>
            <tr>
             <td>Gov_number</td>
             <td><input type="text" name="Gov_number" value=<%=gov_number%>></td>
            </tr>
            <tr>
             <td>Running</td>
             <td><input type="text" name="Running" value=<%=running%>></td>
            </tr>
            </table>
            <input type="submit" name="edit" value="Edit car"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
