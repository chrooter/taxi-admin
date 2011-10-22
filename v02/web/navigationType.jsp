<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,ru.dreamjteam.xml.binds.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>navigation car</title>
        	<link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
   <div class="wrapper">
       <%
       CarTypes types = (CarTypes)request.getAttribute("types");
       %>
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
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewOrderListServlet">Заявки</a></div></div></div></div>
                                                                        <div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Типы</div></div></div></div>
							</div>

							<div class="txt_content">

								<h2>Типы машин</h2>

								<table class="c_table">
									<tbody>
										<tr>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet?orderBy=name" alt="">Тип</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet?orderBy=seating_capacity" alt="">Вместимость</a></div></th>
											<th><div class="nobr th_div table_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet?orderBy=COST_PER_KM" alt="">Расход на километр</a></div></th>

											<th><div class="nobr table_div">Действия</div></th>
										</tr>
                                                                                <%
                                                                                 int class_tr;
                                                                                 String name;
                                                                                 int seating_capacity;
                                                                                int id = 0;
                                                                                 int cost_per_km;
                                                                                 for(int i = 0; i < types.getCarType().size();i++)
                                                                                     {
                                                                                      class_tr = (i+1)%2;
                                                                                      if (class_tr == 0) class_tr+=2;
                                                                                      name = types.getCarType().get(i).getName();
                                                                                      seating_capacity = types.getCarType().get(i).getSeatCap();
                                                                                      id = types.getCarType().get(i).getId();
                                                                                      //request.setAttribute("id", id);
                                                                                      cost_per_km = types.getCarType().get(i).getCostPerKm();
                                                                                %>
										<tr class="tr_<%=class_tr%>">
											<td><div class="r_div"><%=name%></div></td>
											<td><div class="r_div"><%=seating_capacity%></div></td>
											<td><div class="r_div"><%=cost_per_km%></div></td>
											
											<td class="links">
												<a href="<%=request.getContextPath()%>/EditCarTypeServlet?id=<%=id%>"><img src="<%=request.getContextPath()%>/i/pen.png" alt=""/></a>
												<a href="<%=request.getContextPath()%>/DeleteCarTypeServlet?id=<%=id%>"><img src="<%=request.getContextPath()%>/i/del.png" alt=""/></a>

											</td>
										</tr>
                                                                                <%
                                                                                      }
                                                                                %>


									</tbody>
								</table>

								<p class="add_auto"><a href="<%=request.getContextPath()%>/CreateCarTypeServlet"><img src="<%=request.getContextPath()%>/i/add.png" alt=""/> <span>Добавить тип машины</span></a></p>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
    </body>
</html>
