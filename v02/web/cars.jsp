<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<title>Список машин</title>
        <meta name="description" content="" />
        <meta name="keywords" content="" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ie.css" /><![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
		<!--[if IE]><script type="text/javascript" src="<%=request.getContextPath()%>/js/pie.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/ie.js"></script><![endif]-->
	</head>
	<body>
		<div class="wrapper">
			<div class="head"><div class="head_inner">
				<a href="<%=request.getContextPath()%>/" class="app-logo"></a>
				<a href="#" class="logo"></a>
			</div></div>
			<div class="content box">
				<div class="top pr"><div class="top2"><div class="top3">
					<div class="top_t">Служба такси</div>
					<div class="fr top_inf">
					</div>
				</div></div></div>
				<div class="text_content box">
					<jsp:include page="/newRequests.jsp"/>
					<div class="box txt_wrap">
						<div class="txt pr zoom after">
							<div class="zoom after pr menu1">
									<div class="menu1_active menu1_active_s fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Машины</div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="/ViewRequestList">Заявки</a></div></div></div></div>
									<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="/ViewCarTypeList">Типы машин</a></div></div></div></div>
							</div>
							<div class="txt_content">
								<table class="c_table b_table">
									<col width="25%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="*"/>
									<col width="70px"/>
									<tbody>
										<tr>
											<th><div class="th_div b_div">Марка/модель</div></th>
											<th><div class="th_div b_div">Гос. Номер</div></th>
											<th><div class="th_div b_div">Вместимость</div></th>
											<th><div class="b_div">Пробег</div></th>
											<th>&nbsp;</th>
										</tr>
										<%int i = 0;%>
										<c:forEach var="car" items="${requestScope.cars}">
											<tr class="<%=(i++ % 2) == 0 ? "tr_1" : "tr_2"%>">
												<td><c:out value="${car.id}"/></td>
												<td><c:out value="${car.govNumber}"/></td>
												<td>НЕ ЗНАЮ</td>
												<td><c:out value="${car.running}"/></td>
												<td class="b_div b_table_l_td">
														<a href="<%=request.getContextPath()%>/EditCar?id=${car.id}" title="Редактировать" class="green_edit"></a>
														<a href="<%=request.getContextPath()%>/DeleteCar?id=${car.id}" onclick="confirm('Хотите удалить?');" title="Удалить" class="red_del"></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<p class="add_auto"><a href="#"><img src="i/add.png" alt=""/> <span>Добавить машину</span></a></p>
								<div class="start_wrap box"><a class="pr fl start" href="#"><span class="db">В начало</span></a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
	</body>
</html>