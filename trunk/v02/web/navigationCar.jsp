<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,ru.dreamjteam.xml.binds.carReports.*"%>
<!DOCCar HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Car" content="text/html; charset=UTF-8">
        <title>navigation car</title>
       	<link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
     <%
       CarReports cars = (CarReports)request.getAttribute("cars");
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
									<div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Машины</div></div></div></div>
                                                                        <div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewRequestListServlet">Заявки</a></div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet">Типы</a></div></div></div></div>
							</div>

							<div class="txt_content">

								<h2>Ближайшие автотесты</h2>

								<table class="c_table">
									<tbody>
										<tr>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet?orderBy=model" alt="">Марка/модель</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet?orderBy=gov_number" alt="">Гос. Номер</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet?orderBy=seating_capacity" alt="">Вместимость</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet?orderBy=status" alt="">Статус</a></div></th>
											<th><div class="nobr table_div">Действия</div></th>
										</tr>
                                                                                 <%
                                                                                 int class_tr;
                                                                                 String model;
                                                                                 int seating_capacity;
                                                                                 int id;
                                                                                 int id_order;
                                                                                 String gov_number;
                                                                                 String status;
                                                                                 for(int i = 0; i < cars.getCarReport().size();i++)
                                                                                     {
                                                                                      class_tr = (i+1)%2;
                                                                                      if (class_tr == 0) class_tr+=2;
                                                                                      model = cars.getCarReport().get(i).getCarModel();
                                                                                      id_order = cars.getCarReport().get(i).getIdOrder();
                                                                                      seating_capacity = cars.getCarReport().get(i).getSeatCap();
                                                                                      id = cars.getCarReport().get(i).getIdCar();
                                                                                      status = cars.getCarReport().get(i).getStatus();
                                                                                      gov_number = cars.getCarReport().get(i).getGovNumber();
                                                                                %>
										<tr class="tr_<%=class_tr%>">
											<td><div class="r_div"><%=model%></div></td>
											<td><div class="r_div"><%=gov_number%></div></td>
											<td><div class="r_div"><%=seating_capacity%></div></td>
											<td><div class="r_div">
                                                                                                <%=status%>
                                                                                                <%
                                                                                                 if (status.equals("execute")) {
                                                                     
                                                                                                %>
                                                                                                <a href="<%=request.getContextPath()%>/editRequest.jsp?id=<%=id_order%>" >#<%=id%></a>
                                                                                                <% 
                                                                                                 }                                                                                                   
                                                                                                %>
                                                                                            </div></td>
											<td class="links">
												<a href="<%=request.getContextPath()%>/editCar.jsp?id=<%=id%>"><img src="i/pen.png" alt=""/></a>
												<a href="<%=request.getContextPath()%>/DeleteCarServlet?id=<%=id%>"><img src="i/del.png" alt=""/></a>

											</td>
										</tr>
									        <%
                                                                                      }
                                                                                %>


									</tbody>
								</table>

								<p class="add_auto"><a href="<%=request.getContextPath()%>/createCar.jsp"><img src="i/add.png" alt=""/> <span>Добавить машину</span></a></p>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
	
    </body>
</html>
