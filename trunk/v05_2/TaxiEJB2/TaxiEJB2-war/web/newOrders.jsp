<%@ page import="java.util.Date" %>
<%@ page import="ru.dreamjteam.entity.OrderVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${!empty requestScope.newOrders}">
<div class="fl pr l_side">
	<div class="l_side_inner">
		<h2>необработанные заявки</h2>
		<c:forEach items="${requestScope.newOrders}" var="order" varStatus="status">
			<div class="l_side_div box">
				<div class="fl l_side_div_numb">${status.count}.</div>
				<div class="box">
                    <%
                        Date now = new Date();
                        OrderVO order = (OrderVO) pageContext.getAttribute("order");
                        pageContext.setAttribute("waitingFor", (now.getTime() - order.getTimeOrd().getTime()) / 1000 / 60);

                    %>
					<div class="fr l_side_div_lnk"><a href="#" class="l_side_div_lnk_numb">(<c:out value="${pageScope.waitingFor}"/> мин)</a><a href="#"><img
							src="i/qwest.jpg" alt=""/></a></div>
					<div class="box">
                                            Номер заявки: <fmt:formatNumber groupingUsed="false" value="${order.id}" minIntegerDigits="4"/>
                                            Адрес: <c:out value="${order.startPoint}"/>
                                            <div class="l_side_div_date">Ждет с <fmt:formatDate value="${order.timeOrd}" pattern="dd.MM.yyyy HH:mm"/></div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</c:if>