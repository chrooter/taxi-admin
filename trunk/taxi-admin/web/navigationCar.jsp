<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Car"%>
<!DOCCar HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Car" content="text/html; charset=UTF-8">
        <title>navigation car</title>
        <link rel="stylesheet" Car="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
          <div id="header"><h1>Taxi cars</h1></div>
          <%
           Vector<Object> Cars = Query.GetVector("Cars");
           String count = "carServlet?count="+Integer.toString(Cars.size());
          %>
          <form method="post" action=<%=count%>>
          <div id="sidebar">
              <input style="width: 35%" value="Create Car" Type="submit" name="create"><br><br>
              <input style="width: 35%" value="Edit Car" Type="submit" name="edit"><br><br>

            <a href="index.jsp">tittle page</a>
           </div>
           <div id="content">
            <h2>Cars</h2>
            <table border="1" cellspacing="5" cellpadding="4">
                <tr style="color: blue">
                    <td style="width: 10px"></td>
                    <td>ref_type</td>
                    <td>gov_number</td>
                    <td>running</td>
                </tr>
                <%
                 String Car_id,checkbox;
                 for (int i = 0; i< Cars.size();i++) {
                     Car_id = ""+((Car)(Cars.elementAt(i))).car_id;
                     checkbox = "cb"+i;
                     int ref_type = ((Car)(Cars.elementAt(i))).ref_type;
                     String gov_number = ((Car)(Cars.elementAt(i))).gov_number;
                     int running = ((Car)(Cars.elementAt(i))).running;
                %>
                <tr>
                    <td><input value=<%=Car_id%> Type="checkbox" name=<%=checkbox%>></td>
                    <td><%=ref_type%></td>
                    <td><%=gov_number%></td>
                    <td><%=running%></td>
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
