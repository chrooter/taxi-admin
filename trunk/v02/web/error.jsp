<%-- 
    Document   : error
    Created on : 20.10.2011, 20:44:37
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error page</title>
    </head>
    <body>
        <h1>Произошла ошибка</h1>
        <%
        String error = (String)request.getAttribute("error");
        %>
        <%=error%>
    </body>
</html>
