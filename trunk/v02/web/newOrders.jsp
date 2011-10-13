<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="fl pr l_side">
	<div class="l_side_inner">
		<h2>необработанные заявки</h2>
		<c:forEach items="${requestScope.newRequests}" var="request">
			<div class="l_side_div box">
				<div class="fl l_side_div_numb">1.</div>
				<div class="box">
					<div class="fr l_side_div_lnk"><a href="#" class="l_side_div_lnk_numb">(???)</a><a href="#"><img
							src="i/qwest.jpg" alt=""/></a></div>
					<div class="box"><c:out value="${request.addrDest}"/><div class="l_side_div_date"><c:out value="${request.passengers}"/></div>
						Ждет с <c:out value="${request.timeOrd}"/>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>