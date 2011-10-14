<%-- 
    Document   : create
    Created on : 01.10.2011, 14:56:05
    Author     : диман
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>create request</title>
        	<link rel="stylesheet" href="css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="js/pie.js"></script>
		<script type="text/javascript" src="js/ie.js"></script><![endif]-->
    </head>
    <body>
        <%
        String date = (new SimpleDateFormat("dd.MM.yyyy hh:mm")).format(new Date());
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

								<h2>Создание заявки</h2>

								<table class="c_table">
									<!--<col width="25%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="20%"/>
									<col width="*"/>-->
									<tbody>
                                                                            <tr class="tr_1">
											<th><div class="nobr th_div table_div">Время отправления</div></th>
                                                                                        <td ><div class="r_div"><input type="text" value="<%=date%>" name="TIME_ORD" /></div></td>
											
										</tr>
										<tr class="tr_1">
                                                                                    <th><div class="nobr th_div table_div">Время прибытия</div></th>
                                                                                    <td><div class="r_div"><input type="text"  name="TIME_DEST" /></div></td>

											
                                                                                       
                                                                                       
											
										</tr>
                                                                                <tr class="tr_1">
                                                                                    <th><div class="nobr th_div table_div">Адресс отправления</div></th>
                                                                                    <td><div class="r_div"><input type="text" name="ADDR_DEP" /></div></td>
                                                                                    
                                                                                </tr>
                                                                                <tr class="tr_1">
                                                                                    <th><div class="nobr th_div table_div">Адресс прибытия</div></th>
                                                                                    <td><div class="r_div"><input type="text" name="ADDR_DEST" /></div></td>
                                                                                    
                                                                                        
                                                                                </tr>
                                                                                <tr class="tr_1">
                                                                                     <th><div class="nobr th_div table_div">Число пассажиров</div></th>
                                                                                     <td><div class="r_div"><input type="text" name="PASSENGER" /></div></td>
                                                                                </tr>
                                                                                 <tr class="tr_1">
                                                                                     <th><div class="nobr th_div table_div">Статус</div></th>
                                                                                     <td><div class="r_div"><input type="text" name="STATUS" /></div></td>
                                                                                </tr>
                                                                                 <tr class="tr_1">
                                                                                      <th><div class="nobr th_div table_div">Дальность</div></th>
                                                                                       <td><div class="r_div"><input type="text" name="Dist" /></div></td>
                                                                                </tr>
                                                                                 <tr class="tr_1">
                                                                                     <th><div class="nobr th_div table_div">Цена</div></th>
                                                                                     <td><div class="r_div"><input type="text" name="cost" /></div></td>

                                                                                </tr>

                                                                                <tr class="tr_1">
                                                                                    <th><div class="nobr table_div">Действия</div></th>
                                                                                    <td class="links">
												<a href="<%=request.getContextPath()%>/CreateRequestServlet"><img src="i/add.png" alt=""/></a>


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
