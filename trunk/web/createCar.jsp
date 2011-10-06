<%-- 
    Document   : newjsp
    Created on : 05.10.2011, 18:45:51
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>create car</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
        <div id="header"><h1>Create new car</h1></div>
          <div id="sidebar" style="width: 30%;">
            <form action="CreateCarServlet" method="post">
            <table id="in">
            <tr>
             <td>Ref_type</td>
             <td><select name="ref_type" style="width: 100%;">
                     <option disabled selected></option>
                     <%
                      Vector<String> ides = Query.GetRefId();
                      String ref,ref_id;
                      for (int i = 0; i < ides.size();i++) {
                          ref = "refId"+ides.elementAt(i);
                          ref_id = ides.elementAt(i);
                      %>
                      <option value=<%=ref%>><%=ref_id%></option>
                      <%
                          }
                      %>
                      
               </select></td>
            </tr>
            <tr>
             <td>Gov_number</td>
             <td><input type="text" name="Gov_number"/></td>
            </tr>
            <tr>
             <td>Running</td>
             <td><input type="text" name="Running"/></td>
            </tr>
            </table>
            <input type="submit" name="create" value="Create car"/><br><br>
            <a href="index.jsp">tittle page</a>
            </form>
          </div>
          <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
