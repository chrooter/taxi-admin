<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="isAdmin" value="true"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<title>Заказы</title>
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
					<div class="top_t">Заказы</div>
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
								<div class="menu1_active fl"><div class="menu1_activ_wrap"><div class="menu1_active_inner"><div class="menu1_active_div">Заявки</div></div></div></div>
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarList">Автопарк</a></div></div></div></div>
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeList">Типы машин</a></div></div></div></div>
							</div>
							<div class="txt_content">
								<c:if test="${!empty requestScope.orders}">
								<table class="c_table b_table">
									<col width="10%"/>
									<col width="25%"/>
									<col width="25%"/>
									<col width="15%"/>
									<col width="*"/>
									<col width="110px"/>

									<tbody>
										<tr>
											<th><div class="th_div b_div">Номер заказа</div></th>
											<th><div class="th_div b_div">Дата и время заказа</div></th>
											<th><div class="th_div b_div">Автомобиль</div></th>
											<th><div class="th_div b_div">Телефон клиента</div></th>
                                                                                        <th><div class="b_div">Статус</div></th>
											<th>&nbsp;</th>
										</tr>
										<% int i = 0; %>
										<c:forEach var="order" items="${requestScope.orders}">
										<tr class="<%=(i++ % 2) == 0 ? "tr_1" : "tr_2"%> autotest" id="at${order.id}">
											<td><fmt:formatNumber groupingUsed="false" value="${order.id}" minIntegerDigits="4"/></td>
											<td><fmt:formatDate value="${order.timeOrd}" type="both" dateStyle="long" timeStyle="short"/></td>
											<c:if test="${!empty order.car}"><td><c:out value="${order.car}"/></td></c:if>
                                                                                        <c:if test="${empty order.car}"><td>Машина не назначена</td></c:if>
											<td><c:out value="${order.phone}"/></td>
                                                                                        <c:choose>
                                                                                            <c:when test="${order.status=='new'}">
                                                                                                <td>Новый</td>
                                                                                            </c:when>
                                                                                            <c:when test="${order.status=='executing'}">
                                                                                                <td>Выполняется</td>
                                                                                            </c:when>
                                                                                            <c:when test="${order.status=='done'}">
                                                                                                <td>Выполнен</td>
                                                                                            </c:when>
                                                                                            <c:when test="${order.status=='canceled'}">
                                                                                                <td>Отменен</td>
                                                                                            </c:when>
                                                                                         </c:choose>
											<td class="b_div b_table_l_td">
												<c:if test="${pageScope.isAdmin}">
													<a href="<%=request.getContextPath()%>/EditOrder?id=${order.id}" class="blue_edit" title="Редактировать"></a>
													<c:if test="${order.status=='new'}">
                                                                                                                <a href="<%=request.getContextPath()%>/EditOrder?id=${order.id}&status=executing" class="play" title="Запустить"></a>
													</c:if>
                                                                                                        <c:if test="${order.status=='executing'}">
                                                                                                                <a href="<%=request.getContextPath()%>/EditOrder?id=${order.id}&status=done" class="green_ok" title="Выполнено"></a>
                                                                                                                <a href="<%=request.getContextPath()%>/EditOrder?id=${order.id}&status=canceled" class="cancel" onclick="return confirm('Хотите отменить заказ?')" title="Отменить"></a>
                                                                                                        </c:if>
                                                                                                        <c:if test="${order.status!='executing'}">
                                                                                                            <a href="<%=request.getContextPath()%>/DeleteOrder?id=${order.id}&startpoint=${order.startPoint}" class="red_del" onclick="return confirm('Хотите удалить заказ?')" title="Удалить"></a>
                                                                                                        </c:if>
												</c:if>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:if>
								<c:if test="${empty requestScope.orders}">
									<h2>Заявок не найдено</h2>
								</c:if>
								<p class="create_test">
								<c:if test="${pageScope.isAdmin}">
									<a href="<%=request.getContextPath()%>/CreateOrder"><img src="<%=request.getContextPath()%>/i/add.png" alt="Добавить"/> <span>Добавить заявку</span></a>
                                                                        <a href="<%=request.getContextPath()%>/XMLOrder"><span class="xml_link"><img src="<%=request.getContextPath()%>/i/xml.png" alt="Экпорт"/>Экспорт XML</span></a>
                                                                        <a href="<%=request.getContextPath()%>/importOrder.jsp"><span class="xml_link"><img src="<%=request.getContextPath()%>/i/xml.png" alt="Импорт"/>Импорт XML</span></a>
								</c:if>
								</p>
								<div class="start_wrap box"><a class="pr fl start" href="#"><span class="db">В начало</span></a></div>
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
			$(function() {
				$('.close_b_div_link').live('click', function() {
					var buttons = $('.close_b_div_link', $(this).parent().parent());
					$('.box', $(this).parent().parent()).removeClass('close_b_div');
					buttons.removeClass('close_b_div_link');
					buttons.addClass('open_b_div_link');
				});
				$('.open_b_div_link').live('click', function() {
					var buttons = $('.open_b_div_link', $(this).parent().parent());
					$('.box', $(this).parent().parent()).addClass('close_b_div');
					buttons.removeClass('open_b_div_link');
					buttons.addClass('close_b_div_link');
				});
			});
		</script>
	</body>
</html>