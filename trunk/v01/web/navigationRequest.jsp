<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.Query,java.util.*,db.Request"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>navigation request</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
         <%
           Vector<Object> requests = Query.GetVector("Orders");
           String count = "requestServlet?count="+Integer.toString(requests.size());
         %>
      <div id="header"><h1>Taxi requests</h1></div>
      <form method="post" action=<%=count%>>
      <div id="sidebar">
         
              <input style="width: 35%" value="Create request" type="submit" name="create"><br><br>
         
         
              <input style="width: 35%" value="Edit request" type="submit" name="edit"><br><br>
       
          
              <input style="width: 35%" value="Delete request" type="submit" name="delete"><br><br>
              <input style="width: 35%" value="Copy request" type="submit" name="copy"><br><br>
              <input style="width: 35%" value="Sort request" type="submit" name="sort">
               <select name="select1" style="width: 50%;">
               <option value="TIME_ORD" selected>Time of receipt</option>
               <option value="TIME_DEST">Time of execution</option>
               <option value="ADDR_DEP">Departure adress</option>
               <option value="ADDR_DEST">Arrival adress</option>
               <option value="PASSENGER">Passenger's count</option>
               <option value="STATUS">Status</option>
               <option value="DIST">Distance</option>
               <option value="COST">Cost</option>
               </select>
               <br><br>
              <input style="width: 35%" value="Search request" type="submit" name="search">
               <select name="select2" style="width: 50%;">
               <option value="TIME_ORD" selected>Time of receipt</option>
               <option value="TIME_DEST">Time of execution</option>
               <option value="ADDR_DEP">Departure adress</option>
               <option value="ADDR_DEST">Arrival adress</option>
               <option value="PASSENGER">Passenger's count</option>
               <option value="STATUS">Status</option>
               <option value="DIST">Distance</option>
               <option value="COST">Cost</option>
              </select> <br><br>
              <input type="text" name="search" style="width: 98%;"/><br><br>
          <a href="index.jsp">tittle page</a>
      </div>
      <div id="content">
        <h2>Requests</h2>
        <table border="1" cellspacing="5" cellpadding="4">
            <tr style="color: blue">
                <td style="width: 10px"></td>
                <td>Time of receipt</td>
                <td>Time of execution</td>
                <td>Departure adress</td>
                <td>Arrival adress</td>
                <td>Passenger's count</td>
                <td>Status</td>
                <td>Distance</td>
                <td>Cost</td>
            </tr>
            <%
             requests = Query.GetVector("Orders");
             String request_id,checkbox;
             for (int i = 0; i< requests.size();i++) {
                 request_id = ""+((Request)(requests.elementAt(i))).order_id;
                 checkbox = "cb"+i;
                 Date TIME_ORD = ((Request)(requests.elementAt(i))).time_ord;
                 Date TIME_DEST = ((Request)(requests.elementAt(i))).time_dest;
                 String ADDR_DEP = ((Request)(requests.elementAt(i))).addr_dep;
                 String ADDR_DEST = ((Request)(requests.elementAt(i))).addr_dest;
                 int PASSENGER = ((Request)(requests.elementAt(i))).passenger;
                 int status = ((Request)(requests.elementAt(i))).status;
                 int dist = ((Request)(requests.elementAt(i))).dist;
                 int cost = ((Request)(requests.elementAt(i))).cost;
            %>
            <tr>
                <td><input value="<%=request_id%>" type="checkbox" name=<%=checkbox%>></td>
                <td><%=TIME_ORD%></td>
                <td><%=TIME_DEST%></td>
                <td><%=ADDR_DEP%></td>
                <td><%=ADDR_DEST%></td>
                <td><%=PASSENGER%></td>
                <td><%=status%></td>
                <td><%=dist%></td>
                <td><%=cost%></td>
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
