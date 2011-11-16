<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<title>Добавить машину</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/css/ie.css"%>"/><![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath() + "/js/my.js"%>"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.qtip.js"></script>
		<!--[if IE]><script type="text/javascript" src="<%=request.getContextPath() + "/js/ie.js"%>"></script><![endif]-->
	    <!--[if IE]><script type="text/javascript" src="<%=request.getContextPath() + "/js/pie.js"%>"></script><![endif]-->
	</head>
	<body>
	<div id="loadpage" style="position: absolute; height: 120px; width: 400px; top: 50%; margin-top:-60px;
		left: 50%; margin-left: -200px; background-color: #ebf0f5; vertical-align: middle; text-align: right; z-index:1001">
		<p style=" height:100%; width:100%; vertical-align:middle; text-align:center; font-size: large;">
			<br><B>Загрузка ... ... Пожалуйста, подождите</B><br><br>
			<img src="<%=request.getContextPath()%>/i/loading.gif" alt="Загрузка"/>
		</p>
	</div>
		<div class="wrapper">
			<div class="head"><div class="head_inner">
				<a href="<%=request.getContextPath()%>/" class="logo"></a>
				<a href="#" class="o_code"></a>
			</div></div>

			<div class="content box">
				<div class="top pr"><div class="top2"><div class="top3">
					<div class="top_t">Парк автомобилей</div>
					<div class="fr top_inf">
						<%--<span class="top_inf_span"><strong><img src="<%=request.getContextPath()%>/i/m2.png" alt=""/>Пользователь:</strong> <a href="#"><c:out value="${requestScope.User}"/></a>  </span>--%>
						<%--<span class="top_inf_span"><strong>Статус:</strong> <a href="#"><c:out value="${requestScope.UserRole}"/></a></span>--%>
						<%--<span class="top_inf_span"><a href="#"><img src="<%=request.getContextPath()%>/i/settings.png" alt=""/><span>Настройки</span></a></span>--%>
						<%--<html:link action="/Logout"><img src="<%=request.getContextPath()%>/i/m.png" alt=""/><span>Выход</span></html:link>--%>
					</div>
				</div></div></div>
				<div class="text_content box">
					<div id="neworders_list">
						<jsp:include page="newOrders.jsp"/>
					</div>
					<div class="box txt_wrap">
						<div class="txt pr zoom after">
							<div class="zoom after pr menu1">
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewOrderList">Заявки</a></div></div></div></div>
								<div class="menu1_active fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Добавление автомобиля</div></div></div></div>
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeList">Типы машин</a></div></div></div></div>
							</div>
							<div class="txt_content">
								<form action="<%=request.getContextPath()%>/CreateCar" method="POST" accept-charset="UTF-8">
									<h2>Добавление автомобиля</h2>
									<div class="edit_box"><div class="box">
										<div>
											<c:forEach items="${requestScope.errors}" var="error">
												<strong style="color:red"><c:out value="${error}"/></strong><br>
											</c:forEach>
										</div>
										<div class="fl edit_side1">
                                                                                        <div class="edit_side_block">
												<h2><label for="model">Модель</label></h2>
												<div class="box"><input type="text" class="db edit_txt" name="model" maxlength="255" id="model" value="${requestScope.car.model}"/></div>
											</div>
                                                                                        
											<div class="edit_side_block">
												<h2><label for="govnum">Гос. Номер</label></h2>
												<div class="box"><input type="text" class="db edit_txt" name="govnum" maxlength="10" id="govnum" value="${requestScope.car.govNumber}"/></div>
											</div>
											
											<div class="edit_side_block">
												<h2><label for="color">Цвет</label></h2>
												<div class="box"><input type="text" class="db edit_txt" name="color" maxlength="255" id="color" value="${requestScope.car.color}"/></div>
											</div>
											<div class="edit_side_block">
												<h2><label for="cartype">Тип</label></h2>
												<div class="box">
													<select name="typeid" id="cartype">
														<c:forEach items="${requestScope.carTypes}" var="carType">
															<option value="${carType.id}" <c:if test="${requestScope.car.carTypeId eq carType.id}">selected="selected"</c:if>><c:out value="${carType.name}"/></option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div></div>
									<p class="create_test">
										<a href="#"><img onclick="$('#submitButton').trigger('click')" alt="Сохранить" src="<%=request.getContextPath()%>/i/save.jpg"/></a>
										<html:submit style="display:none" styleId="submitButton">Сохранить</html:submit>
										<html:cancel style="display:none;" styleId="cancelButton">Отменить</html:cancel>
										<a href="<%=request.getContextPath()%>/ViewCarList"><img alt="Отменить" src="<%=request.getContextPath()%>/i/cancel.jpg"/></a>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
		<script type="text/javascript">
			$(function() {
				UpdateNewOrderList('<%=request.getContextPath()%>/newOrders.jsp');
			});
		</script>
	</body>
</html>