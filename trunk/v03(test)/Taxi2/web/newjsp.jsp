<%@ page import="javax.naming.*, javax.sql.*, java.sql.*, java.util.Locale, ru.dreamjteam.xml.binds.orders.*" language="java" contentType="text/html; charset=Cp1251"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <table border=1>
	<%
		int conditions = 1;
                Order ord = null;
                String orderBy = "ORDER_ID";
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Locale.setDefault(Locale.ENGLISH);
            Connection conn = ds.getConnection();

            String query = "SELECT "
                    + "ORDER_ID,"
                    + "TIME_ORD,"
                    + "TIME_DEST,"
                    + "ADDR_DEP,"
                    + "ADDR_DEST,"
                    + "PASSENGER,"
                    + "STATUS,"
                    + "DIST_APPR,"
                    + "DIST_INFACT,"
                    + "COST"
                    + " FROM ORDERS ";
            
            if (ord != null) {            
                query += "WHERE ORDER_ID = ORDER_ID";
            
                if (ord.getId() != 0)
                    query += "AND ORDER_ID = ? ";
                if (ord.getTimeOrd() != null)
                    query += "AND TIME_ORD = ? ";
                if (ord.getTimeDest() != null)
                    query += "AND TIME_DEST = ? ";
                if (ord.getAddrDep() != null)
                    query += "AND ADDR_DER = ? ";
                if (ord.getAddrDest() != null)
                    query += "AND ADDR_DEST = ? ";
                if (ord.getPassengers() != 0)
                    query += "AND PASSENGER = ? ";
                if (ord.getStatus() != null)
                    query += "AND STATUS = ? ";
                if (ord.getDistAppr() != 0)
                    query += "AND DIST_APPR = ? ";
                if (ord.getDistInfact() != 0)
                    query += "AND DIST_INFACT = ? ";
                if (ord.getCost() != 0)
                    query += "AND COST = ? ";
                if (ord.getId() != 0)
                    query += "AND ORDER_ID = ? ";
            }
            query = query + "ORDER BY " + orderBy;
                        
            PreparedStatement ps = conn.prepareStatement(query);
            
            if (ord != null) {
                if (ord.getId() != 0)
                    ps.setInt(conditions++, ord.getId());
                if (ord.getTimeOrd() != null)
                    ps.setString(conditions++, "%"+ord.getTimeOrd()+"%");
                if (ord.getTimeDest() != null)
                    ps.setString(conditions++, "%"+ord.getTimeDest()+"%");
                if (ord.getAddrDep() != null)
                    ps.setString(conditions++, "%"+ord.getAddrDep()+"%");
                if (ord.getAddrDest() != null)
                    ps.setString(conditions++, "%"+ord.getAddrDest()+"%");
                if (ord.getPassengers() != 0)
                    ps.setInt(conditions++, ord.getPassengers());
                if (ord.getStatus() != null)
                    ps.setString(conditions++, ord.getStatus());
                if (ord.getDistAppr() != 0)
                    ps.setInt(conditions++, ord.getDistAppr());
                if (ord.getDistInfact() != 0)
                    ps.setInt(conditions++,ord.getDistInfact());
                if (ord.getCost() != 0)
                    ps.setInt(conditions++, ord.getCost());
            }                                 
            ResultSet rs = ps.executeQuery();            

            Orders rows = new Orders();
            while (rs.next()) {
				%>
				<tr>
					<td><%=rs.getInt("ORDER_ID")%></td>
					<td><%=rs.getString("TIME_ORD")%></td>
                                        <td>tralala</td>
				</tr>
				<%
		}
		//çàêðûâàåì îáúåêòû - îñâîáîæäàåì ðåñóðñû
		ps.close();
		rs.close();
		//à ýòîò âûçîâ ôàêòè÷åñêè íå çàêðûâàåò ñîåäèíåíèå, à âîçâðàùàåò åãî â ïóë
		conn.close();
	%>
	</table>
    </body>
</html>
