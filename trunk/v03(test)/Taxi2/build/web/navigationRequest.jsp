<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,ru.dreamjteam.xml.binds.orders.*,ru.dreamjteam.db.*,ru.dreamjteam.xml.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>navigation request</title>
       	<link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
        <%
       Orders orders = (Orders)XMLParser.parseXML(OrderDb.select(),Orders.class);
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
									<div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Заявки</div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet">Типы</a></div></div></div></div>
							</div>

							<div class="txt_content">

								<h2>Все заявки</h2>

								<table class="c_table">
									<tbody>
										<tr>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=TIME_ORD" alt="">Время отправления</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=ADDR_DEP" alt="">Откуда</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=ADDR_DEST" alt="">Куда</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=PASSENGER" alt="">Количество пассажиров</a></div></th>
                                                                                        <th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=status" alt="">Статус</a></div></th>
                                                                                        <th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet?orderBy=cost" alt="">Цена</a></div></th>
											<th><div class="nobr table_div">Действия</div></th>
										</tr>
                                                                                  <%
                                                                                 int class_tr;
                                                                                 String TIME_ORD;
                                                                                 String ADDR_DEP;
                                                                                 String ADDR_DEst;
                                                                                 int pas;
                                                                                 String status;
                                                                                 int cost;
                                                                                 int id;
                                                                                 for(int i = 0; i < orders.getOrder().size();i++)
                                                                                     {
                                                                                      class_tr = (i+1)%2;
                                                                                      if (class_tr == 0) class_tr+=2;
                                                                                      TIME_ORD = orders.getOrder().get(i).getTimeOrd();
                                                                                      ADDR_DEP = orders.getOrder().get(i).getAddrDep();
                                                                                      ADDR_DEst = orders.getOrder().get(i).getAddrDest();
                                                                                      status = orders.getOrder().get(i).getStatus();
                                                                                      pas = orders.getOrder().get(i).getPassengers();
                                                                                      cost = orders.getOrder().get(i).getCost();
                                                                                      id = orders.getOrder().get(i).getId();
                                                                                %>
										<tr class="tr_<%=class_tr%>">
											<td><div class="r_div"><%=TIME_ORD%></div></td>
											<td><div class="r_div"><%=ADDR_DEP%></div></td>
											<td><div class="r_div"><%=ADDR_DEst%></div></td>
											<td><div class="r_div"><%=pas%></div></td>
                                                                                        <td><div class="r_div"><%=status%></div></td>
                                                                                        <td><div class="r_div"><%=cost%></div></td>
											<td class="links">
												<a href="<%=request.getContextPath()%>/editRequest.jsp?id=<%=id%>"><img src="i/pen.png" alt=""/></a>
												<a href="<%=request.getContextPath()%>/DeleteRequestServlet?id=<%=id%>"><img src="i/del.png" alt=""/></a>

											</td>
										</tr>
										<%
                                                                                      }
                                                                                %>


									</tbody>
								</table>

								<p class="add_auto"><a href="<%=request.getContextPath()%>/createRequest.jsp"><img src="i/add.png" alt=""/> <span>Добавить заявку</span></a></p>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
	
    </body>
</html>
