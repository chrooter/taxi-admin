
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ru.dreamjteam.xml.binds.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit car</title>
        	<link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
        <%
        String id = request.getParameter("id");

         String model ="";
        int ref_type=0;

         String gov_number="";
         int running=0;
        if (id != null)
            {
             Car car = (Car)request.getAttribute("car");
             ref_type = car.getCarTypeId();
             model = car.getCarModel();

             gov_number = car.getGovNumber();
             running = car.getRunning();
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
									<div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div"><a href="<%=request.getContextPath()%>/ViewCarListServlet">Машины</a></div></div></div></div>
                                                                        <div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewOrderListServlet">Заявки</a></div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeListServlet">Типы</a></div></div></div></div>
							</div>

							<div class="txt_content">

								<h2>Редактирование машины</h2>
                                                                <form action="<%=request.getContextPath()%>/EditCarServlet" method="post">
								<table class="c_table">
								<input type="hidden" name="id" value="<%=id%>">
									<tbody>
										<tr>
											<th><div class="nobr th_div table_div">Тип</div></th>
											<th><div class="nobr th_div table_div">Гос. номер</div></th>
											<th><div class="nobr th_div table_div">Пробег</div></th>
                                                                                        <th><div class="nobr th_div table_div">Марка</div></th>

											<th><div class="nobr table_div">Действия</div></th>
										</tr>
										<tr class="tr_1">
                                                                                    <td><div class="r_div"><input type="text" value="<%=ref_type%>" name="ref_type" /></div></td>
											<td><div class="r_div"><input type="text" value="<%=gov_number%>" name="gov_number" /></div></td>
											<td><div class="r_div"><input type="text" value="<%=running%>" name="running" /></div></td>
                                                                                        <td><div class="r_div"><input type="text" value="<%=model%>" name="model" /></div></td>
											<td class="links">
                                                                                            <input type="submit" value="edit">

											</td>
										</tr>



									</tbody>
								</table>
</form>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
    </body>
</html>
