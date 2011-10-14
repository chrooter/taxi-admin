<%-- 
    Document   : edit
    Created on : 01.10.2011, 15:14:22
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ru.dreamjteam.xml.binds.orders.*,ru.dreamjteam.xml.XMLParser,ru.dreamjteam.db.OrderDb"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit request</title>
       <link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
           <%
        String id = request.getParameter("id");
        String TIME_ORD = "";
        String TIME_DEST = "";
        String ADDR_DEP = "";
        String ADDR_DEST = "";
        int PASSENGER = 0;
        int dist = 0;
        int cost = 0;
        String status = "";
        if (id != null)
            {
             Orders orders = XMLParser.parseXML(OrderDb.select("ORDER_ID",Integer.parseInt(id)),Orders.class);

             TIME_ORD = orders.getOrder().get(0).getTimeOrd();
             TIME_DEST = orders.getOrder().get(0).getTimeDest();
             ADDR_DEP = orders.getOrder().get(0).getAddrDep();
             ADDR_DEST = orders.getOrder().get(0).getAddrDest();
             PASSENGER = orders.getOrder().get(0).getPassengers();
             dist = orders.getOrder().get(0).getDistInfact();
             cost = orders.getOrder().get(0).getCost();
             status = orders.getOrder().get(0).getStatus();
            }
        %>
         <div class="wrapper">
			<div class="head"><div class="head_inner">
				<a href="#" class="logo"></a>
				<a href="#" class="o_code"></a>
			</div></div>
			<div class="content box">

				<div class="top pr"><div class="top2"><div class="top3">
					<div class="top_t">Служба такси</div>
				</div></div></div>

				<div class="text_content box">

					<div class="fl pr l_side"><div class="l_side_inner">
						<h2>необработанные заявки</h2>
					</div></div>

					<div class="box txt_wrap">
						<div class="txt pr zoom after">

							<div class="zoom after pr menu1">
									  <div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet">Машины</a></div></div></div></div>
									<div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet">Заявки</a></div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet">Типы</a></div></div></div></div>
							</div>

							<div class="txt_content">

								<h2>Редактирование заявки</h2>

								<table class="c_table">
									<!--<col width="25%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="20%"/>
									<col width="*"/>-->
									<tbody>
										<tr>
											<th><div class="nobr th_div table_div">Время отправления</div></th>
                                                                                        <td><div class="r_div"><input type="text" value="<%=TIME_ORD%>" name="TIME_ORD" /></div></td>

										</tr>
										<tr>
                                                                                    <th><div class="nobr th_div table_div">Время прибытия</div></th>
											<td><div class="r_div"><input type="text" value="<%=TIME_DEST%>" name="TIME_DEST" /></div></td>





										</tr>
                                                                                <tr >
                                                                                    <th><div class="nobr th_div table_div">Адресс отправления</div></th>
                                                                                    <td><div class="r_div"><input type="text" value="<%=ADDR_DEP%>" name="ADDR_DEP" /></div></td>

                                                                                </tr>
                                                                                <tr >
                                                                                    <th><div class="nobr th_div table_div">Адресс прибытия</div></th>
                                                                                    <td><div class="r_div"><input type="text" value="<%=ADDR_DEST%>" name="ADDR_DEST" /></div></td>


                                                                                </tr>
                                                                                <tr>
                                                                                     <th><div class="nobr th_div table_div">Число пассажиров</div></th>
                                                                                     <td><div class="r_div"><input type="text" value="<%=PASSENGER%>" name="PASSENGER" /></div></td>
                                                                                </tr>
                                                                                 <tr>
                                                                                     <th><div class="nobr th_div table_div">Статус</div></th>
                                                                                     <td><div class="r_div"><input type="text" value="<%=status%>" name="STATUS" /></div></td>
                                                                                </tr>
                                                                                 <tr>
                                                                                      <th><div class="nobr th_div table_div">Дальность</div></th>
                                                                                       <td><div class="r_div"><input type="text" value="<%=dist%>" name="Dist" /></div></td>
                                                                                </tr>
                                                                                 <tr>
                                                                                     <th><div class="nobr th_div table_div">Цена</div></th>
                                                                                     <td><div class="r_div"><input type="text" value="<%=cost%>" name="cost" /></div></td>

                                                                                </tr>

                                                                                <tr>
                                                                                    <th><div class="nobr table_div">Действия</div></th>
                                                                                    <td class="links">
												<a href="<%=request.getContextPath()%>/EditRequestServlet?id=<%=id%>"><img src="i/pen.png" alt=""/></a>


											</td>
                                                                                </tr>
									</tbody>
								</table>



							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
    </body>
</html>
